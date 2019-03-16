import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import java.awt.geom.Line2D;
import java.lang.Math.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Board extends JPanel implements Observer{
    Model model;
    private int beginX, beginY;

    public Board(int width, int height, Model model){
        super(true);
        this.model = model;
        model.reset(width, height);
        this.layoutView(width, height);
        var listener = new MyMouseListener();
        this.addMouseMotionListener(listener);
        this.addMouseListener(listener);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == '\u001B')
                {
                    if(model.getIdx() != -1)
                    model.drawables.get(model.getIdx()).setSelected(false);
                    model.setIdx(-1);
                    repaint();
                }
            }
        });
    }

    private class MyMouseListener extends MouseAdapter {

        private int endX, endY;

        public void mouseClicked(MouseEvent e){
            super.mouseClicked(e);
            var tool = model.getTool();
            var color = model.getColor();
            var thick = model.getThickness();
            beginX = e.getX();
            beginY = e.getY();
            switch (tool){
                case "select":
                    boolean found = false;
                    for(int j = model.drawables.size() - 1; j >= 0; j--){
                        if (model.drawables.get(j).collide((new Point(beginX,beginY)))) {
                            if (model.getIdx() != -1) {
                                model.drawables.get(model.getIdx()).setSelected(false);
                            }
                            model.drawables.get(j).setSelected(true);
                            model.setIdx(j);
                            found = true;
                            break;
                        }
                    }
                    if (!found && model.getIdx() != -1) {
                        model.drawables.get(model.getIdx()).setSelected(false);
                        model.setIdx(-1);
                    }
                    repaint();
                    break;
                case "erase":
                    for(int j = model.drawables.size() - 1; j >= 0; j--){
                        if (model.drawables.get(j).collide((new Point(beginX,beginY)))) {
                            model.drawables.remove(j);
                            break;
                        }
                    }
                    break;
                case "line":
                    break;
                case "circle":
                    break;
                case "rectangle":
                    break;
                case "fill":
                    for(int j = model.drawables.size() - 1; j >= 0; j--){
                        if (model.drawables.get(j).collide((new Point(beginX,beginY)))) {
                            model.drawables.get(j).setFilled(true);
                            model.drawables.get(j).setFill(model.getColor());
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }

        public void mousePressed(MouseEvent e){
            super.mousePressed(e);
            requestFocus();
            var tool = model.getTool();
            var color = model.getColor();
            var thick = model.getThickness();
            beginX = e.getX();
            beginY = e.getY();
            switch (tool){
                case "select":
                    if(model.getIdx() != -1)
                    if(model.drawables.get(model.getIdx()).collide((new Point(beginX,beginY)))){
                    model.drawables.get(model.getIdx()).setMoving(true);
                }
                    break;
                case "erase":
                    break;
                case "line":
                    model.dtemp = new DrawLine(new Line2D.Double(beginX, beginY, beginX, beginY));
                    model.btemp = true;
                    model.dtemp.setStroke(model.getColor());
                    model.dtemp.setThick(model.getThickness());
                    break;
                case "circle":
                    model.dtemp = new DrawCircle(new Ellipse2D.Double(beginX, beginY, 0, 0));
                    model.btemp = true;
                    model.dtemp.setStroke(model.getColor());
                    model.dtemp.setThick(model.getThickness());
                    break;
                case "rectangle":
                    model.dtemp = new DrawRectangle(new Rectangle2D.Double(beginX, beginY, 0, 0));
                    model.btemp = true;
                    model.dtemp.setStroke(model.getColor());
                    model.dtemp.setThick(model.getThickness());
                    break;
                case "fill":
                    break;
                default:
                    break;
            }
            repaint();
        }

        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);

            var tool = model.getTool();
            var color = model.getColor();
            var thick = model.getThickness();
            if(!notExceed(e.getX(), e.getY()))
            {
                return;
            }
            endX = e.getX();
            endY = e.getY();


            switch (tool){
                case "select":
                    if(model.getIdx()!=-1)
                        if(model.drawables.get(model.getIdx()).getMoving())
                            model.drawables.get(model.getIdx()).move(endX - beginX,endY - beginY);
                    beginX = endX;
                    beginY = endY;
                    break;
                case "erase":
                    break;
                case "line":
                    model.dtemp = new DrawLine(new Line2D.Double(beginX, beginY, endX, endY));
                    break;
                case "circle":
                    model.dtemp = new DrawCircle(new Ellipse2D.Double(min(beginX, endX),
                            min(beginY, endY), abs(endX-beginX), abs(endY-beginY)));
                    break;
                case "rectangle":
                    model.dtemp = new DrawRectangle(new Rectangle2D.Double(min(beginX, endX),
                            min(beginY, endY), abs(endX-beginX), abs(endY-beginY)));
                    break;
                case "fill":
                    break;
                default:
                    break;
            }
            repaint();
        }

        public void mouseReleased(MouseEvent e){
            Drawable dab;
            super.mousePressed(e);
            //System.out.println("released");
            var tool = model.getTool();
            var color = model.getColor();
            var thick = model.getThickness();
            if(notExceed(e.getX(), e.getY()))
            {
                endX = e.getX();
                endY = e.getY();
            }

            switch (tool){
                case "select":
                    if (model.getIdx()!=-1)
                        model.drawables.get(model.getIdx()).setMoving(false);
                    break;
                case "erase":
                    break;
                case "line":
                    dab = new DrawLine(new Line2D.Double(beginX, beginY, endX, endY));
                    dab.setStroke(model.getColor());
                    dab.setThick(model.getThickness());
                    model.drawables.add(dab);
                    model.btemp = false;
                    break;
                case "circle":
                    dab = new DrawCircle(new Ellipse2D.Double(min(beginX, endX),
                            min(beginY, endY), abs(endX-beginX), abs(endY-beginY)));
                    dab.setStroke(model.getColor());
                    dab.setThick(model.getThickness());
                    model.drawables.add(dab);
                    model.btemp = false;
                    break;
                case "rectangle":
                    dab = new DrawRectangle(new Rectangle2D.Double(min(beginX, endX),
                            min(beginY, endY), abs(endX-beginX), abs(endY-beginY)));
                    dab.setStroke(model.getColor());
                    dab.setThick(model.getThickness());
                    model.drawables.add(dab);
                    model.btemp = false;
                    break;
                case "fill":
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    private void layoutView(int width, int height){
        this.setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
    }

    public void reset(int width, int height){
        layoutView(width, height);
    }

    private boolean notExceed(int X, int Y)
    {
        return (0 < X && X < getPreferredSize().width && 0 < Y && Y < getPreferredSize().height);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        for(var drawable:model.drawables)
            drawable.draw(g);
        if(model.btemp == true)
            model.dtemp.draw(g);
    }

    @Override
    public void update(Observable m, Object what)
    {
        if (((String)what).equals("clear"))
        {
            repaint();
        }
        else if (((String)what).equals("set"))
        {
            repaint();
        }
        else if(((String)what).equals("reset")) {
            reset(model.getWidth(), model.getHeight());
            repaint();
            System.out.println("new Size is" + model.getWidth());
        }
    }

}
