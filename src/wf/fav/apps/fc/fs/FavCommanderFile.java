package wf.fav.apps.fc.fs;

import java.util.List;

public interface FavCommanderFile {

    String getName();

    boolean isDirectory();

    ParentFavCommanderFile getParentDirectory();

    FavCommanderFileSystem getFileSystem();

    List<? extends FavCommanderFile> getDirectoryFileList();

    long getFileSize();

}
