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

        // BorderLayout is the default layout
        final Container p = getContentPane();

        final FavCommanderTableView vl = new FavCommanderTableView();
        p.add(vl, BorderLayout.LINE_START);
        vl.getModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());
        vl.grabFocus();

        final FavCommanderTableView vr = new FavCommanderTableView();
        p.add(vr, BorderLayout.LINE_END);
        vr.getModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());

        pack();
    }

}
