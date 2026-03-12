package wf.fav.apps.fc.gui;

import wf.fav.apps.fc.gui.table.FavCommanderTableModel;

import static java.awt.event.KeyEvent.*;

public class FavCommanderController {

    private final FavCommanderTableModel model;

    public FavCommanderController(final FavCommanderTableModel favCommanderTableModel) {
        this.model = favCommanderTableModel;
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE -> System.exit(0);

            case VK_UP -> model.cursorUp();

            case VK_DOWN -> model.cursorDown();

            case VK_INSERT -> model.markFile();

            case VK_RIGHT, VK_ENTER -> model.openFileOrDirectory();

            case VK_LEFT, VK_BACK_SPACE -> model.goToParentDirectory();

            default -> System.out.println(keyCode);
        }
    }

}
