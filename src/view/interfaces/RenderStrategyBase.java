package view.interfaces;

import model.shape.Shape;
import model.shape.ShapeShadingType;

/**
 * Created by oliviachisman on 2019-07-16
 */
public abstract class RenderStrategyBase {

    protected final PaintCanvasBase paintCanvas;

    protected RenderStrategyBase(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    public abstract void render(Shape shape);

    protected boolean shapeShouldBeFilledIn(Shape shape) {
        return !shape.getShapeShadingType().equals(ShapeShadingType.OUTLINE);
    }

    protected boolean shapeShouldHaveOutline(Shape shape) {
        return !shape.getShapeShadingType().equals(ShapeShadingType.FILLED_IN);
    }
}
