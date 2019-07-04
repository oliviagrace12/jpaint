package view.gui;

import model.shape.Rectangle;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    Stack<Rectangle> rectangles;
    PaintCanvasBase paintCanvas;

    public PaintMouseAdapter(Stack<Rectangle> rectangles, PaintCanvasBase paintCanvas) {
        this.rectangles = rectangles;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(e.getX());
        rectangle.setY(e.getY());
        rectangles.add(rectangle);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!rectangles.empty() && rectangles.peek() != null) {
            Rectangle rectangle = rectangles.pop();
            rectangle.setWidth(Math.abs(e.getX() - rectangle.getX()));
            if (e.getX() < rectangle.getX()) {
                rectangle.setX(e.getX());
            }
            rectangle.setHeight(Math.abs(e.getY() - rectangle.getY()));
            if (e.getY() < rectangle.getY()) {
                rectangle.setY(e.getY());
            }
            Graphics2D graphics2d = paintCanvas.getGraphics2D();
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(Color.BLUE);
            graphics2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            graphics2d.setColor(Color.GREEN);
            graphics2d.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        }
    }
}
