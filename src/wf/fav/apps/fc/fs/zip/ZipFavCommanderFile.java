package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.util.List;
import java.util.zip.ZipEntry;

public class ZipFavCommanderFile implements FavCommanderFile {

    final String name;
    final ZipEntry zipEntry;

    public ZipFavCommanderFile(final String name, final ZipEntry zipEntry) {
        this.name = name;
        this.zipEntry = zipEntry;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public FavCommanderFile getParentDirectory() {
        throw new RuntimeException();
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
