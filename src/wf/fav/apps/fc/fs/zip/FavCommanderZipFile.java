package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

import java.util.List;
import java.util.zip.ZipEntry;

public class FavCommanderZipFile extends AbstractFavCommanderZipFile {

    final ZipEntry zipEntry;

    public FavCommanderZipFile(
            final String name,
            final AbstractFavCommanderZipDirectory parentDirectory,
            final FavCommanderZipFileSystem fileSystem,
            final ZipEntry zipEntry) {
        super(name, new FavCommanderParentDirectory(parentDirectory, fileSystem), fileSystem);
        this.zipEntry = zipEntry;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public List<? extends FavCommanderFile> getDirectoryFileList() {
         throw new RuntimeException("Not a ZIP directory: " + getName());
    }

    @Override
    public long getFileSize() {
        return zipEntry.getSize();
    }

}
