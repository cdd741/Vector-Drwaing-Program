import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class ToolPanel extends JPanel implements Observer {
    private Model model;

    private Icon iMouse = new ImageIcon("mouse.png");
    private Icon iEraser = new ImageIcon("eraser.png");
    private Icon iLine = new ImageIcon("line.png");
    private Icon iCircle = new ImageIcon("circle.png");
    private Icon iRectangle = new ImageIcon("rectangle.png");
    private Icon iPaint = new ImageIcon("paint.png");

    private  JButton none = new JButton("n");
    private  JButton select = new JButton(iMouse);
    private  JButton erase = new JButton(iEraser);
    private  JButton line = new JButton(iLine);
    private  JButton circle = new JButton(iCircle);
    private  JButton rectangle = new JButton(iRectangle);
    private  JButton fill = new JButton(iPaint);
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();


    public ToolPanel(Model model){
        super();
        this.model = model;

        select.setBorder(BorderFactory.createEmptyBorder());
        erase.setBorder(BorderFactory.createEmptyBorder());
        line.setBorder(BorderFactory.createEmptyBorder());
        circle.setBorder(BorderFactory.createEmptyBorder());
        rectangle.setBorder(BorderFactory.createEmptyBorder());
        fill.setBorder(BorderFactory.createEmptyBorder());

        this.layoutView();
        // this.addMouseMotionListener(new MyListener());
        this.registerControllers();



    }
    // From ButtonView.java
    private void registerControllers() {
        this.select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                select.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("select");
            }
        });

        this.erase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                erase.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("erase");
            }
        });

        this.line.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                resetButton();
                line.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("line");
            }
        });

        this.circle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                circle.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("circle");
            }
        });

        this.rectangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                rectangle.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("rectangle");
            }
        });

        this.fill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                fill.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setTool("fill");
            }
        });
    }

    private void resetButton(){
        String t = model.getTool();
        switch (t){
                case "select":    select.setBorder(BorderFactory.createEmptyBorder());
                break;
                case "erase":     erase.setBorder(BorderFactory.createEmptyBorder());
                break;
                case "line":      line.setBorder(BorderFactory.createEmptyBorder());
                break;
                case "circle":    circle.setBorder(BorderFactory.createEmptyBorder());
                break;
                case "rectangle": rectangle.setBorder(BorderFactory.createEmptyBorder());
                break;
                case "fill":      fill.setBorder(BorderFactory.createEmptyBorder());
                break;
            default:
                break;
        }
    }

    private void layoutView(){
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        Dimension dimension = new Dimension(50,50);
        this.setLayout(layout);

        try {

        } catch (Exception ex) {
            System.out.println(ex);
        }

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(2, 2, 2, 2);
        //gbc.gridwidth = 2;
        //gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        select.setPreferredSize(dimension);
        this.add(select, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        erase.setPreferredSize(dimension);
        this.add(erase, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        line.setPreferredSize(dimension);
        this.add(line, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        circle.setPreferredSize(dimension);
        this.add(circle, gbc);

        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rectangle.setPreferredSize(dimension);
        this.add(rectangle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        fill.setPreferredSize(dimension);
        this.add(fill, gbc);
    }

    @Override
    public void update(Observable m, Object what){}
}
