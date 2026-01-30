package wf.fav.apps.fc.fs;

import java.util.List;

public interface FavCommanderFile {

    String getName();

    boolean isDirectory();

    FavCommanderFileSystem getFileSystem();

    List<? extends FavCommanderFile> listDirectoryFileList();

    long getFileSize();

}
