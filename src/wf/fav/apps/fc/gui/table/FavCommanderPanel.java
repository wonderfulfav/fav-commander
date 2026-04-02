package wf.fav.apps.fc.gui.table;

import javax.swing.*;
import java.awt.*;

public class FavCommanderPanel extends JPanel {

    private final FavCommanderTableView tableView;
    private final JLabel topPanel;
    private final JScrollBar scrollBar;

    public FavCommanderPanel() {
        tableView = new FavCommanderTableView();

        setLayout(new BorderLayout());
        add(tableView, BorderLayout.CENTER);

        topPanel = new JLabel("fav top");
        add(topPanel, BorderLayout.PAGE_START);
        getModel().setTopPanel(topPanel);

        scrollBar = new JScrollBar();
        add(scrollBar, BorderLayout.LINE_END);
    }

    public FavCommanderTableModel getModel() {
        return tableView.getModel();
    }

    public JLabel getTopPanel() {
        return topPanel;
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }

}
