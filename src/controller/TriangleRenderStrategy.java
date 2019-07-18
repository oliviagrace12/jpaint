package controller;

import model.shape.Shape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class TriangleRenderStrategy implements IRenderStrategy {

    private final PaintCanvasBase paintCanvas;

    public TriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void render(Shape shape) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        int[] xs = {shape.getX1(), shape.getWidth()};
        int[] ys = {shape.getY1(), shape.getHeight()};
        graphics2d.drawPolygon(xs, ys, 3);
        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillPolygon(shape.getX1(), shape.getY1(), shape.getWidth(), shape.getHeight());
    }
}
