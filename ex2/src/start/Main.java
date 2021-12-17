package start;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());

    }

    private static void createAndShowGUI() {

        JFrame frame = new Application();
        frame.setVisible(true);

    }

}
