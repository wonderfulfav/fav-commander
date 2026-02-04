package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.fs.FavCommanderFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme.*;
import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.*;

public class FavCommanderTable extends JComponent {

    private List<? extends FavCommanderFile> fileList;
    private final Set<FavCommanderFile> selectedSet = new HashSet<>();
    private int cursorIndex;

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

            case KeyEvent.VK_UP -> {
                if (cursorIndex > 0) {
                    cursorIndex--;
                    repaint();
                }
            }

            case KeyEvent.VK_DOWN -> {
                if (fileList != null && cursorIndex < fileList.size() - 1) {
                    cursorIndex++;
                    repaint();
                }
            }

            case KeyEvent.VK_INSERT -> {
                final FavCommanderFile selectedFile = fileList.get(cursorIndex);

                if (selectedSet.contains(selectedFile)) {
                    selectedSet.remove(selectedFile);
                } else {
                    selectedSet.add(selectedFile);
                }

                if (fileList != null && cursorIndex < fileList.size() - 1) {
                    cursorIndex++;
                }

                repaint();
            }

            default -> System.out.println(keyCode);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void paint(final Graphics g) {
        final int width = getWidth();
        final int height = getHeight();

        g.setColor(BACKGROUND);
        g.fillRect(0, 0, width, height);

        if (fileList == null || fileList.isEmpty()) {
            return;
        }

        g.setColor(CURSOR_BACKGROUND);
        g.fillRect(0, cursorIndex * LINE_HEIGHT, width, LINE_HEIGHT);

        for (int i = 0; i < fileList.size(); i++) {
            g.setColor((i == cursorIndex) ? CURSOR_FOREGROUND : FOREGROUND);
            paintFile(g, i);
        }
    }

    public void paintFile(final Graphics g, final int i) {
        final FavCommanderFile f = fileList.get(i);

        if (f.isDirectory()) {
            g.drawString("<DIR>", TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        } else {
            g.drawString(FavCommanderFormatUtil.humanReadableSize(f.getFileSize()), TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        }

        if (selectedSet.contains(f)) {
            g.setColor(HIGHLIGHTED);
        }

        g.drawString(f.getName(), LEFT_MARGIN, i * LINE_HEIGHT + TEXT_OFFSET);
    }

    @Override
    public void update(final Graphics g) {
        paint(g);
    }

    public void setFileList(final List<? extends FavCommanderFile> fileList) {
        this.fileList = fileList;
        selectedSet.clear();
        repaint();
    }

}
