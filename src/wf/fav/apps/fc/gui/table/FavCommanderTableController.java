package wf.fav.apps.fc.gui.table;

import static java.awt.event.KeyEvent.*;

public class FavCommanderTableController {

    private final FavCommanderTableModel model;

    public FavCommanderTableController(final FavCommanderTableModel favCommanderTableModel) {
        this.model = favCommanderTableModel;
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE -> System.exit(0);

            case VK_UP -> model.cursorUp();

            case VK_DOWN -> model.cursorDown();

            case VK_INSERT -> model.markFile();

            default -> System.out.println(keyCode);
        }
    }

}
