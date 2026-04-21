package wf.fav.apps.fc.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class FavCommanderI18n {

    private static final ResourceBundle MESSAGE_BUNDLE = ResourceBundle.getBundle("MessageBundle", Locale.US);

    public static String getText(final String message) {
        return MESSAGE_BUNDLE.getString(message);
    }

}
