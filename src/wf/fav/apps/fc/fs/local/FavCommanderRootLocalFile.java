package wf.fav.apps.fc.fs.local;

import java.io.File;

public class FavCommanderRootLocalFile extends AbstractFavCommanderLocalFile {

    private final String name;

    public FavCommanderRootLocalFile(final File file) {
        super(file);
        this.name = file.getAbsoluteFile().getAbsolutePath();
    }

    @Override
    public String getName() {
        return name;
    }

}
