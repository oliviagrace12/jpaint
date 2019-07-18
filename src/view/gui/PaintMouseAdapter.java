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
            for (Shape shape : shapes) {
                if (collisionOccurred()) {
                    selectedShapes.add(shape);
                }
            }
        }
    }

    private boolean collisionOccurred() {
        return false;
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
        Shape r = shapes.peek();

        r.setX2(event.getX());
        r.setY2(event.getY());
        r.setShapeType(applicationState.getActiveShapeType());
        r.setShapeShadingType(applicationState.getActiveShapeShadingType());
        r.setPrimaryColor(applicationState.getActivePrimaryColor());
        r.setSecondaryColor(applicationState.getActiveSecondaryColor());

        IRenderStrategy renderStrategy;
        if (r.getShapeType().equals(ShapeType.RECTANGLE)) {
            renderStrategy = new RectangleRenderStrategy(paintCanvas);
        } else if (r.getShapeType().equals(ShapeType.ELLIPSE)) {
            renderStrategy = new EllipseRenderStrategy(paintCanvas);
        } else {
            renderStrategy = new TriangleRenderStrategy(paintCanvas);
        }

        renderStrategy.render(r);
    }
}
