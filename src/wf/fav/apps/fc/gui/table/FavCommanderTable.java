package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.gui.FavCommanderFormatUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme.*;
import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.*;

public class FavCommanderTable extends JComponent {

    private final FavCommanderTableModel model;

    public FavCommanderTable() {
        model = new FavCommanderTableModel();
        final FavCommanderTableController controller =
                new FavCommanderTableController(this, model);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                controller.keyPressedAction(e.getKeyCode());
            }
        });
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

        if (model.isFileListEmpty()) {
            return;
        }

        g.setColor(CURSOR_BACKGROUND);
        g.fillRect(0, model.getCursorIndex() * LINE_HEIGHT, width, LINE_HEIGHT);

        for (int i = 0; i < model.getFileListSize(); i++) {
            g.setColor((i == model.getCursorIndex()) ? CURSOR_FOREGROUND : FOREGROUND);
            paintFile(g, i);
        }
    }

    public void paintFile(final Graphics g, final int i) {
        final FavCommanderFile f = model.getFile(i);

        if (f.isDirectory()) {
            g.drawString("<DIR>", TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        } else {
            g.drawString(FavCommanderFormatUtil.humanReadableSize(f.getFileSize()), TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        }

        if (model.selectedSetContains(f)) {
            g.setColor(HIGHLIGHTED);
        }

        g.drawString(f.getName(), LEFT_MARGIN, i * LINE_HEIGHT + TEXT_OFFSET);
    }

    @Override
    public void update(final Graphics g) {
        paint(g);
    }

    public void setFileList(final List<? extends FavCommanderFile> fileList) {
        model.setFileList(fileList);
        repaint();
    }

}
