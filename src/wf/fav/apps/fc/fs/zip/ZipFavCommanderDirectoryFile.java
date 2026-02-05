package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.util.ArrayList;
import java.util.List;

public class ZipFavCommanderDirectoryFile implements FavCommanderFile {

    private final String name;
    private final List<ZipFavCommanderFile> fileList = new ArrayList<>();

    public ZipFavCommanderDirectoryFile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public FavCommanderFile getParentDirectory() {
        return null;
    }

    @Override
    public FavCommanderFileSystem getFileSystem() {
        return null;
    }

    @Override
    public List<? extends FavCommanderFile> listDirectoryFileList() {
        return fileList;
    }

    @Override
    public long getFileSize() {
        throw new RuntimeException("Not a ZIP direcotry: " + getName());
    }

    public void addZipFile(ZipFavCommanderFile file) {
        fileList.add(file);
    }

}
