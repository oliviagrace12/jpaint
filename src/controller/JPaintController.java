package controller;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.EventName;
import view.command.DeleteCommand;
import view.command.PasteCommand;
import view.interfaces.IUiModule;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapesRenderer shapesRenderer;
    private final Set<Shape> allShapes;
    private final Set<Shape> selectedShapes;
    private final Set<Shape> copiedShapes;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapesRenderer shapesRenderer,
                            Set<Shape> allShapes, Set<Shape> selectedShapes) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapesRenderer = shapesRenderer;
        this.allShapes = allShapes;
        this.selectedShapes = selectedShapes;
        this.copiedShapes = new HashSet<>();
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
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(copiedShapes, allShapes, shapesRenderer).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(selectedShapes, allShapes, shapesRenderer).run());
    }
}
