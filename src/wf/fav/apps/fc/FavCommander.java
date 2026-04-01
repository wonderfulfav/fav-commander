package wf.fav.apps.fc;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.FavCommanderLocalFile;
import wf.fav.apps.fc.fs.local.FavCommanderLocalFileSystem;
import wf.fav.apps.fc.gui.FavCommanderMainWindow;

import java.io.File;

public class FavCommander {

    static void main() {
        FavCommanderLocalFile userDir = new FavCommanderLocalFile(
                new File(System.getProperty("user.dir")));
        final FavCommanderMainWindow m = new FavCommanderMainWindow();
        final FavCommanderFile root = FavCommanderLocalFileSystem
                .getLocalFavCommanderFileSystemInstance().getRootList().getFirst();
        m.getLeftPanelModel().setCurrentDirectory(root);
        m.getRightPanelModel().setCurrentDirectory(userDir);
    }

}
