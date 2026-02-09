package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFavCommanderFileSystem implements FavCommanderFileSystem {

    private final HashMap<String, ZipFavCommanderDirectoryFile> directoryMap = new HashMap<>();
    final ZipFavCommanderDirectoryFile rootDirectory;

    public ZipFavCommanderFileSystem(final FavCommanderFile parentFile) {
        rootDirectory = new ZipFavCommanderDirectoryFile("", parentFile);
        directoryMap.put("", rootDirectory);

        try (final ZipFile zipFile = new ZipFile("/test.zip")) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                final String name = entry.getName();

                final int lastSlash = name.lastIndexOf('/');

                // find or create the parent directory
                final String directoryPath = name.substring(0, lastSlash + 1);
                final ZipFavCommanderDirectoryFile directory = getOrCreateDirectory(directoryPath);

                if (entry.isDirectory()) {
                    continue;
                }

                // create the file
                final String fileName = (lastSlash < 0) ? name : name.substring(lastSlash + 1);
                final ZipFavCommanderFile file = new ZipFavCommanderFile(fileName, directory, entry);

                // add the file to the directory
                directory.addZipFile(file);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        printValues();
    }

    @Override
    public List<? extends FavCommanderFile> listRoots() {
        return List.of();
    }

    public ZipFavCommanderDirectoryFile getOrCreateDirectory(final String directoryPath) {
        if (directoryMap.containsKey(directoryPath)) {
            return directoryMap.get(directoryPath);
        }

        // create directory / directories
        ZipFavCommanderDirectoryFile currentDirectory = rootDirectory;

        for (int startSlash = 0, nextSlash = directoryPath.indexOf('/'); nextSlash > 0;
             startSlash = nextSlash + 1, nextSlash = directoryPath.indexOf('/', startSlash)) {
            final String nextDirectoryPath = directoryPath.substring(0, nextSlash + 1);

            if (directoryMap.containsKey(nextDirectoryPath)) {
                currentDirectory = directoryMap.get(nextDirectoryPath);
                continue;
            }

            final String nextDirectoryName = nextDirectoryPath.substring(startSlash, nextSlash);
            final  ZipFavCommanderDirectoryFile nextDirectory =
                    new ZipFavCommanderDirectoryFile(nextDirectoryName, currentDirectory);
            directoryMap.put(nextDirectoryPath, nextDirectory);
            currentDirectory.addZipFile(nextDirectory);
            currentDirectory = nextDirectory;
        }

        return currentDirectory;
    }

    public void printValues() {
        new ArrayList<>(directoryMap.values()).stream().sorted(
                Comparator.comparing(AbstractZipFavCommanderFile::getName)
        ).forEach(dir -> {
            System.out.println(dir.getName());
            dir.listDirectoryFileList().forEach(
                    f -> System.out.println("\t + " + f.getName()));
        });
    }

}
