package view.render;

import model.shape.Shape;
import view.interfaces.PaintCanvasBase;
import view.interfaces.RenderStrategyBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapeRenderer {

    private final RenderStrategyBase renderStrategy;

    ShapeRenderer(RenderStrategyBase renderStrategy) {
        this.renderStrategy = renderStrategy;
    }

    public void render(Shape shape, PaintCanvasBase paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        renderStrategy.render(shape);
    }
}
