package wf.fav.apps.fc;

import wf.fav.apps.fc.gui.FavCommanderMainWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FavCommander {
    static void main() {
        final JFrame f = new JFrame("fav commander");
        f.setVisible(true);
        f.setLocation(100, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final FavCommanderMainWindow m = new FavCommanderMainWindow();
        f.getContentPane().add(m);
//        f.grabFocus();
        f.pack();
        m.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("hello world");
//                    m.shutDown();
                }
            }
        });
        System.out.println("hello world");
    }
}
