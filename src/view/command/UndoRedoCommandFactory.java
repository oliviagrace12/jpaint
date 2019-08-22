package view.command;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-21
 */
public class UndoRedoCommandFactory {

    public static IUndoRedo createDeleteCommand(Set<Shape> selectedShapes, Set<Shape> allShapes,
                                               ShapesRenderer shapesRenderer) {
        return new DeleteCommand(selectedShapes, allShapes, shapesRenderer);
    }

    public static IUndoRedo createDrawCommand(MouseEvent event1, MouseEvent event2, IApplicationState applicationState,
                                             ShapesRenderer shapeRenderer, Set<Shape> allShapes) {
        return new DrawCommand(event1, event2, applicationState, shapeRenderer, allShapes);
    }

    public static IUndoRedo createGroupCommand(Set<Shape> selectedShapes, Set<Shape> allShapes) {
        return new GroupCommand(selectedShapes, allShapes);
    }

    public static IUndoRedo createMoveCommand(MouseEvent event1, MouseEvent event2, Set<Shape> allShapes,
                                             Set<Shape> selectedShapes, ShapesRenderer shapesRenderer) {
        return new MoveCommand(event1, event2, allShapes, selectedShapes, shapesRenderer);
    }

    public static IUndoRedo createPasteCommand(Set<Shape> copiedShapes, Set<Shape> allShapes,
                                              ShapesRenderer shapesRenderer) {
        return new PasteCommand(copiedShapes, allShapes, shapesRenderer);
    }

    public static IUndoRedo createUngroupCommand(Set<Shape> selectedShapes, Set<Shape> allShapes) {
        return new UngroupCommand(selectedShapes, allShapes);
    }
}
