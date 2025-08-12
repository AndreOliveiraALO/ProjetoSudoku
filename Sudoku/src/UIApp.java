import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;

import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;

public class UIApp {

public static void main(String[] args){
    var dimension = new Dimension(800, 600);
    JPanel mainPanel = new MainPanel(dimension);
    JFrame mainFrame = new MainFrame(dimension, mainPanel);
    mainFrame.revalidate();
    mainFrame.repaint();
}

}
