package wf.fav.apps.fc.config;

import java.awt.Color;

public class FavCommanderVisualConfigurationLightTheme implements FavCommanderVisualConfigurationTheme {

    private static final Color BACKGROUND = Color.WHITE;
    private static final Color FOREGROUND = Color.BLACK;
    private static final Color CURSOR_BACKGROUND = Color.GRAY;
    private static final Color CURSOR_FOREGROUND = Color.BLACK;
    private static final Color HIGHLIGHTED = Color.BLUE;

    @Override
    public Color getBackgroundColor() {
        return BACKGROUND;
    }

    @Override
    public Color getForegroundColor() {
        return FOREGROUND;
    }

    @Override
    public Color getCursorBackgroundColor() {
        return CURSOR_BACKGROUND;
    }

    @Override
    public Color getCursorForegroundColor() {
        return CURSOR_FOREGROUND;
    }

    @Override
    public Color getHighlightedColor() {
        return HIGHLIGHTED;
    }

}
