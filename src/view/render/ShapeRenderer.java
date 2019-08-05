package view.render;

import model.shape.Shape;
import view.interfaces.RenderStrategyBase;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapeRenderer {

    private final RenderStrategyBase renderStrategy;
    private final Shape shape;

    public ShapeRenderer(Shape shape, RenderStrategyBase renderStrategy) {
        this.renderStrategy = renderStrategy;
        this.shape = shape;
    }

    public void render() {
        renderStrategy.render(shape);
    }
}
