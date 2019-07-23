package view.gui;

import controller.EllipseRenderStrategy;
import controller.IRenderStrategy;
import controller.RectangleRenderStrategy;
import controller.TriangleRenderStrategy;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private Stack<Shape> shapes = new Stack<>();
    private Set<Shape> selectedShapes = new HashSet<>();
    private Offset offset;
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState applicationState;

    public PaintMouseAdapter(PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.SELECT)) {
            selectShapes(event);
        }
    }

    private void selectShapes(MouseEvent event) {
        boolean noShapesSelected = true;
        for (Shape shape : shapes) {
            if (collisionOccurred(shape, event)) {
                selectedShapes.add(shape);
                noShapesSelected = false;
            }
        }
        if (noShapesSelected) {
            selectedShapes.clear();
        }
        System.out.println("Selected shapes: " + selectedShapes);
    }

    private boolean collisionOccurred(Shape shape, MouseEvent event) {
        int lowestX = Math.min(shape.getX1(), shape.getX2());
        int highestX = Math.max(shape.getX1(), shape.getX2());
        int lowestY = Math.min(shape.getY1(), shape.getY2());
        int highestY = Math.max(shape.getY1(), shape.getY2());

        return event.getX() >= lowestX
                && event.getX() <= highestX
                && event.getY() >= lowestY
                && event.getY() <= highestY;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW)) {
            startDrawShape(event);
        } else if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)) {
            startMoveShapes(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW)) {
            endDrawShape(event);
        } else if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)) {
            endMoveShapes(event);
        }
    }

    private void startMoveShapes(MouseEvent event) {
        offset = new Offset();
        offset.x1 = event.getX();
        offset.y1 = event.getY();
    }

    private void endMoveShapes(MouseEvent event) {
        offset.x2 = event.getX();
        offset.y2 = event.getY();

        int offsetX = offset.x2 - offset.x1;
        int offsetY = offset.y2 - offset.y1;

        for (Shape shape : selectedShapes) {
            moveShape(shape, offsetX, offsetY);
        }

        reRenderAllShapes();
    }

    private void moveShape(Shape shape, int offsetX, int offsetY) {
        shape.setX1(shape.getX1() + offsetX);
        shape.setX2(shape.getX2() + offsetX);
        shape.setY1(shape.getY1() + offsetY);
        shape.setY2(shape.getY2() + offsetY);
    }

    private void startDrawShape(MouseEvent event) {
        Shape r = new Shape();
        r.setX1(event.getX());
        r.setY1(event.getY());

        shapes.push(r);
    }

    private void endDrawShape(MouseEvent event) {
        Shape shape = shapes.peek();

        shape.setX2(event.getX());
        shape.setY2(event.getY());
        shape.setShapeType(applicationState.getActiveShapeType());
        shape.setShapeShadingType(applicationState.getActiveShapeShadingType());
        shape.setPrimaryColor(applicationState.getActivePrimaryColor());
        shape.setSecondaryColor(applicationState.getActiveSecondaryColor());

        renderShape(shape);
    }

    private void reRenderAllShapes() {
        paintCanvas.repaint();
        shapes.forEach(this::renderShape);
    }

    private void renderShape(Shape shape) {
        IRenderStrategy renderStrategy;
        if (shape.getShapeType().equals(ShapeType.RECTANGLE)) {
            renderStrategy = new RectangleRenderStrategy(paintCanvas);
        } else if (shape.getShapeType().equals(ShapeType.ELLIPSE)) {
            renderStrategy = new EllipseRenderStrategy(paintCanvas);
        } else {
            renderStrategy = new TriangleRenderStrategy(paintCanvas);
        }

        renderStrategy.render(shape);
    }

    private class Offset {
        int x1;
        int y1;
        int x2;
        int y2;
    }
}
