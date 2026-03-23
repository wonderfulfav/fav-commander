package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.config.FavCommanderVisualConfigurationDarkTheme;
import wf.fav.apps.fc.config.FavCommanderVisualConfigurationLightTheme;
import wf.fav.apps.fc.config.FavCommanderVisualConfigurationTheme;
import wf.fav.apps.fc.gui.table.FavCommanderTableModel;

import static java.awt.event.KeyEvent.*;

public class FavCommanderController {

    private final FavCommanderTableModel leftPanelModel;

    private final FavCommanderTableModel rightPanelModel;

    private final FavCommanderVisualConfigurationDarkTheme darkTheme;

    private final FavCommanderVisualConfigurationLightTheme lightTheme;

    private FavCommanderTableModel activePanelModel;

    private FavCommanderVisualConfigurationTheme activeTheme;

    public FavCommanderController(
            final FavCommanderTableModel leftPanelModel,
            final FavCommanderTableModel rightPanelModel,
            final FavCommanderVisualConfigurationDarkTheme darkTheme,
            final FavCommanderVisualConfigurationLightTheme lightTheme) {
        this.leftPanelModel = leftPanelModel;
        this.rightPanelModel = rightPanelModel;
        activePanelModel = leftPanelModel;
        leftPanelModel.setActive(true);

        this.darkTheme = darkTheme;
        this.lightTheme = lightTheme;
        activeTheme = darkTheme;
        leftPanelModel.setTheme(activeTheme);
        rightPanelModel.setTheme(activeTheme);
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE -> System.exit(0);

            case VK_UP -> activePanelModel.cursorUp();

            case VK_DOWN -> activePanelModel.cursorDown();

            case VK_INSERT -> activePanelModel.markFile();

            case VK_RIGHT, VK_ENTER -> activePanelModel.openFileOrDirectory();

            case VK_LEFT, VK_BACK_SPACE -> activePanelModel.goToParentDirectory();

            case VK_G -> switchPanels();

            case VK_T -> switchTheme();

            default -> System.out.println(keyCode);
        }
    }

    private void switchPanels() {
        activePanelModel.setActive(false);
        activePanelModel = (activePanelModel == leftPanelModel) ? rightPanelModel : leftPanelModel;
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
