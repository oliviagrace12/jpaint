package view.render;

import model.shape.Shape;
import view.interfaces.RenderStrategyBase;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class TriangleRenderStrategy extends RenderStrategyBase {

    TriangleRenderStrategy(PaintCanvasBase paintCanvas) {
        super(paintCanvas);
    }

    @Override
    public void render(Shape shape) {
        int[] xs = {shape.getX1()+2, shape.getX2()+2, shape.getX1()+2};
        int[] ys = {shape.getY1()+2, shape.getY2()+2, shape.getY2()+2};

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        if (shapeShouldHaveOutline(shape)) {
            graphics2d.setColor(shape.getPrimaryColor().getColor());
            graphics2d.drawPolygon(xs, ys, 3);
        }
        if (shapeShouldBeFilledIn(shape)) {
            graphics2d.setColor(shape.getSecondaryColor().getColor());
            graphics2d.fillPolygon(xs, ys, 3);
        }
    }
}
