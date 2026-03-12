package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;
import java.awt.*;

public class FavCommanderMainWindow extends JFrame {

    private final FavCommanderTableView leftPanel;

    private final FavCommanderTableView rightPanel;

    public FavCommanderMainWindow() {
        super("fav commander");
        setVisible(true);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout is the default layout
        final Container p = getContentPane();

        leftPanel = new FavCommanderTableView();
        p.add(leftPanel, BorderLayout.LINE_START);

        rightPanel = new FavCommanderTableView();
        p.add(rightPanel, BorderLayout.LINE_END);

        leftPanel.grabFocus();
        pack();
    }

    public FavCommanderTableView getLeftPanel() {
        return leftPanel;
    }

    public FavCommanderTableView getRightPanel() {
        return rightPanel;
    }

}
