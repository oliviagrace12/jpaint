package view.render;

import model.shape.Shape;
import view.interfaces.IRenderStrategy;
import view.interfaces.PaintCanvasBase;
import view.interfaces.RenderStrategyBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-08-07
 */
public class SelectedTriangleRenderStrategy implements IRenderStrategy {

    private final IRenderStrategy triangleRenderStrategy;
    private final PaintCanvasBase paintCanvas;

    SelectedTriangleRenderStrategy(IRenderStrategy triangleRenderStrategy, PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.triangleRenderStrategy = triangleRenderStrategy;
    }

    @Override
    public void render(Shape shape) {
        triangleRenderStrategy.render(shape);

        int[] xs = {shape.getX1()-5, shape.getX2()+5, shape.getX1()-5};
        int[] ys = {shape.getY1()-5, shape.getY2()+5, shape.getY2()+5};

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        float[] dash1 = {10.0f};
        graphics2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawPolygon(xs, ys, 3);
    }
}
