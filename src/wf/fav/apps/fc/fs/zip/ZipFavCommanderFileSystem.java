package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFavCommanderFileSystem implements FavCommanderFileSystem {

    public static void openZipFile(final FavCommanderFile file) {
    }

    public static void main(String[] args) {
        final HashMap<String, ZipFavCommanderDirectoryFile> directoryMap = new HashMap<>();

        try (final ZipFile zipFile = new ZipFile("/test.zip")) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                final String name = entry.getName();

                if (entry.isDirectory()) {
                    final ZipFavCommanderDirectoryFile directory = new ZipFavCommanderDirectoryFile(name);
                    directoryMap.put(name, directory);
                } else {
                    final int lastSlash = name.lastIndexOf('/');

                    // find or create the parent directory
                    final String directoryName = name.substring(0, lastSlash + 1);

                    if (!directoryMap.containsKey(directoryName)) {
                        directoryMap.put(directoryName, new ZipFavCommanderDirectoryFile(directoryName));
                    }

                    final ZipFavCommanderDirectoryFile directory = directoryMap.get(directoryName);

                    // create the file
                    final String fileName = (lastSlash < 0) ? name : name.substring(lastSlash + 1);
                    final ZipFavCommanderFile file = new ZipFavCommanderFile(fileName, entry);

                    // add the file to the directory
                    directory.addZipFile(file);
                }
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        new ArrayList<>(directoryMap.values()).stream().sorted(
                Comparator.comparing(ZipFavCommanderDirectoryFile::getName)
        ).forEach(dir -> {
            System.out.println(dir.getName());
            dir.listDirectoryFileList().forEach(
                    f -> System.out.println("\t + " + f.getName()));
        });
    }

    @Override
    public List<? extends FavCommanderFile> listRoots() {
        return List.of();
    }

}
