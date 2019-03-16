import javax.swing.*;
import java.util.ArrayList;
import java.io.Serializable;

public class Model extends java.util.Observable implements Serializable{

    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    public Drawable dtemp;
    public  boolean btemp = false;
    private int idx = -1;
    private String tool  = "none";
    private String color = "none";
    private int thickness  = 0;
    private int width;
    private int height;

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void setDrawables(ArrayList<Drawable> drawables) {
        this.drawables = (ArrayList) drawables.clone();
        setChanged();
        notifyObservers("reset");
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void reset(int width, int height){
        setWidth(width);
        setHeight(height);
        setChanged();
        notifyObservers("reset");
        System.out.println("OCCURED!");
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Model() { }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
        if(tool != "select"){
            if(idx != -1){
                drawables.get(idx).setSelected(false);
                idx = -1;
                setChanged();
                notifyObservers("set");
            }
        }
        //updateButton();
        setChanged();
        notifyObservers("tool");

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        if(idx != -1 && tool != "fill") {
            drawables.get(idx).setStroke(color);
        }
//        else
//            drawables.get(idx).setFill(color);
        setChanged();
        notifyObservers("set");
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int line) {
        this.thickness = line;
        if(idx != -1){
            drawables.get(idx).setThick(line);
        }
        setChanged();
        notifyObservers("set");
    }

    public void clear()
    {
        drawables.clear();
        setChanged();
        notifyObservers("clear");
    }

}