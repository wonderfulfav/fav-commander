package wf.fav.apps.fc.sort;

import wf.fav.apps.fc.fs.FavCommanderFile;

import java.util.Comparator;

public class FavCommanderIsDirectoryComparator implements Comparator<FavCommanderFile> {

    @Override
    public int compare(FavCommanderFile a, FavCommanderFile b) {
        return -Boolean.compare(a.isDirectory(), b.isDirectory());
    }

}
