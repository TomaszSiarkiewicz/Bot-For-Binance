package ui;

import bot.Bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SellingDialog implements Runnable{
    private JOptionPane pane;
    private JDialog dialog;
    private Bot bot;

    public SellingDialog(Bot bot) {
        this.bot = bot;
    }

    public void dispose() {
        dialog.dispose();
    }

    @Override
    public void run() {

        pane = new JOptionPane("Selling assets...", 1, JOptionPane.PLAIN_MESSAGE);
        pane.setOptions(new Object[]{});
        pane.setVisible(true);

        dialog = pane.createDialog("");
        dialog.setModalityType(Dialog.ModalityType.MODELESS);
        dialog.setAlwaysOnTop(dialog.isAlwaysOnTopSupported());
        dialog.setDefaultCloseOperation(dialog.DO_NOTHING_ON_CLOSE);
        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Catch ESC key stroke.
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        dialog.setVisible(true);
        while(bot.isPanic()){
            System.out.println("seling");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dispose();

    }
}
