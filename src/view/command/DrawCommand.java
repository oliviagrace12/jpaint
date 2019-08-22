package view.command;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class DrawCommand implements IUndoRedo {

    private final MouseEvent event1;
    private final MouseEvent event2;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapeRenderer;
    private Shape shape;
    private final Set<Shape> allShapes;

    DrawCommand(MouseEvent event1, MouseEvent event2, IApplicationState applicationState,
                       ShapesRenderer shapeRenderer, Set<Shape> allShapes) {
        this.event1 = event1;
        this.event2 = event2;
        this.applicationState = applicationState;
        this.shapeRenderer = shapeRenderer;
        this.allShapes = allShapes;
    }

    @Override
    public void run() {
        shape = new Shape();
        shape.setX1(event1.getX());
        shape.setY1(event1.getY());
        shape.setX2(event2.getX());
        shape.setY2(event2.getY());

        shape.setShapeType(applicationState.getActiveShapeType());
        shape.setShapeShadingType(applicationState.getActiveShapeShadingType());
        shape.setPrimaryColor(applicationState.getActivePrimaryColor());
        shape.setSecondaryColor(applicationState.getActiveSecondaryColor());

        shapeRenderer.renderShape(shape);
        allShapes.add(shape);
    }

    @Override
    public void undo() {
        allShapes.remove(shape);
        shapeRenderer.reRenderAllShapes(allShapes);
    }

    @Override
    public void redo() {
        allShapes.add(shape);
        shapeRenderer.reRenderAllShapes(allShapes);
    }
}
