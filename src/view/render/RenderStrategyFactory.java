package view.render;

import view.interfaces.IRenderStrategy;
import view.interfaces.PaintCanvasBase;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class RenderStrategyFactory {

    public static IRenderStrategy createEllipseRenderStrategy(PaintCanvasBase paintCanvas) {
        return new EllipseRenderStrategy(paintCanvas);
    }

    public static IRenderStrategy createTriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new TriangleRenderStrategy(paintCanvas);
    }

    public static IRenderStrategy createRectangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new RectangleRenderStrategy(paintCanvas);
    }

    public static IRenderStrategy createSelectedEllipseRenderStrategy(PaintCanvasBase paintCanvas) {
        return new SelectedEllipseRenderStrategy(new EllipseRenderStrategy(paintCanvas), paintCanvas);
    }

    public static IRenderStrategy createSelectedTriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new SelectedTriangleRenderStrategy(new TriangleRenderStrategy(paintCanvas), paintCanvas);
    }

    public static IRenderStrategy createSelectedRectangleRenderStrategy(PaintCanvasBase paintCanvas) {
        return new SelectedRectangleRenderStrategy(new RectangleRenderStrategy(paintCanvas), paintCanvas);
    }
}
