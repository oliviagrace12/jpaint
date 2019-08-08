package view.render;

import model.shape.Shape;
import model.shape.ShapeType;
import view.interfaces.IRenderStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapesRenderer {

    private final PaintCanvasBase paintCanvas;
    private final IRenderStrategy triangleRenderStrategy;
    private final IRenderStrategy rectangleRenderStrategy;
    private final IRenderStrategy ellipseRenderStrategy;
    private final IRenderStrategy selectedTriangleRenderStrategy;
    private final IRenderStrategy selectedRectangleRenderStrategy;
    private final IRenderStrategy selectedEllipseRenderStrategy;
    private final Set<Shape> selectedShapes;

    public ShapesRenderer(PaintCanvasBase paintCanvas, Set<Shape> selectedShapes) {
        this.paintCanvas = paintCanvas;
        triangleRenderStrategy = RenderStrategyFactory.createTriangleRenderStrategy(paintCanvas);
        rectangleRenderStrategy = RenderStrategyFactory.createRectangleRenderStrategy(paintCanvas);
        ellipseRenderStrategy = RenderStrategyFactory.createEllipseRenderStrategy(paintCanvas);
        selectedTriangleRenderStrategy = RenderStrategyFactory.createSelectedTriangleRenderStrategy(paintCanvas);
        selectedRectangleRenderStrategy = RenderStrategyFactory.createSelectedRectangleRenderStrategy(paintCanvas);
        selectedEllipseRenderStrategy = RenderStrategyFactory.createSelectedEllipseRenderStrategy(paintCanvas);
        this.selectedShapes = selectedShapes;
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
        boolean isSelected = selectedShapes.contains(shape);
        IRenderStrategy renderStrategy;
        if (shape.getShapeType().equals(ShapeType.RECTANGLE)) {
            renderStrategy = isSelected ? selectedRectangleRenderStrategy : rectangleRenderStrategy;
        } else if (shape.getShapeType().equals(ShapeType.ELLIPSE)) {
            renderStrategy = isSelected ? selectedEllipseRenderStrategy : ellipseRenderStrategy;
        } else {
            renderStrategy = isSelected ? selectedTriangleRenderStrategy : triangleRenderStrategy;
        }

        new ShapeRenderer(renderStrategy).render(shape, paintCanvas);
    }

}
