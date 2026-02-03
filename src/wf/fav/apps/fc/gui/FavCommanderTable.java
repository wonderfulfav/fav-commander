package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.fs.FavCommanderFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavCommanderTable extends JComponent {

    private static final Color BACKGROUND = Color.BLACK;
    private static final Color FOREGROUND = new Color(0, 127, 0);
    private static final Color CURSOR_BACKGROUND = FOREGROUND;
    private static final Color CURSOR_FOREGROUND = BACKGROUND;
    private static final Color HIGHLIGHTED = Color.GREEN;

    private static final long MAGNITUDE = 1024;
    private static final long KB = MAGNITUDE;
    private static final long MB = KB * MAGNITUDE;
    private static final long GB = MB * MAGNITUDE;
    private static final long TB = GB * MAGNITUDE;
    private static final long PB = TB * MAGNITUDE;

    private static final long KB10 = 10 * KB;
    private static final long MB10 = 10 * MB;
    private static final long GB10 = 10 * GB;
    private static final long TB10 = 10 * TB;

    private static final String SPACE = " ";
    private static final String SPACE2 = SPACE + SPACE;
    private static final String SPACE3 = SPACE2 + SPACE;

    // or make it KiB, MiB, GiB, TiB, PiB?
    private static final String B = SPACE + "B";
    private static final String KIB = SPACE + "K";
    private static final String MIB = SPACE + "M";
    private static final String GIB = SPACE + "G";
    private static final String TIB = SPACE + "T";
    private static final String PIB = SPACE + "P";

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
        return new Dimension(550, 380);
    }

    @Override
    public void paint(final Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(BACKGROUND);
        g.fillRect(0, 0, width, height);

        if (fileList == null || fileList.isEmpty()) {
            return;
        }

        g.setColor(CURSOR_BACKGROUND);
        g.fillRect(0, cursorIndex * 20, width, 20);

        for (int i = 0; i < fileList.size(); i++) {
            g.setColor((i == cursorIndex) ? CURSOR_FOREGROUND : FOREGROUND);
            drawFile(g, i);
        }
    }

    public void drawFile(final Graphics g, final int i) {
        final FavCommanderFile f = fileList.get(i);

        if (f.isDirectory()) {
            g.drawString("<DIR>", 250, i * 20 + 12);
        } else {
            g.drawString(humanReadableSize(f.getFileSize()), 250, i * 20 + 12);
        }

        if (selectedSet.contains(f)) {
            g.setColor(HIGHLIGHTED);
        }

        g.drawString(f.getName(), 10, i * 20 + 12);
    }

    public static String humanReadableSize(final long size) {
        if (size > PB) {
            return leftPad(size / PB) + PIB;
        }
        if (size > TB10) {
            return leftPad(size / TB) + TIB;
        }

        if (size > GB10) {
            return leftPad(size / GB) + GIB;
        }

        if (size > MB10) {
            return leftPad(size / MB) + MIB;
        }

        if (size > KB10) {
            return leftPad(size / KB) + KIB;
        }

        return leftPad(size) + B;
    }

    public static String leftPad(final long size) {
        if (size < 10) {
            return SPACE3 + size;
        }

        if (size < 100) {
            return SPACE2 + size;
        }

        if (size < 1000) {
            return SPACE + size;
        }

        return Long.toString(size);
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
