package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import model.shape.Shape;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.PaintMouseAdapter;
import view.interfaces.*;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        Set<Shape> allShapes = new HashSet<>();
        Set<Shape> selectedShapes = new HashSet<>();
        ShapesRenderer shapesRenderer = new ShapesRenderer(paintCanvas, selectedShapes);
        Stack<IUndoRedo> commands = new Stack<>();
        Stack<IUndoRedo> undoCommands = new Stack<>();

        IJPaintController controller = new JPaintController(uiModule, appState, shapesRenderer, allShapes,
                selectedShapes, commands, undoCommands);
        controller.setup();

        paintCanvas.addMouseListener(
                new PaintMouseAdapter(appState, shapesRenderer, allShapes, selectedShapes, commands));
    }
}
