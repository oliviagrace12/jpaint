package view;

import model.interfaces.IApplicationState;
import model.shape.StartAndEndPointMode;
import view.command.DrawCommand;
import view.command.MoveCommand;
import view.command.SelectCommand;
import view.interfaces.ICommand;
import view.render.ShapesRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private ShapesTracker shapesTracker;
    private SelectedShapesTracker selectedShapesTracker;
    private MouseEvent lastMousePressedEvent;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapesRenderer;

    public PaintMouseAdapter(IApplicationState applicationState, ShapesRenderer shapesRenderer, ShapesTracker shapesTracker, SelectedShapesTracker selectedShapesTracker) {
        this.applicationState = applicationState;
        this.shapesRenderer = shapesRenderer;
        this.shapesTracker = shapesTracker;
        this.selectedShapesTracker = selectedShapesTracker;
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
            command = new DrawCommand(lastMousePressedEvent, event, applicationState, shapesRenderer, shapesTracker);
        } else if (currentState.equals(StartAndEndPointMode.SELECT)) {
            command = new SelectCommand(lastMousePressedEvent, event, shapesTracker, selectedShapesTracker);
        } else {
            command = new MoveCommand(lastMousePressedEvent, event, shapesTracker, selectedShapesTracker, shapesRenderer);
        }
        command.run();
    }
}
