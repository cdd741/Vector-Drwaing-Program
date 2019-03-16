import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

public class ThicknessPanel extends JPanel implements Observer {
    private Model model;
    private  JButton l1 = new JButton("1");
    private  JButton l2 = new JButton("2");
    private  JButton l3 = new JButton("3");
    private  JButton l4 = new JButton("4");

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    public ThicknessPanel(Model model){
        super();
        this.model = model;
        this.layoutView();
        this.registerControllers();
        // Ellipse2D
    }

    private void registerControllers() {
        this.l1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                l1.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setThickness(1);
            }
        });

        this.l2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                l2.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setThickness(3);
            }
        });

        this.l3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                l3.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setThickness(9);
            }
        });

        this.l4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButton();
                l4.setBorder(BorderFactory.createLoweredBevelBorder());
                model.setThickness(27);
            }
        });
    }

    private void resetButton(){
        int t = model.getThickness();
        switch (t){
            case 1: l1.setBorder(BorderFactory.createEmptyBorder());
                break;
            case 3: l2.setBorder(BorderFactory.createEmptyBorder());
                break;
            case 9: l3.setBorder(BorderFactory.createEmptyBorder());
                break;
            case 27: l4.setBorder(BorderFactory.createEmptyBorder());
                break;
            default:
                break;
        }
    }

    private void layoutView(){
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        Dimension dimension = new Dimension(100,35);
        this.setLayout(layout);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(2, 4, 2, 4);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        l1.setPreferredSize(dimension);
        l1.setBorder(BorderFactory.createEmptyBorder());
        //l1.setBorderPainted(false);
        this.add(l1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        l2.setPreferredSize(dimension);
        l2.setBorder(BorderFactory.createEmptyBorder());
        //l2.setBorderPainted(false);
        this.add(l2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        l3.setPreferredSize(dimension);
        l3.setBorder(BorderFactory.createEmptyBorder());
        //l3.setBorderPainted(false);
        this.add(l3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        l4.setPreferredSize(dimension);
        l4.setBorder(BorderFactory.createEmptyBorder());
        //l4.setBorderPainted(false);
        this.add(l4, gbc);
    }

    @Override
    public void update(Observable m, Object what)
    {

        if (((String)what).equals("tool"))
        {
            if (((Model)m).getTool() == "erase" || ((Model)m).getTool() == "fill"){
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
                    l1.setBorder(BorderFactory.createLoweredBevelBorder());
                    ((Model)m).setThickness(1);
                }
        }
    }

}
