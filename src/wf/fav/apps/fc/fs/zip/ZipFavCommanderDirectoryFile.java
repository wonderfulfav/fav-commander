package wf.fav.apps.fc.fs.zip;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.ParentFavCommanderFile;

import java.util.ArrayList;
import java.util.List;

public class ZipFavCommanderDirectoryFile extends AbstractZipFavCommanderFile {

    private final List<FavCommanderFile> fileList = new ArrayList<>();

    public ZipFavCommanderDirectoryFile(final String name, final FavCommanderFile parentDirectory, final ZipFavCommanderFileSystem fileSystem) {
        super(name, parentDirectory, fileSystem);
        fileList.add(new ParentFavCommanderFile(parentDirectory, fileSystem));
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public List<? extends FavCommanderFile> listDirectoryFileList() {
        return fileList;
    }

    @Override
    public long getFileSize() {
        throw new RuntimeException("This is a ZIP directory: " + getName());
    }

    public void addZipFile(final AbstractZipFavCommanderFile file) {
        fileList.add(file);
    }

}
