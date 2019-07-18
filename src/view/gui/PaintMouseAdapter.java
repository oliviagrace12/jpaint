package view.gui;

import controller.EllipseRenderStrategy;
import controller.IRenderStrategy;
import controller.RectangleRenderStrategy;
import controller.TriangleRenderStrategy;
import model.ShapeType;
import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private Shape shape;
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState applicationState;

    public PaintMouseAdapter(PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Shape r = new Shape();
        r.setX1(event.getX());
        r.setY1(event.getY());

        shape = r;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Shape r = shape;
        shape = null;

        r.setX2(event.getX());
        r.setY2(event.getY());

        IRenderStrategy renderStrategy;

        if (applicationState.getActiveShapeType().equals(ShapeType.RECTANGLE)) {
            renderStrategy = new RectangleRenderStrategy(paintCanvas);
        } else if (applicationState.getActiveShapeType().equals(ShapeType.ELLIPSE)) {
            renderStrategy = new EllipseRenderStrategy(paintCanvas);
        } else {
            renderStrategy = new TriangleRenderStrategy(paintCanvas);
        }
//
        renderStrategy.render(r);
    }

    private void adjustXY(MouseEvent event, Shape rectangle) {
        if (event.getX() < rectangle.getX1()) {
            rectangle.setX1(event.getX());
        }
        if (event.getY() < rectangle.getY1()) {
            rectangle.setY1(event.getY());
        }
    }

    private void render(Shape rectangle) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(rectangle.getX1(), rectangle.getY1(), rectangle.getWidth(), rectangle.getHeight());
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(rectangle.getX1(), rectangle.getY1(), rectangle.getWidth(), rectangle.getHeight());
    }
}
