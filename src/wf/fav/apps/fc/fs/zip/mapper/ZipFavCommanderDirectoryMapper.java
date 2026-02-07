package wf.fav.apps.fc.fs.zip.mapper;

import wf.fav.apps.fc.fs.zip.ZipFavCommanderDirectoryFile;

import java.util.Collection;
import java.util.HashMap;

public class ZipFavCommanderDirectoryMapper {

    final HashMap<String, ZipFavCommanderDirectoryMapperItem> directoryMap = new HashMap<>();

    public ZipFavCommanderDirectoryMapper() {
        final ZipFavCommanderDirectoryFile rootDirectory = new ZipFavCommanderDirectoryFile("", null);
        final ZipFavCommanderDirectoryMapperItem rootDirectoryMapperItem = new ZipFavCommanderDirectoryMapperItem(rootDirectory);
        directoryMap.put("", rootDirectoryMapperItem);
    }

    public Collection<ZipFavCommanderDirectoryMapperItem> getDirectoryMapValues() {
        return directoryMap.values();
    }

}
