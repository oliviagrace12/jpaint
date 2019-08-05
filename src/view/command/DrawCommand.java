package view.command;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.ShapesTracker;
import view.interfaces.ICommand;
import view.render.ShapesRenderer;

import java.awt.event.MouseEvent;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class DrawCommand implements ICommand {

    private final MouseEvent event1;
    private final MouseEvent event2;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapeRenderer;
    private final ShapesTracker shapesTracker;

    public DrawCommand(MouseEvent event1, MouseEvent event2, IApplicationState applicationState,
                       ShapesRenderer shapeRenderer, ShapesTracker shapesTracker) {
        this.event1 = event1;
        this.event2 = event2;
        this.applicationState = applicationState;
        this.shapeRenderer = shapeRenderer;
        this.shapesTracker = shapesTracker;
    }

    @Override
    public void run() {
        Shape shape = new Shape();
        shape.setX1(event1.getX());
        shape.setY1(event1.getY());
        shape.setX2(event2.getX());
        shape.setY2(event2.getY());

        shape.setShapeType(applicationState.getActiveShapeType());
        shape.setShapeShadingType(applicationState.getActiveShapeShadingType());
        shape.setPrimaryColor(applicationState.getActivePrimaryColor());
        shape.setSecondaryColor(applicationState.getActiveSecondaryColor());

        shapeRenderer.renderShape(shape);
        shapesTracker.addShape(shape);
    }
}
