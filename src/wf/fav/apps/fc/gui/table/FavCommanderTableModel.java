package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.zip.ZipFavCommanderFileSystem;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavCommanderTableModel {

    private FavCommanderFile currentDirectory;
    private List<? extends FavCommanderFile> fileList;
    private final Set<FavCommanderFile> selectedSet = new HashSet<>();
    private int cursorIndex;

    private final FavCommanderTableView view;

    public FavCommanderTableModel(final FavCommanderTableView view) {
        this.view = view;
    }

    public void viewRepaint() {
        view.repaint();
    }

    public int getCursorIndex() {
        return cursorIndex;
    }

    public int getFileListSize() {
        return fileList.size();
    }

    public FavCommanderFile getFile(final int index) {
        return fileList.get(index);
    }

    public boolean selectedSetContains(final FavCommanderFile file) {
        return selectedSet.contains(file);
    }

    public boolean isFileListEmpty() {
        return fileList == null || fileList.isEmpty();
    }

    public void setCurrentDirectory(final FavCommanderFile currentDirectory) {
        this.currentDirectory = currentDirectory;
        fileList = currentDirectory.listDirectoryFileList();
        fileList.sort(FavCommanderFileComparator.NAME);
        selectedSet.clear();
        cursorIndex = 0;
        viewRepaint();
    }

    public void cursorUp() {
        if (cursorIndex > 0) {
            cursorIndex--;
            viewRepaint();
        }
    }

    public void cursorDown() {
        if (fileList != null && cursorIndex < fileList.size() - 1) {
            cursorIndex++;
            viewRepaint();
        }
    }

    public void markFile() {
        final FavCommanderFile selectedFile = fileList.get(cursorIndex);

        if (selectedSet.contains(selectedFile)) {
            selectedSet.remove(selectedFile);
        } else {
            selectedSet.add(selectedFile);
        }

        cursorDown();
    }

    public void openFileOrDirectory() {
        if (isFileListEmpty()) {
            goToParentDirectory();
            return;
        }

        final FavCommanderFile file = getFile(getCursorIndex());

        if (file.isDirectory()) {
            setCurrentDirectory(file);
        } else if (file.getName().endsWith(".zip")) {
            setCurrentDirectory(new ZipFavCommanderFileSystem(file).getRootList().getFirst());
        }
    }

    public void goToParentDirectory() {
        final FavCommanderFile parentDirectory = currentDirectory.getParentDirectory();

        if (parentDirectory == null) {
            return;
        }

        setCurrentDirectory(parentDirectory);
    }

    public void setTheme(final FavCommanderVisualConfigurationTheme theme) {
        view.setTheme(theme);
        viewRepaint();
    }

}
