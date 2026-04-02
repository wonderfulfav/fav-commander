package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.zip.FavCommanderZipFileSystem;
import wf.fav.apps.fc.gui.FavCommanderController;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static wf.fav.apps.fc.config.FavCommanderVisualConfigurationFontSize.LINE_HEIGHT;

public class FavCommanderTableModel {

    private final FavCommanderTableView view;
    private final Set<FavCommanderFile> selectedSet = new HashSet<>();

    private FavCommanderController controller;

    private FavCommanderFile currentDirectory;
    private List<? extends FavCommanderFile> fileList;
    private int cursorIndex;
    private boolean active;

    public FavCommanderTableModel(final FavCommanderTableView view) {
        this.view = view;
    }

    public void setController(final FavCommanderController controller) {
        this.controller = controller;
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
        fileList = currentDirectory.getDirectoryFileList();
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

    public void cursorHome() {
        if (fileList == null) {
            return;
        }

        cursorIndex = 0;
        viewRepaint();
    }


    public void cursorEnd() {
        if (fileList == null) {
            return;
        }

        cursorIndex = fileList.size() - 1;
        viewRepaint();
    }

    public void cursorPageUp() {
        if (fileList == null) {
            return;
        }

        final int jumpSize = view.getHeight() / LINE_HEIGHT;
        cursorIndex -= jumpSize;

        if (cursorIndex < 0) {
            cursorIndex = 0;
        }

        viewRepaint();
    }

    public void cursorPageDown() {
        if (fileList == null) {
            return;
        }

        final int jumpSize = view.getHeight() / LINE_HEIGHT;
        cursorIndex += jumpSize;

        if (cursorIndex >= fileList.size() - 1) {
            cursorIndex = fileList.size() - 1;
        }

        viewRepaint();
    }

    public void setCursorIndex(final int index) {
        if (isFileListEmpty() || index >= fileList.size()) {
            return;
        }

        cursorIndex = index;

        if (!isActive()) {
            controller.switchPanels();
        }

        viewRepaint();
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
            setCurrentDirectory(new FavCommanderZipFileSystem(file).getRootList().getFirst());
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

    public void setActive(final boolean active) {
        this.active = active;
        viewRepaint();
    }

    public boolean isActive() {
        return active;
    }

}
