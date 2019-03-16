import java.awt.*;
import java.awt.geom.Line2D;

public interface Drawable {
    default void draw(Graphics g){}
    default boolean collide(Point p) { return false; }

    Color getStroke();
    void setStroke(String color);

    Color getFill();
    void setFill(String fill);

    int getThick();
    void setThick(int thick);

    boolean getSelected();
    void setSelected(boolean selected);

    void fillColor();

    boolean getMoving();
    void setMoving(boolean moving);

    boolean isFilled();
    void setFilled(boolean filled);

//    void setLine(Line2D line);
    default void move(int X, int Y) {}
}
