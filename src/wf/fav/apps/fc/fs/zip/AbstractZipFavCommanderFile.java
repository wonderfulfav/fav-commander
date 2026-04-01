package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

public abstract class AbstractZipFavCommanderFile implements FavCommanderFile {

    final String name;
    private final FavCommanderParentDirectory parentDirectory;
    private final FavCommanderFileSystem fileSystem;

    public AbstractZipFavCommanderFile(
            final String name,
            final FavCommanderParentDirectory parentDirectory,
            final FavCommanderFileSystem fileSystem) {
        this.name = name;
        this.parentDirectory = parentDirectory;
        this.fileSystem = fileSystem;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FavCommanderFileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public FavCommanderParentDirectory getParentDirectory() {
        return parentDirectory;
    }

}
