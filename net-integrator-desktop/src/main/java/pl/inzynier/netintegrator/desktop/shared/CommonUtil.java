package pl.inzynier.netintegrator.desktop.shared;

public class CommonUtil {
    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public static String standardFormat(String s) {
        return centerString(25, s);
    }
}
