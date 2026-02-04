package wf.fav.apps.fc.gui.table;

import javax.swing.*;
import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class FavCommanderTableController {

    private final FavCommanderTable table;
    private final FavCommanderTableModel model;

    public FavCommanderTableController(
            final FavCommanderTable favCommanderTable,
            final FavCommanderTableModel favCommanderTableModel) {
        this.table = favCommanderTable;
        this.model = favCommanderTableModel;
    }

    public void keyPressedAction(final int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE -> System.exit(0);

            case VK_UP -> {
                if (model.cursorUp()) {
                    table.repaint();
                }
            }

            case VK_DOWN -> {
                if (model.cursorDown()) {
                    table.repaint();
                }
            }

            case VK_INSERT -> {
                if (model.markFile()) {
                    table.repaint();
                }
            }

            default -> System.out.println(keyCode);
        }
    }

}
