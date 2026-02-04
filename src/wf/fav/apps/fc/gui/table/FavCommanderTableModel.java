package wf.fav.apps.fc.gui.table;

import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavCommanderTableModel {

    private List<? extends FavCommanderFile> fileList;
    private final Set<FavCommanderFile> selectedSet = new HashSet<>();
    private int cursorIndex;

    private final FavCommanderTableView view;

    public FavCommanderTableModel(final FavCommanderTableView favCommanderTableView) {
        this.view = favCommanderTableView;
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

    public void setFileList(final List<? extends FavCommanderFile> fileList) {
        fileList.sort(FavCommanderFileComparator.NAME);
        this.fileList = fileList;
        selectedSet.clear();
        cursorIndex = 0;
        view.repaint();
    }

    public void cursorUp() {
        if (cursorIndex > 0) {
            cursorIndex--;
            view.repaint();
        }
    }

    public void cursorDown() {
        if (fileList != null && cursorIndex < fileList.size() - 1) {
            cursorIndex++;
            view.repaint();
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
        final FavCommanderFile file = getFile(getCursorIndex());

        if (file.isDirectory()) {
            setFileList(file.listDirectoryFileList());
        }
    }

}
