import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class DrawCircle extends Shape implements Serializable {
    Ellipse2D.Double m;

    DrawCircle(Ellipse2D.Double ellip) { m = ellip; }

    @Override
    public void draw(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        if(getSelected()){
            Stroke dashed = new BasicStroke(getThick(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            graphics2D.setStroke(dashed);
        } else
            graphics2D.setStroke(new BasicStroke(getThick()));

        if(isFilled()) {
            graphics2D.setColor(getFill());
            graphics2D.fillArc((int) m.getX(), (int) m.getY(), (int) m.getWidth(), (int) m.getHeight(), 0, 360);
        }
        graphics2D.setColor(getStroke());
        graphics2D.drawArc((int)m.getX(), (int)m.getY(), (int)m.getWidth(), (int)m.getHeight(), 0,360);
    }

    @Override
    public boolean collide(Point p)
    {
        return m.contains(p.getX(), p.getY(), 10, 10);
    }

    @Override
    public void move(int X, int Y){
        m.x = m.getX() + X;
        m.y = m.getY() + Y;
    }
}
