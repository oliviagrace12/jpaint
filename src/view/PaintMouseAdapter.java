package view;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import model.shape.StartAndEndPointMode;
import view.command.DrawCommand;
import view.command.MoveCommand;
import view.command.SelectCommand;
import view.interfaces.ICommand;
import view.render.ShapesRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private Set<Shape> allShapes;
    private Set<Shape> selectedShapes;
    private MouseEvent lastMousePressedEvent;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapesRenderer;

    public PaintMouseAdapter(IApplicationState applicationState, ShapesRenderer shapesRenderer, Set<Shape> allShapes,
                             Set<Shape> selectedShapes) {
        this.applicationState = applicationState;
        this.shapesRenderer = shapesRenderer;
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        lastMousePressedEvent = event;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        StartAndEndPointMode currentState = applicationState.getActiveStartAndEndPointMode();
        ICommand command;
        if (currentState.equals(StartAndEndPointMode.DRAW)) {
            command = new DrawCommand(lastMousePressedEvent, event, applicationState, shapesRenderer, allShapes);
        } else if (currentState.equals(StartAndEndPointMode.SELECT)) {
            command = new SelectCommand(lastMousePressedEvent, event, allShapes, selectedShapes, shapesRenderer);
        } else {
            command = new MoveCommand(lastMousePressedEvent, event, allShapes, selectedShapes, shapesRenderer);
        }
        command.run();
    }
}
