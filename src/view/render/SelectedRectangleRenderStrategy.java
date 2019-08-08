package view.render;

import model.shape.Shape;
import view.interfaces.IRenderStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-08-07
 */
public class SelectedRectangleRenderStrategy implements IRenderStrategy {

    private final IRenderStrategy rectangleRenderStrategy;
    private final PaintCanvasBase paintCanvas;

    SelectedRectangleRenderStrategy(IRenderStrategy rectangleRenderStrategy, PaintCanvasBase paintCanvas) {
        this.rectangleRenderStrategy = rectangleRenderStrategy;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void render(Shape shape) {
        rectangleRenderStrategy.render(shape);

        int width = Math.abs(shape.getX2() - shape.getX1());
        int height = Math.abs(shape.getY2() - shape.getY1());

        int x = shape.getX2() < shape.getX1() ? shape.getX2() : shape.getX1();
        int y = shape.getY2() < shape.getY1() ? shape.getY2() : shape.getY1();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        float[] dash1 = {10.0f};
        graphics2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(x - 5, y - 5, width + 10, height + 10);
    }
}
