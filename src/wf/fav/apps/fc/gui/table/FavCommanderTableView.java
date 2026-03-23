package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.gui.utils.FavCommanderFormatUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.*;

public class FavCommanderTableView extends JComponent {

    private final FavCommanderTableModel model;

    private FavCommanderVisualConfigurationTheme theme;

    public FavCommanderTableView() {
        model = new FavCommanderTableModel(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setCursorIndex(e.getY() / LINE_HEIGHT);
            }
        });
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
        g.setColor(theme.getBackgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        if (model.isFileListEmpty()) {
            return;
        }

        paintFileList(g);

        if (model.isActive()) {
            paintCursorFile(g);
        }
    }

    public void paintFile(final Graphics g, final int i) {
        final FavCommanderFile f = model.getFile(i);

        if (f.isDirectory()) {
            g.drawString("<DIR>", TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        } else {
            g.drawString(FavCommanderFormatUtil.humanReadableSize(f.getFileSize()), TAB_OFFSET, i * LINE_HEIGHT + TEXT_OFFSET);
        }

        g.drawString(f.getName(), LEFT_MARGIN, i * LINE_HEIGHT + TEXT_OFFSET);
    }

    public void paintFileList(final Graphics g) {
        for (int i = 0; i < model.getFileListSize(); i++) {
            g.setColor(model.selectedSetContains(model.getFile(i)) ?
                    theme.getHighlightedColor() : theme.getForegroundColor());
            paintFile(g, i);
        }
    }

    public void paintCursorFile(final Graphics g) {
        final int cursorIndex = model.getCursorIndex();
        g.setColor(theme.getCursorBackgroundColor());
        g.fillRect(0, cursorIndex * LINE_HEIGHT, getWidth(), LINE_HEIGHT);
        g.setColor(model.selectedSetContains(model.getFile(cursorIndex)) ?
                theme.getHighlightedColor() : theme.getCursorForegroundColor());
        paintFile(g, cursorIndex);
    }

    @Override
    public void update(final Graphics g) {
        paint(g);
    }

    public FavCommanderTableModel getModel() {
        return model;
    }

}
