package wf.fav.apps.fc;

import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;

import javax.swing.*;
import java.io.File;

public class FavCommander {
    static void main() {
        final JFrame f = new JFrame("fav commander");
        f.setVisible(true);
        f.setLocation(100, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new File(System.getProperty("user.dir"));
//        final FavCommanderMainWindow m = new FavCommanderMainWindow();
        final FavCommanderTableView m = new FavCommanderTableView();
        f.getContentPane().add(m);
        m.grabFocus();
        f.pack();

        m.getModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());
    }

}
