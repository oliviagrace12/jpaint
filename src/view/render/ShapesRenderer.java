package view.render;

import model.shape.Shape;
import model.shape.ShapeType;
import view.interfaces.RenderStrategyBase;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.Collection;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapesRenderer {

    private final PaintCanvasBase paintCanvas;
    private final RenderStrategyBase triangleRenderStrategy;
    private final RenderStrategyBase rectangleRenderStrategy;
    private final RenderStrategyBase ellipseRenderStrategy;

    public ShapesRenderer(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        triangleRenderStrategy = RenderStrategyFactory.createTriangleRenderStrategy(paintCanvas);
        rectangleRenderStrategy = RenderStrategyFactory.createRectangleRenderStrategy(paintCanvas);
        ellipseRenderStrategy = RenderStrategyFactory.createEllipseRenderStrategy(paintCanvas);
    }

    public void reRenderAllShapes(Collection<Shape> shapes) {
        clearCanvas();
        shapes.forEach(this::renderShape);
    }

    private void clearCanvas() {
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 50000, 50000);
    }

    public void renderShape(Shape shape) {
        ShapeRenderer shapeRenderer;
        if (shape.getShapeType().equals(ShapeType.RECTANGLE)) {
            shapeRenderer = new ShapeRenderer(shape, rectangleRenderStrategy);
        } else if (shape.getShapeType().equals(ShapeType.ELLIPSE)) {
            shapeRenderer = new ShapeRenderer(shape, ellipseRenderStrategy);
        } else {
            shapeRenderer = new ShapeRenderer(shape, triangleRenderStrategy);
        }

        shapeRenderer.render();
    }

}
