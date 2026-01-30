package wf.fav.apps.fc.fs.local;

import java.io.File;

public class LocalRootFavCommanderFile extends AbstractLocalFavCommanderFile {

    private final String name;

    public LocalRootFavCommanderFile(final File file) {
        super(file);
        this.name = file.getAbsoluteFile().getAbsolutePath();
    }

    @Override
    public String getName() {
        return name;
    }

}
