package wf.fav.apps.fc.fs.local;

import java.io.File;

public class FavCommanderLocalFile extends AbstractFavCommanderLocalFile {

    private final String name;

    public FavCommanderLocalFile(final File file) {
        super(file);
        this.name = file.getAbsoluteFile().getName();
    }

    @Override
    public String getName() {
        return name;
    }

}
