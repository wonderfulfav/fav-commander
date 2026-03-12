package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;

public class FavCommanderMainWindow extends JFrame {

    public FavCommanderMainWindow() {
        super("fav commander");
        setVisible(true);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final FavCommanderTableView m = new FavCommanderTableView();
        getContentPane().add(m);
        m.grabFocus();
        pack();

        m.getModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());
    }

}
