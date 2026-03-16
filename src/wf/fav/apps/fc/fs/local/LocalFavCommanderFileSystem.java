package wf.fav.apps.fc.fs.local;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderFileSystem;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LocalFavCommanderFileSystem implements FavCommanderFileSystem {

    private static final LocalFavCommanderFileSystem instance = new LocalFavCommanderFileSystem();

    private LocalFavCommanderFileSystem() { }

    @Override
    public List<? extends FavCommanderFile> getRootList() {
        // can also be done as:
        // for (Path path : Paths.get("/").getFileSystem().getRootDirectories()) System.out.println(path);
        return Arrays.stream(File.listRoots()).map(LocalRootFavCommanderFile::new).toList();
    }

    public static LocalFavCommanderFileSystem getLocalFavCommanderFileSystemInstance() {
        return instance;
    }

}
