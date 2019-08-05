package view;

import model.shape.Shape;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class SelectedShapesTracker {

    private Set<Shape> selectedShapes = new HashSet<>();

    public void addShape(Shape shape) {
        selectedShapes.add(shape);
        System.out.println("Selectd shapes: " + selectedShapes);
    }

    public Collection<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void clearSelectedShapes() {
        selectedShapes.clear();
    }

    public boolean noShapedSelected() {
        return selectedShapes.isEmpty();
    }
}
