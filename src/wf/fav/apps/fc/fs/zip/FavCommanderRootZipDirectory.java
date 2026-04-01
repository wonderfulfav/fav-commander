package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFileSystem;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

public class FavCommanderRootZipDirectory extends AbstractFavCommanderZipDirectory {

    public FavCommanderRootZipDirectory(
            final String name,
            final FavCommanderParentDirectory parentDirectory,
            final FavCommanderFileSystem fileSystem) {
        super(name, parentDirectory, fileSystem);
    }

}
