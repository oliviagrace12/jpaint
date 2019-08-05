package view.command;

import model.shape.Shape;
import view.SelectedShapesTracker;
import view.ShapesTracker;
import view.interfaces.ICommand;

import java.awt.event.MouseEvent;

/**
 * Created by oliviachisman on 2019-08-04
 */
public class SelectCommand implements ICommand {

    private final MouseEvent event1;
    private final MouseEvent event2;
    private final ShapesTracker shapesTracker;
    private final SelectedShapesTracker selectedShapes;

    public SelectCommand(MouseEvent event1, MouseEvent event2, ShapesTracker shapesTracker, SelectedShapesTracker selectedShapesTracker) {
        this.event1 = event1;
        this.event2 = event2;
        this.shapesTracker = shapesTracker;
        this.selectedShapes = selectedShapesTracker;
    }

    @Override
    public void run() {
        int zoneMinX = Math.min(event1.getX(), event2.getX());
        int zoneMaxX = Math.max(event1.getX(), event2.getX());
        int zoneMinY = Math.min(event1.getY(), event2.getY());
        int zoneMaxY = Math.max(event1.getY(), event2.getY());

        selectedShapes.clearSelectedShapes();
        for (Shape shape : shapesTracker.getAllShapes()) {
            if (collisionOccurred(shape, zoneMinX, zoneMaxX, zoneMinY, zoneMaxY)) {
                selectedShapes.addShape(shape);
            }
        }
    }

    private boolean collisionOccurred(Shape shape, int zoneMinX, int zoneMaxX, int zoneMinY, int zoneMaxY) {
        int shapeMinX = Math.min(shape.getX1(), shape.getX2());
        int shapeMaxX = Math.max(shape.getX1(), shape.getX2());
        int shapeMinY = Math.min(shape.getY1(), shape.getY2());
        int shapeMaxY = Math.max(shape.getY1(), shape.getY2());

        return zoneMaxX >= shapeMinX && zoneMaxY >= shapeMinY && shapeMaxX >= zoneMinX && shapeMaxY >= zoneMinY;
    }
}
