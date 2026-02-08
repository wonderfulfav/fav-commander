package wf.fav.apps.fc.fs.zip.mapper;

import wf.fav.apps.fc.fs.zip.ZipFavCommanderDirectoryFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ZipFavCommanderDirectoryMapper {

    final HashMap<String, ZipFavCommanderDirectoryMapperItem> directoryMap = new HashMap<>();

    public ZipFavCommanderDirectoryMapper() {
        final ZipFavCommanderDirectoryFile rootDirectory = new ZipFavCommanderDirectoryFile("", null);
        final ZipFavCommanderDirectoryMapperItem rootDirectoryMapperItem = new ZipFavCommanderDirectoryMapperItem(rootDirectory);
        directoryMap.put("", rootDirectoryMapperItem);
    }

    public ZipFavCommanderDirectoryFile getOrCreateDirectory(String directoryPath) {
        if (directoryMap.containsKey(directoryPath)) {
            return directoryMap.get(directoryPath).getDirectory();
        }

        // create directory / directories
        ZipFavCommanderDirectoryMapper currentDirectoryMapper = rootDirectoryMapper;

        for (int startSlash = 0, nextSlash = name.indexOf('/', startSlash);
             nextSlash > 0;) {
            final String nextDirectoryPath = name.substring(0, nextSlash);

            if (directoryMap.containsKey(nextDirectoryPath)) {
                currentDirectoryMapper = directoryMap.get(nextDirectoryPath);
            } else {
                String nextDirectoryName = name.substring(startSlash, nextSlash);
                new ZipFavCommanderDirectoryFile(nextDirectoryName, currentDirectoryMapper.getDirectory());
                directoryMap.put(directoryPath, directory);
                directory = currentDirectory.getDirectory();
                ;
            }
        }

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
