package view.gui;

import model.shape.Rectangle;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by oliviachisman on 2019-07-04
 */
public class PaintMouseAdapter extends MouseAdapter {

    private Rectangle rectangle;
    private PaintCanvasBase paintCanvas;

    public PaintMouseAdapter(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Rectangle r = new Rectangle();
        r.setX(event.getX());
        r.setY(event.getY());

        rectangle = r;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Rectangle r = rectangle;
        rectangle = null;

        r.setWidth(Math.abs(event.getX() - r.getX()));
        r.setHeight(Math.abs(event.getY() - r.getY()));
        adjustXY(event, r);

        render(r);
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
