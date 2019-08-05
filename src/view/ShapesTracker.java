package view;

import model.shape.Shape;

import java.util.Collection;
import java.util.Stack;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class ShapesTracker {

    private Stack<Shape> shapes = new Stack<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("All Shapes: " + shapes);
    }

    public Collection<Shape> getAllShapes() {
        return shapes;
    }
}
