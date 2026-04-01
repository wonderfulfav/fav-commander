package wf.fav.apps.fc.sort;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.FavCommanderParentDirectory;

import java.util.Comparator;

public class FavCommanderFileComparator {

    private static final Comparator<FavCommanderFile> PARENT_DIRECTORY_FIRST_COMPARATOR
            = (file1, file2) -> {
        if (file1 instanceof FavCommanderParentDirectory) {
            return -1;
        }

        if (file2 instanceof FavCommanderParentDirectory) {
            return 1;
        }

        return 0;
    };

    private static final Comparator<FavCommanderFile> DIRECTORY_BEFORE_FILES_COMPARATOR =
            (a, b) -> -Boolean.compare(a.isDirectory(), b.isDirectory());

    private static final Comparator<FavCommanderFile> NAME_ONLY_COMPARATOR =
            (a, b) -> a.getName().compareToIgnoreCase(b.getName());

    private static final Comparator<FavCommanderFile> GENERAL_COMPARATOR =
            PARENT_DIRECTORY_FIRST_COMPARATOR.thenComparing(DIRECTORY_BEFORE_FILES_COMPARATOR);

    public static final Comparator<FavCommanderFile> NAME =
            GENERAL_COMPARATOR.thenComparing(NAME_ONLY_COMPARATOR);

}
