package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.util.List;
import java.util.zip.ZipEntry;

public class ZipFavCommanderFile extends AbstractZipFavCommanderFile {

    final ZipEntry zipEntry;

    public ZipFavCommanderFile(final String name, final ZipFavCommanderDirectoryFile parentDirectory, final ZipEntry zipEntry) {
        super(name, parentDirectory);
        this.zipEntry = zipEntry;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public FavCommanderFileSystem getFileSystem() {
        throw new RuntimeException();
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
