package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class MoveCommand implements IUndoRedo {

    private final MouseEvent event1;
    private final MouseEvent event2;
    private final Set<Shape> allShapes;
    private final Set<Shape> selectedShapes;
    private final ShapesRenderer shapesRenderer;
    private int offsetX;
    private int offsetY;

    public MoveCommand(MouseEvent event1, MouseEvent event2, Set<Shape> allShapes, Set<Shape> selectedShapes,
                       ShapesRenderer shapesRenderer) {
        this.event1 = event1;
        this.event2 = event2;
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
        this.shapesRenderer = shapesRenderer;
    }

    @Override
    public void run() {
        offsetX = event2.getX() - event1.getX();
        offsetY = event2.getY() - event1.getY();

        for (Shape shape : selectedShapes) {
            shape.getChildren().forEach(this::moveShape);
        }

        shapesRenderer.reRenderAllShapes(allShapes);
    }

    private void moveShape(Shape shape) {
        shape.setX1(shape.getX1() + offsetX);
        shape.setX2(shape.getX2() + offsetX);
        shape.setY1(shape.getY1() + offsetY);
        shape.setY2(shape.getY2() + offsetY);
    }

    @Override
    public void undo() {
        for (Shape shape : selectedShapes) {
            shape.getChildren().forEach(this::unMoveShape);
        }

        shapesRenderer.reRenderAllShapes(allShapes);
    }

    private void unMoveShape(Shape shape) {
        shape.setX1(shape.getX1() - offsetX);
        shape.setX2(shape.getX2() - offsetX);
        shape.setY1(shape.getY1() - offsetY);
        shape.setY2(shape.getY2() - offsetY);
    }

    @Override
    public void redo() {
        for (Shape shape : selectedShapes) {
            shape.getChildren().forEach(this::moveShape);
        }

        shapesRenderer.reRenderAllShapes(allShapes);
    }
}
