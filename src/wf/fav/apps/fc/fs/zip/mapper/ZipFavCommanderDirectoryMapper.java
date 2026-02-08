package wf.fav.apps.fc.fs.zip.mapper;

import wf.fav.apps.fc.fs.zip.ZipFavCommanderDirectoryFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ZipFavCommanderDirectoryMapper {

    private final HashMap<String, ZipFavCommanderDirectoryMapperItem> directoryMap = new HashMap<>();
    private final ZipFavCommanderDirectoryMapperItem rootDirectoryMapperItem;

    public ZipFavCommanderDirectoryMapper() {
        final ZipFavCommanderDirectoryFile rootDirectory = new ZipFavCommanderDirectoryFile("", null);
        rootDirectoryMapperItem = new ZipFavCommanderDirectoryMapperItem(rootDirectory);
        directoryMap.put("", rootDirectoryMapperItem);
    }

    public ZipFavCommanderDirectoryFile getOrCreateDirectory(final String directoryPath) {
        if (directoryMap.containsKey(directoryPath)) {
            return directoryMap.get(directoryPath).getDirectory();
        }

        // create directory / directories
        ZipFavCommanderDirectoryMapperItem currentDirectoryMapperItem = rootDirectoryMapperItem;

        for (int startSlash = 0, nextSlash = directoryPath.indexOf('/'); nextSlash > 0;
             startSlash = nextSlash + 1, nextSlash = directoryPath.indexOf('/', startSlash)) {
            final String nextDirectoryPath = directoryPath.substring(0, nextSlash);

            if (directoryMap.containsKey(nextDirectoryPath)) {
                currentDirectoryMapperItem = directoryMap.get(nextDirectoryPath);
                continue;
            }

            final String nextDirectoryName = nextDirectoryPath.substring(startSlash, nextSlash);
            final  ZipFavCommanderDirectoryFile nextDirectory =
                    new ZipFavCommanderDirectoryFile(nextDirectoryName, currentDirectoryMapperItem.getDirectory());
            final ZipFavCommanderDirectoryMapperItem nextDirectoryMapperItem = new ZipFavCommanderDirectoryMapperItem(nextDirectory);
            directoryMap.put(directoryPath, nextDirectoryMapperItem);
            currentDirectoryMapperItem = nextDirectoryMapperItem;
        }

        return currentDirectoryMapperItem.getDirectory();
    }

    public void printValues() {
        new ArrayList<>(directoryMap.values()).stream().sorted(
                Comparator.comparing(m -> m.getDirectory().getName())
        ).forEach(m -> {
            final ZipFavCommanderDirectoryFile dir = m.getDirectory();
            System.out.println(dir.getName());
            dir.listDirectoryFileList().forEach(
                    f -> System.out.println("\t + " + f.getName()));
        });
    }

}
