package wf.fav.apps.fc;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.gui.FavCommanderMainWindow;

import java.io.File;

public class FavCommander {

    static void main() {
        LocalFavCommanderFile userDir = new LocalFavCommanderFile(
                new File(System.getProperty("user.dir")));
        final FavCommanderMainWindow m = new FavCommanderMainWindow();
        final FavCommanderFile root = LocalFavCommanderFileSystem
                .getLocalFavCommanderFileSystemInstance().listRoots().getFirst();
        m.getLeftPanelModel().setCurrentDirectory(root);
        m.getRightPanelModel().setCurrentDirectory(userDir);
    }

}
