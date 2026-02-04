package wf.fav.apps.fc.fs.local;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public List<? extends FavCommanderFile> listDirectoryFileList() {
        if (!isDirectory()) {
            throw new RuntimeException("Not a Directory: " + getName());
        }

        final File[] fileList = file.listFiles();

        if (fileList == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(Arrays.stream(fileList).map(LocalFavCommanderFile::new).toList());
    }

    public long getFileSize() {
        if (isDirectory()) {
            throw new RuntimeException("Directory: " + getName());
        }

        return file.length();
    }

}
