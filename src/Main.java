import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import java.io.File;


public class Main {
    private static JFrame frame;
    private static JPanel sidebar;
    private static Board vBoard;
    private static JMenuItem New;
    private static JMenuItem Open;
    private static JMenuItem Save;
    private static Model model;
    public static void main(String[] args) {
        // pane
        JPanel panel = new JPanel(new GridBagLayout());
        sidebar = new JPanel();


        sidebar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        // scrollpane
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED ;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED ;
        JScrollPane scrollPane = new JScrollPane(panel, v, h);
        // Model
        model = new Model();

        // Frame
        frame = new JFrame("Paint");
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        ToolPanel vTool = new ToolPanel(model);
        model.addObserver(vTool);
        ColorPanel vColor = new ColorPanel(model);
        model.addObserver(vColor);
        ThicknessPanel vLine = new ThicknessPanel(model);
        model.addObserver(vLine);



        // Layout
        // gbc.anchor = GridBagConstraints.PAGE_START;
        sidebar.setLayout(layout);
        // gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        // Sidebar
        gbc.gridx = 0;
        gbc.gridy = 0;
        sidebar.add(vTool, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        sidebar.add(vColor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        sidebar.add(vLine, gbc);
        // pane layout
        gbc.weighty = 0; // ?????
        gbc.weightx = 0; // ?????
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(sidebar,gbc);


        // Menubar
        // file.addSeparator();
        JMenuBar jmb = new JMenuBar();
        JMenu file = new JMenu("File");
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        frame.setJMenuBar(jmb);
        jmb.add(file);
        file.add(New);
        file.add(Open);
        file.add(Save);
        registerControllers();


//        model.drawables.add(new DrawLine(new Line2D.Double(10, 50, 100, 100)));
        frame.add(scrollPane);
        frame.pack();
        frame.setSize(540, 580);
        frame.setMinimumSize(new Dimension(540, 580));

        //// Canvas ////
        gbc.weighty = 1; // ?????
        gbc.weightx = 1; // ?????
        gbc.gridx = 1;
        gbc.gridy = 0;
        vBoard = new Board(frame.getWidth() - sidebar.getWidth(), frame.getHeight(), model);
        //model.reset();
        // model.reset(440, 580);
        model.addObserver(vBoard);
        panel.add(vBoard,gbc);
        ////////////////

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void registerControllers() {
        New.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                model.reset(frame.getWidth() - sidebar.getWidth(), frame.getHeight());
                model.clear();
            }
        });
        Open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                    final JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(frame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //This is where a real application would open the file.


                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Model result = (Model) ois.readObject();
                        ois.close();

                        model.setIdx(-1);
                        model.reset(result.getWidth(), result.getHeight());
                        model.setDrawables(result.getDrawables());
                        System.out.println(result.getWidth());
                    }
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }



//                vBoard.reset(frame.getWidth() - sidebar.getWidth(), frame.getHeight());
            }
        });
        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try{
                    final JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showSaveDialog(frame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //This is where a real application would open the file.


                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(model);
                        oos.close();
                        System.out.println(model.getWidth());
                    }
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
