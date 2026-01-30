package wf.fav.apps.fc.fs.local;

import java.io.File;

public class LocalFavCommanderFile extends AbstractLocalFavCommanderFile {

    private final String name;

    public LocalFavCommanderFile(final File file) {
        super(file);
        this.name = file.getAbsoluteFile().getName();
    }

    @Override
    public String getName() {
        return name;
    }

}
