import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class DrawRectangle extends Shape implements Serializable {
    Rectangle2D m;

    DrawRectangle(Rectangle2D rec) {m = rec;}

    @Override
    public void draw(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        if(getSelected()){
            Stroke dashed = new BasicStroke(getThick(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            graphics2D.setStroke(dashed);
        } else
            graphics2D.setStroke(new BasicStroke(getThick()));
        graphics2D.setPaint(getStroke());
        if(isFilled()) {
            graphics2D.setColor(getFill());
            graphics2D.fillRect((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight());
        }
        graphics2D.setColor(getStroke());
        graphics2D.drawRect((int)m.getX(), (int)m.getY(), (int)m.getWidth(), (int)m.getHeight());
    }

    @Override
    public boolean collide(Point p)
    {
        return m.contains(p.getX(), p.getY(), 10, 10);
    }

    @Override
    public void move(int X, int Y){
        m.setRect(m.getX() + X, m.getY() + Y, m.getWidth(), m.getHeight());
    }
}
