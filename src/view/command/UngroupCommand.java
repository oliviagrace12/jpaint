package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-18
 */
public class UngroupCommand implements IUndoRedo {

    private final Set<Shape> selectedShapes;
    private final Set<Shape> allShapes;
    private final Set<Shape> group;
    private Shape parent;

    UngroupCommand(Set<Shape> selectedShapes, Set<Shape> allShapes) {
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
        this.group = new HashSet<>();
    }

    @Override
    public void run() {
        selectedShapes.forEach(s -> {
            group.addAll(s.getChildren());
            allShapes.addAll(s.getChildren());
            s.getChildren().clear();
        });
    }

    @Override
    public void undo() {
        allShapes.removeAll(group);
        parent = group.iterator().next();
        parent.setChildren(group);
        allShapes.add(parent);
    }

    @Override
    public void redo() {
        allShapes.addAll(group);
        parent.getChildren().clear();
    }
}
