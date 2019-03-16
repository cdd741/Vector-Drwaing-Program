import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ColorPanel extends JPanel implements Observer {
    private Model model;
    private  JButton red    = new JButton();
    private  JButton yellow = new JButton();
    private  JButton blue   = new JButton();
    private  JButton green  = new JButton();
    private  JButton black  = new JButton();
    private  JButton white  = new JButton();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
                            
    public ColorPanel(Model model){
        super();
        this.model = model;
        this.layoutView();
        this.registerControllers();
    }

    private void registerControllers() {
        this.red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                red.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("red");
            }
        });

        this.yellow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                yellow.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("yellow");
            }
        });

        this.blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                blue.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("blue");
            }
        });

        this.green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                green.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("green");
            }
        });

        this.black.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                black.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("black");
            }
        });

        this.white.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                white.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setColor("white");
            }
        });
    }

    private void resetButton(){
        String t = model.getColor();
        switch (t){
            case "red": red.setBorder(BorderFactory.createEmptyBorder());
                break;
            case "yellow": yellow.setBorder(BorderFactory.createEmptyBorder());
                break;
            case "blue": blue.setBorder(BorderFactory.createEmptyBorder());
                break;
            case "green": green.setBorder(BorderFactory.createEmptyBorder());
                break;
            case "black": black.setBorder(BorderFactory.createEmptyBorder());
                break;
            case "white": white.setBorder(BorderFactory.createEmptyBorder());
                break;
            default:
                break;
        }
    }

    private void layoutView(){
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));        Dimension dimension = new Dimension(50,50);
        this.setLayout(layout);

        red.setBackground(Color.RED);
        yellow.setBackground(Color.YELLOW);
        blue.setBackground(Color.BLUE);
        green.setBackground(Color.GREEN);
        black.setBackground(Color.BLACK);
        white.setBackground(Color.WHITE);
        red.setOpaque(true);
        yellow.setOpaque(true);
        blue.setOpaque(true);
        green.setOpaque(true);
        black.setOpaque(true);
        white.setOpaque(true);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(2, 2, 2, 2);
        //gbc.gridwidth = 2;
        //gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // c1
        gbc.gridx = 0;
        gbc.gridy = 0;
        red.setPreferredSize(dimension);
        red.setBorder(BorderFactory.createEmptyBorder());
        this.add(red, gbc);
        // c2
        gbc.gridx = 1;
        gbc.gridy = 0;
        yellow.setPreferredSize(dimension);
        yellow.setBorder(BorderFactory.createEmptyBorder());
        this.add(yellow, gbc);
        // c3
        gbc.gridx = 0;
        gbc.gridy = 1;
        blue.setPreferredSize(dimension);
        blue.setBorder(BorderFactory.createEmptyBorder());
        this.add(blue, gbc);
        // c4
        gbc.gridx = 1;
        gbc.gridy = 1;
        green.setPreferredSize(dimension);
        green.setBorder(BorderFactory.createEmptyBorder());
        this.add(green, gbc);
        // c5
        gbc.gridx = 0;
        gbc.gridy = 2;
        black.setPreferredSize(dimension);
        black.setBorder(BorderFactory.createEmptyBorder());
        this.add(black, gbc);
        // c6
        gbc.gridx = 1;
        gbc.gridy = 2;
        white.setPreferredSize(dimension);
        white.setBorder(BorderFactory.createEmptyBorder());
        this.add(white, gbc);
    }

    @Override
    public void update(Observable m, Object what)
    {
        if (((String)what).equals("tool"))
        {
            if (((Model)m).getTool() == "erase"){
                for(var cp: getComponents())
                    cp.setEnabled(false);
            }
            else
                for(var cp: getComponents())
                    cp.setEnabled(true);

            if (((Model)m).getTool() == "line" ||
                    ((Model)m).getTool() == "circle" ||
                    ((Model)m).getTool() == "rectangle")
                if(((Model)m).getColor() == "none"){
                    black.setBorder(BorderFactory.createLoweredBevelBorder());
                    ((Model)m).setColor("black");
                }


        }

    }


}
