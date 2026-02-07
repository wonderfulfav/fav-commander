package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZipFavCommanderDirectoryFile implements FavCommanderFile {

    private final String name;
    private final FavCommanderFile parentDirectory;
    private final List<ZipFavCommanderFile> fileList = new ArrayList<>();
    private final Map<String, ZipFavCommanderDirectoryFile> directoryMap = new HashMap<>();

    public ZipFavCommanderDirectoryFile(final String name, final FavCommanderFile parentDirectory) {
        this.name = name;
        this.parentDirectory = parentDirectory;
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
        return parentDirectory;
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
