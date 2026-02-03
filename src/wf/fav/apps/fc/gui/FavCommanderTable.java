package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.fs.FavCommanderFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class FavCommanderTable extends JComponent {

    private static final Color BACKGROUND = Color.BLACK;
    private static final Color FOREGROUND = new Color(0, 127, 0);
    private static final Color CURSOR_BACKGROUND = FOREGROUND;
    private static final Color CURSOR_FOREGROUND = BACKGROUND;
    private static final Color HIGHLIGHTED = Color.GREEN;

    private List<? extends FavCommanderFile> fileList;

    public FavCommanderTable() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                keyPressedAction(e.getKeyCode());
            }
        });
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE -> System.exit(0);
            default -> System.out.println(keyCode);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(550, 350);
    }

    @Override
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, width, height);

        if (fileList == null || fileList.isEmpty()) {
            return;
        }

        g.setColor(FOREGROUND);

        for (int i = 0; i < fileList.size(); i++) {
            FavCommanderFile f = fileList.get(i);
            g.drawString(f.getName(), 10, i * 20);

            if (f.isDirectory()) {
                g.drawRect(5, i * 20, 3, 3);
            } else {
                g.drawString(Long.toString(f.getFileSize()), 250, i * 20);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void setFileList(List<? extends FavCommanderFile> fileList) {
        this.fileList = fileList;
        repaint();
    }

}
