package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;

import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-18
 */
public class UngroupCommand implements IUndoRedo {

    private final Set<Shape> selectedShapes;
    private final Set<Shape> allShapes;

    public UngroupCommand(Set<Shape> selectedShapes, Set<Shape> allShapes) {
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
    }

    @Override
    public void run() {
        selectedShapes.forEach(s -> {
            allShapes.addAll(s.getChildren());
            s.getChildren().clear();
        });

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
