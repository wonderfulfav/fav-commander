package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.gui.utils.FavCommanderFormatUtil;

import javax.swing.*;
import java.awt.*;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.*;

public class FavCommanderTableView extends JComponent {

    private final FavCommanderTableModel model;

    private FavCommanderVisualConfigurationTheme theme;

    public FavCommanderTableView() {
        model = new FavCommanderTableModel(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void setTheme(final FavCommanderVisualConfigurationTheme theme) {
        this.theme = theme;
    }

    @Override
    public void paint(final Graphics g) {
        final int width = getWidth();
        final int height = getHeight();

        g.setColor(theme.getBackgroundColor());
        g.fillRect(0, 0, width, height);

        if (model.isFileListEmpty()) {
            return;
        }

        g.setColor(theme.getForegroundColor());

        for (int i = 0; i < model.getFileListSize(); i++) {
            paintFile(g, i);
        }

        final int cursorIndex = model.getCursorIndex();
        g.setColor(theme.getCursorBackgroundColor());
        g.fillRect(0, cursorIndex * LINE_HEIGHT, width, LINE_HEIGHT);
        g.setColor(theme.getCursorForegroundColor());
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
            g.setColor(theme.getHighlightedColor());
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
