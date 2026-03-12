package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.gui.table.FavCommanderTableModel;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        final JLabel topPanel = new JLabel("moo ha ha");
        p.add(topPanel, BorderLayout.PAGE_START);

        leftPanel = new FavCommanderTableView();
        p.add(leftPanel, BorderLayout.LINE_START);

        rightPanel = new FavCommanderTableView();
        p.add(rightPanel, BorderLayout.LINE_END);

        final FavCommanderController controller = new FavCommanderController(getLeftPanelModel(), getRightPanelModel());

        topPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                controller.keyPressedAction(e.getKeyCode());
            }
        });

        topPanel.grabFocus();
        pack();
    }

    public FavCommanderTableModel getLeftPanelModel() {
        return leftPanel.getModel();
    }

    public FavCommanderTableModel getRightPanelModel() {
        return rightPanel.getModel();
    }

}
