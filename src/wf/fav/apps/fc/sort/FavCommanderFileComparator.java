package wf.fav.apps.fc.sort;

import wf.fav.apps.fc.fs.FavCommanderFile;

import java.util.Comparator;

public class FavCommanderFileComparator {

    private static final Comparator<FavCommanderFile> DIRECTORY_LOCAL =
            (a, b) -> -Boolean.compare(a.isDirectory(), b.isDirectory());

    private static final Comparator<FavCommanderFile> NAME_LOCAL =
            (a, b) -> a.getName().compareToIgnoreCase(b.getName());

    public static final Comparator<FavCommanderFile> NAME = DIRECTORY_LOCAL.thenComparing(NAME_LOCAL);

}
