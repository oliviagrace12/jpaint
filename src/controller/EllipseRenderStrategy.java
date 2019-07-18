package controller;

import model.shape.Shape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class EllipseRenderStrategy implements IRenderStrategy {

    private final PaintCanvasBase paintCanvas;

    public EllipseRenderStrategy(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void render(Shape shape) {
        int width = Math.abs(shape.getX2() - shape.getX1());
        int height = Math.abs(shape.getY2() - shape.getY1());

        int x = shape.getX2() < shape.getX1() ? shape.getX2() : shape.getX1();
        int y = shape.getY2() < shape.getY1() ? shape.getY2() : shape.getY1();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawOval(x, y, width, height);
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillOval(x, y, width, height);
    }
}
