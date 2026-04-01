package wf.fav.apps.fc.fs.local;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractLocalFavCommanderFile implements FavCommanderFile {

    private final File file;

    public AbstractLocalFavCommanderFile(final File file) {
        this.file = file.getAbsoluteFile();
    }

    @Override
    public FavCommanderFileSystem getFileSystem() {
        return LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public FavCommanderParentDirectory getParentDirectory() {
        final File parentDirectory = file.getParentFile();

        if (parentDirectory == null) {
            return null;
        }

        return new FavCommanderParentDirectory(new LocalFavCommanderFile(parentDirectory), getFileSystem());
    }

    @Override
    public List<? extends FavCommanderFile> getDirectoryFileList() {
        if (!isDirectory()) {
            throw new RuntimeException("Not a Directory: " + getName());
        }

        final ArrayList<FavCommanderFile> result = new ArrayList<>();

        final FavCommanderFile parentDirectory = getParentDirectory();

        if (parentDirectory != null) {
            result.add(parentDirectory);
        }

        final File[] fileArray = file.listFiles();

        if (fileArray != null) {
            result.addAll(Arrays.stream(fileArray).map(LocalFavCommanderFile::new).toList());
        }

        return result;
    }

    public long getFileSize() {
        if (isDirectory()) {
            throw new RuntimeException("This is a directory: " + getName());
        }

        return file.length();
    }

    public File getFile() {
        return file;
    }

}
