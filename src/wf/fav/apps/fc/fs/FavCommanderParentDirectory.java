package wf.fav.apps.fc.fs;

import java.util.List;

public class FavCommanderParentDirectory implements FavCommanderFile {

    private final FavCommanderFile parentDirectory;
    private final FavCommanderFileSystem fileSystem;

    public FavCommanderParentDirectory(
            final FavCommanderFile parentDirectory,
            final FavCommanderFileSystem fileSystem) {
        this.parentDirectory = parentDirectory;
        this.fileSystem = fileSystem;
    }

    @Override
    public String getName() {
        return "..";
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public List<? extends FavCommanderFile> getDirectoryFileList() {
        return parentDirectory.getDirectoryFileList();
    }

    @Override
    public long getFileSize() {
        throw new RuntimeException("This is a Parent directory!");
    }

    @Override
    public FavCommanderParentDirectory getParentDirectory() {
        throw new RuntimeException("This is a Parent directory!");
    }

    @Override
    public FavCommanderFileSystem getFileSystem() {
        return fileSystem;
    }

}
