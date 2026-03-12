package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;
import java.awt.*;

public class FavCommanderMainWindow extends JFrame {

    public FavCommanderMainWindow() {
        super("fav commander");
        setVisible(true);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container p = getContentPane();
        final FavCommanderTableView m = new FavCommanderTableView();
        // BorderLayout is the default layout
        p.add(m, BorderLayout.LINE_START);
        m.grabFocus();
        pack();

        m.getModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());
    }

}
