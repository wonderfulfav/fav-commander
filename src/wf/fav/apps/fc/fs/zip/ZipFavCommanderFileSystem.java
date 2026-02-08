package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;
import wf.fav.apps.fc.fs.zip.mapper.ZipFavCommanderDirectoryMapper;

import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFavCommanderFileSystem implements FavCommanderFileSystem {

    public static void openZipFile(final FavCommanderFile file) {
    }

    public static void main(String[] args) {
        final ZipFavCommanderDirectoryMapper directoryMapper = new ZipFavCommanderDirectoryMapper();

        try (final ZipFile zipFile = new ZipFile("/test.zip")) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                final String name = entry.getName();

                final int lastSlash = name.lastIndexOf('/');

                // find or create the parent directory
                final String directoryPath = name.substring(0, lastSlash + 1);
                final ZipFavCommanderDirectoryFile directory = directoryMapper.getOrCreateDirectory(directoryPath);

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

        directoryMapper.printValues();
    }

    @Override
    public List<? extends FavCommanderFile> listRoots() {
        return List.of();
    }

}
