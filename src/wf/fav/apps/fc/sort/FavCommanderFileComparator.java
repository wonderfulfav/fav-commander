package wf.fav.apps.fc.sort;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

import java.util.Comparator;

public class FavCommanderFileComparator {

    public static final Comparator<FavCommanderFile> PARENT_DIRECTORY_LOCAL
            = (file1, file2) -> {
        if (file1 instanceof FavCommanderParentDirectory) {
            return -1;
        }

        if (file2 instanceof FavCommanderParentDirectory) {
            return 1;
        }

        return 0;
    };

    private static final Comparator<FavCommanderFile> DIRECTORY_LOCAL =
            (a, b) -> -Boolean.compare(a.isDirectory(), b.isDirectory());

    private static final Comparator<FavCommanderFile> NAME_LOCAL =
            (a, b) -> a.getName().compareToIgnoreCase(b.getName());

    public static final Comparator<FavCommanderFile> NAME =
            PARENT_DIRECTORY_LOCAL.thenComparing(
                    DIRECTORY_LOCAL.thenComparing(
                            NAME_LOCAL));

}
