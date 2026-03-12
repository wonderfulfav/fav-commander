package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.gui.table.FavCommanderTableModel;

import static java.awt.event.KeyEvent.*;

public class FavCommanderController {

    private final FavCommanderTableModel leftPanelModel;

    private final FavCommanderTableModel rightPanelModel;

    private FavCommanderTableModel activePanelModel;

    public FavCommanderController(
            final FavCommanderTableModel leftPanelModel,
            final FavCommanderTableModel rightPanelModel) {
        this.leftPanelModel = leftPanelModel;
        this.rightPanelModel = rightPanelModel;
        activePanelModel = leftPanelModel;
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

            default -> System.out.println(keyCode);
        }
    }

    private void switchPanels() {
        activePanelModel = (activePanelModel == leftPanelModel) ? rightPanelModel : leftPanelModel;
        leftPanelModel.viewRepaint();
        rightPanelModel.viewRepaint();
    }

}
