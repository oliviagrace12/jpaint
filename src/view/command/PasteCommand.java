package view.command;

import model.shape.Shape;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-06
 */
public class PasteCommand implements IUndoRedo {

    private final Set<Shape> copiedShapes;
    private final Set<Shape> allShapes;
    private final ShapesRenderer shapesRenderer;
    private Set<Shape> myCopiedShapes = new HashSet<>();

    public PasteCommand(Set<Shape> copiedShapes, Set<Shape> allShapes, ShapesRenderer shapesRenderer) {
        this.copiedShapes = copiedShapes;
        this.allShapes = allShapes;
        this.shapesRenderer = shapesRenderer;
    }

    @Override
    public void run() {
        Set<Shape> temp = new HashSet<>();
        copiedShapes.forEach(shape -> {
            Shape newShape = deepCopyShape(shape);
            temp.add(newShape);
            allShapes.add(newShape);
        });
        copiedShapes.clear();
        copiedShapes.addAll(temp);
        myCopiedShapes.addAll(temp);
        shapesRenderer.reRenderAllShapes(allShapes);
    }

    private Shape deepCopyShape(Shape shape) {
        Shape newShape = new Shape();
        newShape.setX1(shape.getX1() + 30);
        newShape.setX2(shape.getX2() + 30);
        newShape.setY1(shape.getY1() + 30);
        newShape.setY2(shape.getY2() + 30);
        newShape.setShapeType(shape.getShapeType());
        newShape.setPrimaryColor(shape.getPrimaryColor());
        newShape.setSecondaryColor(shape.getSecondaryColor());
        newShape.setShapeShadingType(shape.getShapeShadingType());

        return newShape;
    }

    @Override
    public void undo() {
        allShapes.removeAll(myCopiedShapes);
        shapesRenderer.reRenderAllShapes(allShapes);
    }

    @Override
    public void redo() {
        allShapes.addAll(myCopiedShapes);
        shapesRenderer.reRenderAllShapes(allShapes);
    }
}
