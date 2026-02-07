package wf.fav.apps.fc.fs.zip.mapper;

import wf.fav.apps.fc.fs.zip.ZipFavCommanderDirectoryFile;

import java.util.HashMap;
import java.util.Map;

public class ZipFavCommanderDirectoryMapperItem {

    private final Map<String, ZipFavCommanderDirectoryFile> directoryMap = new HashMap<>();
    private final ZipFavCommanderDirectoryFile directory;

    public ZipFavCommanderDirectoryMapperItem(final ZipFavCommanderDirectoryFile directory) {
        this.directory = directory;
    }

    public ZipFavCommanderDirectoryFile getDirectory() {
        return directory;
    }

}
