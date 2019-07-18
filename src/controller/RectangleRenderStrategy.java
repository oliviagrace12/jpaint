package controller;

import model.ShapeShadingType;
import model.shape.Shape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Created by oliviachisman on 2019-07-16
 */
public class RectangleRenderStrategy implements IRenderStrategy {

    private final PaintCanvasBase paintCanvas;

    public RectangleRenderStrategy(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void render(Shape shape) {
        int width = Math.abs(shape.getX2()- shape.getX1());
        int height = Math.abs(shape.getY2() - shape.getY1());

        int x = shape.getX2() < shape.getX1() ? shape.getX2() : shape.getX1();
        int y = shape.getY2() < shape.getY1() ? shape.getY2() : shape.getY1();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        if (!shape.getShapeShadingType().equals(ShapeShadingType.FILLED_IN)) {
            graphics2d.setColor(shape.getPrimaryColor().getColor());
            graphics2d.drawRect(x, y, width, height);
        }
        if (!shape.getShapeShadingType().equals(ShapeShadingType.OUTLINE)) {
            graphics2d.setColor(shape.getSecondaryColor().getColor());
            graphics2d.fillRect(x, y, width, height);
        }
    }

}
