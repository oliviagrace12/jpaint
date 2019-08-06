package view.command;

import model.shape.Shape;
import view.interfaces.ICommand;
import view.render.ShapesRenderer;

import java.util.Set;

/**
 * Created by oliviachisman on 2019-08-06
 */
public class DeleteCommand implements ICommand {
    private final Set<Shape> selectedShapes;
    private final Set<Shape> allShapes;
    private final ShapesRenderer shapesRenderer;

    public DeleteCommand(Set<Shape> selectedShapes, Set<Shape> allShapes, ShapesRenderer shapesRenderer) {
        this.selectedShapes = selectedShapes;
        this.allShapes = allShapes;
        this.shapesRenderer = shapesRenderer;
    }

    @Override
    public void run() {
        allShapes.removeAll(selectedShapes);
        shapesRenderer.reRenderAllShapes(allShapes);
    }
}
