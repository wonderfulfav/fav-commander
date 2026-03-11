package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;

public abstract class AbstractZipFavCommanderFile implements FavCommanderFile {

    final String name;
    private final FavCommanderFile parentDirectory;
    private final ZipFavCommanderFileSystem fileSystem;

    public AbstractZipFavCommanderFile(final String name,
                                       final FavCommanderFile parentDirectory,
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
    public FavCommanderFile getParentDirectory() {
        return parentDirectory;
    }

}
