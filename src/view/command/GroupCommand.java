package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-18
 */
public class GroupCommand implements IUndoRedo {

    private final Set<Shape> selectedShapes;
    private final Set<Shape> allShapes;
    private Shape parent;
    private Set<Shape> group = new HashSet<>();

    public GroupCommand(Set<Shape> selectedShapes, Set<Shape> allShapes) {
        this.selectedShapes = selectedShapes;
        this.allShapes = allShapes;
    }

    @Override
    public void run() {
        if (selectedShapes.isEmpty()) {
            return;
        }
        group.addAll(selectedShapes);
        groupShapes();
    }

    private void groupShapes() {
        allShapes.removeAll(group);
        parent = group.iterator().next();
        parent.getChildren().addAll(group);
        allShapes.add(parent);
    }

    @Override
    public void undo() {
        parent.getChildren().clear();
        allShapes.addAll(group);
    }

    @Override
    public void redo() {
        groupShapes();
    }
}
