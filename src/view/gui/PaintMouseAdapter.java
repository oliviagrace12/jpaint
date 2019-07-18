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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private Stack<Shape> shapes = new Stack<>();
    private List<Shape> selectedShapes = new ArrayList<>();
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
        selectedShapes.clear();
        for (Shape shape : shapes) {
            if (collisionOccurred(shape, event)) {
                selectedShapes.add(shape);
            }
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
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW)) {
            endDrawShape(event);
        }
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
}
