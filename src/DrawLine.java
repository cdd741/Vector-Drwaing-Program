import java.awt.*;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class DrawLine extends Shape implements Serializable {
    Line2D m;

    DrawLine(Line2D line) {
        m = line;
    }

    @Override
    public void draw(Graphics g)
    {


        // System.out.println("hi");
        Graphics2D graphics2D = (Graphics2D)g;
        if(getSelected()){
            Stroke dashed = new BasicStroke(getThick(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            graphics2D.setStroke(dashed);
        } else
            graphics2D.setStroke(new BasicStroke(getThick()));
        graphics2D.setColor(getStroke());
        graphics2D.drawLine((int)m.getX1(), (int)m.getY1(), (int)m.getX2(), (int)m.getY2());
    }

//    @Override
//    public void setLine(Line2D line){ this.m = line; }

    @Override
    public boolean collide(Point p)
    {
        return m.ptSegDist(p) < 10;
    }

    @Override
    public void move(int X, int Y){
        m.setLine(m.getX1()+X, m.getY1()+Y, m.getX2()+X, m.getY2()+Y);
    }
}
