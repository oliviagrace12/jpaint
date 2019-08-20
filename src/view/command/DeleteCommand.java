package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-06
 */
public class DeleteCommand implements IUndoRedo {
    private final Set<Shape> selectedShapes;
    private final Set<Shape> allShapes;
    private final ShapesRenderer shapesRenderer;
    private final Set<Shape> removedShapes;

    public DeleteCommand(Set<Shape> selectedShapes, Set<Shape> allShapes, ShapesRenderer shapesRenderer) {
        this.selectedShapes = selectedShapes;
        this.allShapes = allShapes;
        this.shapesRenderer = shapesRenderer;
        this.removedShapes = new HashSet<>();
    }

    @Override
    public void run() {
        allShapes.removeAll(selectedShapes);
        removedShapes.addAll(selectedShapes);
        selectedShapes.clear();
        shapesRenderer.reRenderAllShapes(allShapes);
    }

    @Override
    public void undo() {
        allShapes.addAll(removedShapes);
        shapesRenderer.reRenderAllShapes(allShapes);
    }

    @Override
    public void redo() {
        allShapes.removeAll(removedShapes);
        shapesRenderer.reRenderAllShapes(allShapes);
    }
}
