package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;

import java.util.List;
import java.util.zip.ZipEntry;

public class ZipFavCommanderFile extends AbstractZipFavCommanderFile {

    final ZipEntry zipEntry;

    public ZipFavCommanderFile(
            final String name,
            final ZipFavCommanderDirectoryFile parentDirectory,
            final ZipFavCommanderFileSystem fileSystem,
            final ZipEntry zipEntry) {
        super(name, parentDirectory, fileSystem);
        this.zipEntry = zipEntry;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public List<? extends FavCommanderFile> listDirectoryFileList() {
         throw new RuntimeException("Not a ZIP directory: " + getName());
    }

    @Override
    public long getFileSize() {
        return zipEntry.getSize();
    }

}
