package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.gui.utils.FavCommanderFormatUtil;

import javax.swing.*;
import java.awt.*;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme.*;
import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.*;

public class FavCommanderTableView extends JComponent {

    private final FavCommanderTableModel model;

    public FavCommanderTableView() {
        model = new FavCommanderTableModel(this);
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

        g.setColor(FOREGROUND);

        for (int i = 0; i < model.getFileListSize(); i++) {
            paintFile(g, i);
        }

        final int cursorIndex = model.getCursorIndex();
        g.setColor(CURSOR_BACKGROUND);
        g.fillRect(0, cursorIndex * LINE_HEIGHT, width, LINE_HEIGHT);
        g.setColor(CURSOR_FOREGROUND);
        paintFile(g, cursorIndex);
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

    public FavCommanderTableModel getModel() {
        return model;
    }

}
