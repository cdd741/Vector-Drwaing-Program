import java.awt.*;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Shape implements Drawable, Serializable {
    private Color stroke;
    private Color fill;
    private boolean filled;
    private boolean selected = false;
    private boolean moving = false;
    private int thick;

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public Color getStroke() {
        return stroke;
    }

    @Override
    public void setStroke(String stroke) {
        switch (stroke){
            case "red": this.stroke = Color.RED;
                break;
            case "yellow": this.stroke = Color.YELLOW;
                break;
            case "blue": this.stroke = Color.BLUE;
                break;
            case "green": this.stroke = Color.GREEN;
                break;
            case "black": this.stroke = Color.BLACK;
                break;
            case "white": this.stroke = Color.WHITE;
                break;
            default:
                break;
        }
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public Color getFill() {
        return fill;
    }

    @Override
    public void setFill(String fill) {

        switch (fill){
            case "red": this.fill = Color.RED;
                break;
            case "yellow": this.fill = Color.YELLOW;
                break;
            case "blue": this.fill = Color.BLUE;
                break;
            case "green": this.fill = Color.GREEN;
                break;
            case "black": this.fill = Color.BLACK;
                break;
            case "white": this.fill = Color.WHITE;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean getSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int getThick() {
        return thick;
    }

    @Override
    public void setThick(int thick) {
        this.thick = thick;
    }

    @Override
    public void fillColor(){}

    // @Override
    // public void setLine(Line2D line){}
}
