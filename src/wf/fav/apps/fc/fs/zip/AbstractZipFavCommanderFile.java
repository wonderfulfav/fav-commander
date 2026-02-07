package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;

public abstract class AbstractZipFavCommanderFile implements FavCommanderFile {

    final String name;
    private final ZipFavCommanderDirectoryFile parentDirectory;

    public AbstractZipFavCommanderFile(final String name, final ZipFavCommanderDirectoryFile parentDirectory) {
        this.name = name;
        this.parentDirectory = parentDirectory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FavCommanderFile getParentDirectory() {
        return parentDirectory;
    }

}
