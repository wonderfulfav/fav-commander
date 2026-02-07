package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZipFavCommanderDirectoryFile extends AbstractZipFavCommanderFile {

    private final List<ZipFavCommanderFile> fileList = new ArrayList<>();
    private final Map<String, ZipFavCommanderDirectoryFile> directoryMap = new HashMap<>();

    public ZipFavCommanderDirectoryFile(final String name, final FavCommanderFile parentDirectory) {
        super(name, parentDirectory);
    }

    @Override
    public boolean isDirectory() {
        return true;
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
        throw new RuntimeException("This is a ZIP direcotry: " + getName());
    }

    public void addZipFile(ZipFavCommanderFile file) {
        fileList.add(file);
    }

}
