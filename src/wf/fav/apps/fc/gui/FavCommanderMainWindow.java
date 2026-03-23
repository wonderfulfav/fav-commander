package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme;
import wf.fav.apps.fc.config.FavCommanderVisualConfigurationLightTheme;
import wf.fav.apps.fc.gui.table.FavCommanderTableModel;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FavCommanderMainWindow extends JFrame {

    private final FavCommanderTableModel leftPanelModel;

    private final FavCommanderTableModel rightPanelModel;

    public FavCommanderMainWindow() {
        super("fav commander");
        setVisible(true);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout is the default layout
        final Container p = getContentPane();

        final JLabel topPanel = new JLabel("moo ha ha");
        p.add(topPanel, BorderLayout.PAGE_START);

        final FavCommanderTableView leftPanel = new FavCommanderTableView();
        p.add(leftPanel, BorderLayout.LINE_START);
        leftPanelModel = leftPanel.getModel();

        final FavCommanderTableView rightPanel = new FavCommanderTableView();
        p.add(rightPanel, BorderLayout.LINE_END);
        rightPanelModel = rightPanel.getModel();

        final FavCommanderVisualConfigurationDarkTheme darkTheme = new FavCommanderVisualConfigurationDarkTheme();
        final FavCommanderVisualConfigurationLightTheme lightTheme = new FavCommanderVisualConfigurationLightTheme();

        final FavCommanderController controller =
                new FavCommanderController(leftPanelModel, rightPanelModel, darkTheme, lightTheme);

        topPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                controller.keyPressedAction(e.getKeyCode());
            }
        });

        topPanel.setFocusTraversalKeysEnabled(false);
        topPanel.grabFocus();
        pack();
    }

    public FavCommanderTableModel getLeftPanelModel() {
        return leftPanelModel;
    }

    public FavCommanderTableModel getRightPanelModel() {
        return rightPanelModel;
    }

}
