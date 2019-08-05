package controller;

import model.interfaces.IApplicationState;
import model.shape.Shape;
import view.EventName;
import view.interfaces.IUiModule;

import java.util.HashSet;
import java.util.Set;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final Set<Shape> selectedShapes;
    private final Set<Shape> copiedShapes;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, Set<Shape> selectedShapes) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
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
            System.out.println("COPIED SHAPES: " + copiedShapes);
        });
    }
}
