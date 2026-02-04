package wf.fav.apps.fc.gui;

public class FavCommanderFormatUtil {

    private static final long MAGNITUDE = 1024;
    private static final long KB = MAGNITUDE;
    private static final long MB = KB * MAGNITUDE;
    private static final long GB = MB * MAGNITUDE;
    private static final long TB = GB * MAGNITUDE;
    private static final long PB = TB * MAGNITUDE;

    private static final long KB10 = 10 * KB;
    private static final long MB10 = 10 * MB;
    private static final long GB10 = 10 * GB;
    private static final long TB10 = 10 * TB;

    private static final String SPACE = " ";
    private static final String SPACE2 = SPACE + SPACE;
    private static final String SPACE3 = SPACE2 + SPACE;

    // or make it KiB, MiB, GiB, TiB, PiB?
    private static final String B = SPACE + "B";
    private static final String KIB = SPACE + "K";
    private static final String MIB = SPACE + "M";
    private static final String GIB = SPACE + "G";
    private static final String TIB = SPACE + "T";
    private static final String PIB = SPACE + "P";

    public static String humanReadableSize(final long size) {
        if (size > PB) {
            return leftPad(size / PB) + PIB;
        }
        if (size > TB10) {
            return leftPad(size / TB) + TIB;
        }

        if (size > GB10) {
            return leftPad(size / GB) + GIB;
        }

        if (size > MB10) {
            return leftPad(size / MB) + MIB;
        }

        if (size > KB10) {
            return leftPad(size / KB) + KIB;
        }

        return leftPad(size) + B;
    }

    public static String leftPad(final long size) {
        if (size < 10) {
            return SPACE3 + size;
        }

        if (size < 100) {
            return SPACE2 + size;
        }

        if (size < 1000) {
            return SPACE + size;
        }

        return Long.toString(size);
    }

}
