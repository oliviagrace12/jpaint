package view.render;

import model.shape.ShapeShadingType;
import model.shape.Shape;
import view.interfaces.RenderStrategyBase;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class TriangleRenderStrategy extends RenderStrategyBase {

    public TriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        super(paintCanvas);
    }

    @Override
    public void render(Shape shape) {
        int[] xs = {shape.getX1(), shape.getX2(), shape.getX1()};
        int[] ys = {shape.getY1(), shape.getY2(), shape.getY2()};

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        if (!shape.getShapeShadingType().equals(ShapeShadingType.FILLED_IN)) {
            graphics2d.setColor(shape.getPrimaryColor().getColor());
            graphics2d.drawPolygon(xs, ys, 3);
        }
        if (!shape.getShapeShadingType().equals(ShapeShadingType.OUTLINE)) {
            graphics2d.setColor(shape.getSecondaryColor().getColor());
            graphics2d.fillPolygon(xs, ys, 3);
        }
    }
}