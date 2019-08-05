package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import model.shape.Shape;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.PaintMouseAdapter;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;
import view.render.ShapesRenderer;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        Set<Shape> selectedShapes = new HashSet<>();

        IJPaintController controller = new JPaintController(uiModule, appState, selectedShapes);
        controller.setup();

        paintCanvas.addMouseListener(
                new PaintMouseAdapter(appState, new ShapesRenderer(paintCanvas), new HashSet<>(), selectedShapes));

        // For example purposes only; remove all lines below from your final project.

        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);

        // Clears the Canvas
        //paintCanvas.repaint();
    }
}
