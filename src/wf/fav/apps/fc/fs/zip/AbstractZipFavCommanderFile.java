package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;

public abstract class AbstractZipFavCommanderFile implements FavCommanderFile {

    final String name;
    private final FavCommanderFile parentDirectory;

    public AbstractZipFavCommanderFile(final String name, final FavCommanderFile parentDirectory) {
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
