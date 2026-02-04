package wf.fav.apps.fc;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.table.FavCommanderTableView;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        List<? extends FavCommanderFile> fileList = new ArrayList<>(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst().listDirectoryFileList());
        fileList.sort(FavCommanderFileComparator.NAME);
        m.setFileList(fileList);
    }

}
