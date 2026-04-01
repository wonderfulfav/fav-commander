package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;
import wf.fav.apps.fc.fs.local.AbstractFavCommanderLocalFile;

import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FavCommanderZipFileSystem implements FavCommanderFileSystem {

    private final FavCommanderRootZipDirectory rootDirectory;

    public FavCommanderZipFileSystem(final FavCommanderFile parentFile) {
        rootDirectory = new FavCommanderRootZipDirectory("", parentFile.getParentDirectory(), parentFile.getFileSystem());
        final FavCommanderZipFileSystemBuilder directoryBuilder = new FavCommanderZipFileSystemBuilder(rootDirectory, this);

        final AbstractFavCommanderLocalFile fsFile = ((AbstractFavCommanderLocalFile) parentFile);
        try (final ZipFile zipFile = new ZipFile(fsFile.getFile())) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                final String name = entry.getName();

                final int lastSlash = name.lastIndexOf('/');

                // find or create the parent directory
                final String directoryPath = name.substring(0, lastSlash + 1);
                final AbstractFavCommanderZipDirectory directory = directoryBuilder.getOrCreateDirectory(directoryPath);

                if (entry.isDirectory()) {
                    continue;
                }

                // create the file
                final String fileName = (lastSlash < 0) ? name : name.substring(lastSlash + 1);
                final FavCommanderZipFile file = new FavCommanderZipFile(fileName, directory, this, entry);

                // add the file to the directory
                directory.addZipFile(file);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<? extends FavCommanderFile> getRootList() {
        return List.of(rootDirectory);
    }

    private static final class FavCommanderZipFileSystemBuilder {

        private final HashMap<String, AbstractFavCommanderZipDirectory> directoryMap = new HashMap<>();
        private final FavCommanderRootZipDirectory rootDirectory;
        private final FavCommanderZipFileSystem fileSystem;

        public FavCommanderZipFileSystemBuilder(
                final FavCommanderRootZipDirectory rootDirectory,
                final FavCommanderZipFileSystem fileSystem) {
            this.rootDirectory = rootDirectory;
            this.fileSystem = fileSystem;
            directoryMap.put("", rootDirectory);
        }

        public AbstractFavCommanderZipDirectory getOrCreateDirectory(final String directoryPath) {
            if (directoryMap.containsKey(directoryPath)) {
                return directoryMap.get(directoryPath);
            }

            // create directory structure
            AbstractFavCommanderZipDirectory parentDirectory = rootDirectory;

            for (int startPath = 0, slashPosition = directoryPath.indexOf('/'); slashPosition > 0;
                 startPath = slashPosition + 1, slashPosition = directoryPath.indexOf('/', startPath)) {
                final String currentDirectoryPath = directoryPath.substring(0, slashPosition + 1);

                if (directoryMap.containsKey(currentDirectoryPath)) {
                    parentDirectory = directoryMap.get(currentDirectoryPath);
                    continue;
                }

                final String currentDirectoryName = currentDirectoryPath.substring(startPath, slashPosition);
                final FavCommanderZipDirectory currentDirectory =
                        new FavCommanderZipDirectory(currentDirectoryName, new FavCommanderParentDirectory(parentDirectory, fileSystem), fileSystem);
                directoryMap.put(currentDirectoryPath, currentDirectory);
                parentDirectory.addZipFile(currentDirectory);
                parentDirectory = currentDirectory;
            }

            return parentDirectory;
        }

    }

}
