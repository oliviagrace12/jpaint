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

import java.awt.*;
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
    private Offset selectionZone;
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState applicationState;

    public PaintMouseAdapter(PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        StartAndEndPointMode currentState = applicationState.getActiveStartAndEndPointMode();
        if (currentState.equals(StartAndEndPointMode.DRAW)) {
            startDrawShape(event);
        } else if (currentState.equals(StartAndEndPointMode.SELECT)) {
            startSelectShapes(event);
        } else if (currentState.equals(StartAndEndPointMode.MOVE)) {
            startMoveShapes(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        StartAndEndPointMode currentState = applicationState.getActiveStartAndEndPointMode();
        if (currentState.equals(StartAndEndPointMode.DRAW)) {
            endDrawShape(event);
        } else if (currentState.equals(StartAndEndPointMode.SELECT)) {
            endSelectShapes(event);
        } else if (currentState.equals(StartAndEndPointMode.MOVE)) {
            endMoveShapes(event);
        }
    }

    private void startSelectShapes(MouseEvent event) {
        selectionZone = new Offset();
        selectionZone.x1 = event.getX();
        selectionZone.y1 = event.getY();
    }

    private void endSelectShapes(MouseEvent event) {
        selectionZone.x2 = event.getX();
        selectionZone.y2 = event.getY();

        int zoneMinX = Math.min(selectionZone.x1, selectionZone.x2);
        int zoneMaxX = Math.max(selectionZone.x1, selectionZone.x2);
        int zoneMinY = Math.min(selectionZone.y1, selectionZone.y2);
        int zoneMaxY = Math.max(selectionZone.y1, selectionZone.y2);

        selectedShapes.clear();
        for (Shape shape : shapes) {
            if (collisionOccurred(shape, zoneMinX, zoneMaxX, zoneMinY, zoneMaxY))
            selectedShapes.add(shape);
        }
    }

    private boolean collisionOccurred(Shape shape, int zoneMinX, int zoneMaxX, int zoneMinY, int zoneMaxY) {
        int shapeMinX = Math.min(shape.getX1(), shape.getX2());
        int shapeMaxX = Math.max(shape.getX1(), shape.getX2());
        int shapeMinY = Math.min(shape.getY1(), shape.getY2());
        int shapeMaxY = Math.max(shape.getY1(), shape.getY2());

        return zoneMaxX >= shapeMinX && zoneMaxY >= shapeMinY && shapeMaxX >= zoneMinX && shapeMaxY >= zoneMinY;
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

        if (selectedShapes.isEmpty()) {
            return;
        }

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
        clearCanvas();
        shapes.forEach(this::renderShape);
    }

    private void clearCanvas() {
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 50000, 50000);
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
