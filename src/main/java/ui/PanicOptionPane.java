package ui;

import javax.swing.*;

public class PanicOptionPane {
    int a;

    JFrame frame;

    public PanicOptionPane() {
        Object[] options = {"YES", "CANCEL"};
        JOptionPane pane =  new JOptionPane();

        a =pane.showOptionDialog(null,
                "Click YES to panic",
                "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
    }


    public static void main(String[] args) {
        new PanicOptionPane();
    }

    public int getA() {
        return a;
    }
}
