package view.interfaces;

import model.shape.Shape;

/**
 * Created by oliviachisman on 2019-07-16
 */
public abstract class RenderStrategyBase {

    protected final PaintCanvasBase paintCanvas;

    protected RenderStrategyBase(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    public abstract void render(Shape shape);
}
