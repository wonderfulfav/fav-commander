package wf.fav.apps.fc;

import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.FavCommanderMainWindow;

import java.io.File;

public class FavCommander {
    static void main() {
        new File(System.getProperty("user.dir"));
        final FavCommanderMainWindow m = new FavCommanderMainWindow();
        m.getLeftPanelModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());

        m.getRightPanelModel().setCurrentDirectory(
                LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance()
                        .listRoots().getFirst());
    }

}
