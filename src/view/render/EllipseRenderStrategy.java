package view.render;

import model.shape.Shape;
import view.interfaces.RenderStrategyBase;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class EllipseRenderStrategy extends RenderStrategyBase {

    EllipseRenderStrategy(PaintCanvasBase paintCanvas) {
        super(paintCanvas);
    }

    @Override
    public void render(Shape shape) {
        int width = Math.abs(shape.getX2() - shape.getX1());
        int height = Math.abs(shape.getY2() - shape.getY1());

        int x = shape.getX2() < shape.getX1() ? shape.getX2() : shape.getX1();
        int y = shape.getY2() < shape.getY1() ? shape.getY2() : shape.getY1();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        if (shapeShouldHaveOutline(shape)) {
            graphics2d.setColor(shape.getPrimaryColor().getColor());
            graphics2d.drawOval(x, y, width, height);
        }
        if (shapeShouldBeFilledIn(shape)) {
            graphics2d.setColor(shape.getSecondaryColor().getColor());
            graphics2d.fillOval(x, y, width, height);
        }
    }
}
