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
    public void mousePressed(MouseEvent event) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(event.getX());
        rectangle.setY(event.getY());
        rectangles.add(rectangle);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!rectangles.empty() && rectangles.peek() != null) {
            Rectangle rectangle = rectangles.pop();
            rectangle.setWidth(Math.abs(event.getX() - rectangle.getX()));
            rectangle.setHeight(Math.abs(event.getY() - rectangle.getY()));
            adjustXY(event, rectangle);

            render(rectangle);
        }
    }

    private void adjustXY(MouseEvent event, Rectangle rectangle) {
        if (event.getX() < rectangle.getX()) {
            rectangle.setX(event.getX());
        }
        if (event.getY() < rectangle.getY()) {
            rectangle.setY(event.getY());
        }
    }

    private void render(Rectangle rectangle) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
}
