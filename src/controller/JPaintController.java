package controller;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.EventName;
import view.command.DeleteCommand;
import view.command.PasteCommand;
import view.interfaces.ICommand;
import view.interfaces.IUiModule;
import view.interfaces.IUndoRedo;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapesRenderer;
    private final Set<Shape> allShapes;
    private final Set<Shape> selectedShapes;
    private final Set<Shape> copiedShapes;
    private final Stack<IUndoRedo> commands;
    private final Stack<IUndoRedo> undoCommands;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapesRenderer shapesRenderer,
                            Set<Shape> allShapes, Set<Shape> selectedShapes, Stack<IUndoRedo> commands,
                            Stack<IUndoRedo> undoCommands) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapesRenderer = shapesRenderer;
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
        this.copiedShapes = new HashSet<>();
        this.commands = commands;
        this.undoCommands = undoCommands;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> {
            copiedShapes.clear();
            copiedShapes.addAll(selectedShapes);
        });
        uiModule.addEvent(EventName.PASTE, () -> {
            IUndoRedo c = new PasteCommand(copiedShapes, allShapes, shapesRenderer);
            c.run();
            commands.add(c);
        });
        uiModule.addEvent(EventName.DELETE, () -> {
            IUndoRedo c = new DeleteCommand(selectedShapes, allShapes, shapesRenderer);
            c.run();
            commands.add(c);
        });
        uiModule.addEvent(EventName.UNDO, () -> {
            if (commands.isEmpty()) {
                return;
            }
            IUndoRedo c = commands.pop();
            c.undo();
            undoCommands.push(c);
        });
        uiModule.addEvent(EventName.REDO, () -> {
            if (undoCommands.isEmpty()) {
                return;
            }
            IUndoRedo c = undoCommands.pop();
            c.redo();
            commands.push(c);
        });
    }
}
