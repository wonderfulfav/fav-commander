package wf.fav.apps.fc.sort;

import wf.fav.apps.fc.fs.FavCommanderFile;

import java.util.Comparator;

public class FavCommanderFileComparator {
    private static final Comparator<FavCommanderFile> DIRECTORY_LOCAL = new FavCommanderIsDirectoryComparator();
    private static final Comparator<FavCommanderFile> NAME_LOCAL = new FavCommanderFileNameComparator();
    public static Comparator<FavCommanderFile> NAME = DIRECTORY_LOCAL.thenComparing(NAME_LOCAL);
}
