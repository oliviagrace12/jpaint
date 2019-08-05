package view.command;

import model.shape.Shape;
import view.interfaces.ICommand;
import view.render.ShapesRenderer;

import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class MoveCommand implements ICommand {

    private final MouseEvent event1;
    private final MouseEvent event2;
    private final Set<Shape> shapesTracker;
    private final Set<Shape> selectedShapesTracker;
    private final ShapesRenderer shapesRenderer;

    public MoveCommand(MouseEvent event1, MouseEvent event2, Set<Shape> shapesTracker,
                       Set<Shape> selectedShapesTracker, ShapesRenderer shapesRenderer) {
        this.event1 = event1;
        this.event2 = event2;
        this.shapesTracker = shapesTracker;
        this.selectedShapesTracker = selectedShapesTracker;
        this.shapesRenderer = shapesRenderer;
    }

    @Override
    public void run() {
        int offsetX = event2.getX() - event1.getX();
        int offsetY = event2.getY() - event1.getY();

        if (selectedShapesTracker.isEmpty()) {
            return;
        }

        for (Shape shape : selectedShapesTracker) {
            moveShape(shape, offsetX, offsetY);
        }
        shapesRenderer.reRenderAllShapes(shapesTracker);
    }

    private void moveShape(Shape shape, int offsetX, int offsetY) {
        shape.setX1(shape.getX1() + offsetX);
        shape.setX2(shape.getX2() + offsetX);
        shape.setY1(shape.getY1() + offsetY);
        shape.setY2(shape.getY2() + offsetY);
    }

}
