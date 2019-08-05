package view.render;

import view.interfaces.RenderStrategyBase;
import view.interfaces.PaintCanvasBase;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class RenderStrategyFactory {

    public static RenderStrategyBase createEllipseRenderStrategy(PaintCanvasBase paintCanvas) {
        return new EllipseRenderStrategy(paintCanvas);
    }

    public static RenderStrategyBase createTriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new TriangleRenderStrategy(paintCanvas);
    }

    public static RenderStrategyBase createRectangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new RectangleRenderStrategy(paintCanvas);
    }
}
