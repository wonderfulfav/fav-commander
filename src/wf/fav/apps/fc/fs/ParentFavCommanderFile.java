package wf.fav.apps.fc.fs;

import wf.fav.apps.fc.fs.zip.ZipFavCommanderFileSystem;

import java.util.List;

public class ParentFavCommanderFile implements FavCommanderFile {

    private final FavCommanderFile parentDirectory;
    private final ZipFavCommanderFileSystem fileSystem;

    public ParentFavCommanderFile(
            final FavCommanderFile parentDirectory,
            final ZipFavCommanderFileSystem fileSystem) {
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
    public List<? extends FavCommanderFile> listDirectoryFileList() {
        return parentDirectory.listDirectoryFileList();
//        throw new RuntimeException("This is a Parent directory!");
    }

    @Override
    public long getFileSize() {
        throw new RuntimeException("This is a Parent directory!");
    }

    @Override
    public FavCommanderFile getParentDirectory() {
        return parentDirectory;
    }

    @Override
    public ZipFavCommanderFileSystem getFileSystem() {
        return fileSystem;
    }

}
