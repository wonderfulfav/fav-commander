package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.ParentFavCommanderFile;

public abstract class AbstractZipFavCommanderFile implements FavCommanderFile {

    final String name;
    private final ParentFavCommanderFile parentDirectory;
    private final ZipFavCommanderFileSystem fileSystem;

    public AbstractZipFavCommanderFile(
            final String name,
            final ParentFavCommanderFile parentDirectory,
            final ZipFavCommanderFileSystem fileSystem) {
        this.name = name;
        this.parentDirectory = parentDirectory;
        this.fileSystem = fileSystem;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ZipFavCommanderFileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public ParentFavCommanderFile getParentDirectory() {
        return parentDirectory;
    }

}
