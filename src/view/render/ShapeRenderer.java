package view.render;

import model.shape.Shape;
import view.interfaces.IRenderStrategy;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapeRenderer {

    private final IRenderStrategy renderStrategy;

    ShapeRenderer(IRenderStrategy renderStrategy) {
        this.renderStrategy = renderStrategy;
    }

    public void render(Shape shape) {
        renderStrategy.render(shape);
    }
}
