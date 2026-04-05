package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme;
import wf.fav.apps.fc.config.FavCommanderVisualConfigurationLightTheme;
import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.AbstractFavCommanderLocalFile;
import wf.fav.apps.fc.gui.table.FavCommanderTableModel;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class FavCommanderController {

    private final FavCommanderTableModel leftPanelModel;

    private final FavCommanderTableModel rightPanelModel;

    private final FavCommanderVisualConfigurationDarkTheme darkTheme;

    private final FavCommanderVisualConfigurationLightTheme lightTheme;

    private final FavCommanderMainWindow mainWindow;

    private FavCommanderTableModel activePanelModel;

    private FavCommanderVisualConfigurationTheme activeTheme;

    public FavCommanderController(
            final FavCommanderTableModel leftPanelModel,
            final FavCommanderTableModel rightPanelModel,
            final FavCommanderVisualConfigurationDarkTheme darkTheme,
            final FavCommanderVisualConfigurationLightTheme lightTheme,
            final FavCommanderMainWindow mainWindow) {
        this.leftPanelModel = leftPanelModel;
        this.rightPanelModel = rightPanelModel;
        activePanelModel = leftPanelModel;
        leftPanelModel.setActive(true);

        leftPanelModel.setController(this);
        rightPanelModel.setController(this);

        this.darkTheme = darkTheme;
        this.lightTheme = lightTheme;
        activeTheme = darkTheme;
        leftPanelModel.setTheme(activeTheme);
        rightPanelModel.setTheme(activeTheme);
        this.mainWindow = mainWindow;
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE -> System.exit(0);

            case VK_UP -> activePanelModel.moveCursorUp();

            case VK_DOWN -> activePanelModel.moveCursorDown();

            case VK_INSERT -> activePanelModel.markFile();

            case VK_HOME -> activePanelModel.moveCursorHome();

            case VK_END -> activePanelModel.moveCursorEnd();

            case VK_PAGE_UP -> activePanelModel.moveCursorPageUp();

            case VK_PAGE_DOWN -> activePanelModel.moveCursorPageDown();

            case VK_RIGHT, VK_ENTER -> activePanelModel.openFileOrDirectory();

            case VK_LEFT, VK_BACK_SPACE -> activePanelModel.goToParentDirectory();

            case VK_TAB -> switchPanels();

            case VK_F2 -> renameFile();

            case VK_F3 -> viewFile();

            case VK_F4 -> editFile();

            case VK_F5 -> copyFilesOrDirectories();

            case VK_F6 -> moveFilesOrDirectories();

            case VK_F7 -> createDirectory();

            case VK_F8, VK_DELETE -> deleteFilesOrDirectories();

            case VK_T -> switchTheme();

            default -> System.out.println(keyCode);
        }
    }

    private void renameFile() {
        if (activePanelModel.isFileListEmpty()) {
            return;
        }

        final FavCommanderFile file = activePanelModel.getFile(activePanelModel.getCursorIndex());

        if (!(file instanceof AbstractFavCommanderLocalFile)) {
            return;
        }

        final String newName = (String) JOptionPane.showInputDialog(mainWindow,
                "New name:", "Rename file", QUESTION_MESSAGE,
                null, null, file.getName());

        if (newName == null) {
            return;
        }

        System.out.println(newName);
    }

    private void viewFile() {
        editFile();
    }

    private void editFile() {
        if (activePanelModel.isFileListEmpty()) {
            return;
        }

        final FavCommanderFile file = activePanelModel.getFile(activePanelModel.getCursorIndex());

        if (file.isDirectory() || !(file instanceof AbstractFavCommanderLocalFile)) {
            return;
        }

        try {
            final File localFile = ((AbstractFavCommanderLocalFile) file).getFile();
            Runtime.getRuntime().exec(new String[] { "notepad", localFile.getAbsolutePath() });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void copyFilesOrDirectories() {
        ;
    }

    private void createDirectory() {
        final String directoryName = JOptionPane.showInputDialog(mainWindow,
                "Directory name:", "Create directory", QUESTION_MESSAGE);

        if (directoryName == null) {
            return;
        }

        System.out.println(directoryName);
    }

    private void deleteFilesOrDirectories() {
        ;
    }

    private void moveFilesOrDirectories() {
        final FavCommanderTableModel inactivePanel = getInactivePanel();
        final int option = JOptionPane.showConfirmDialog(mainWindow,
                "Do you want to move?", "Move",
                JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE);
        System.out.println(option);
        System.out.println(inactivePanel);
    }

    private FavCommanderTableModel getInactivePanel() {
        return (activePanelModel == leftPanelModel) ? rightPanelModel : leftPanelModel;
    }

    public void switchPanels() {
        activePanelModel.setActive(false);
        activePanelModel = getInactivePanel();
        activePanelModel.setActive(true);
        leftPanelModel.viewRepaint();
        rightPanelModel.viewRepaint();
    }

    private void switchTheme() {
        activeTheme = (activeTheme == darkTheme) ? lightTheme : darkTheme;
        leftPanelModel.setTheme(activeTheme);
        rightPanelModel.setTheme(activeTheme);
    }

}
