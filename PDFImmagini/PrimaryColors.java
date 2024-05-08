public class PrimaryColors {
    public static final int RED = 0xFF0000;
    public static final int ORANGE = 0xFF8000;
    public static final int YELLOW = 0xFFFF00;
    public static final int LIGHT_GREEN = 0x80FF00;
    public static final int GREEN = 0x00FF00;
    public static final int GREEN2 = 0x00FF80;
    public static final int CYAN = 0x00FFFF;
    public static final int LIGHT_BLUE = 0x0080FF;
    public static final int BLUE = 0x0000FF;
    public static final int PURPLE = 0x8000FF;
    public static final int MAGENTA = 0xFF00FF;
    public static final int PINK = 0xFF0080;

    public static final int[] colorRGB = {
            RED, ORANGE, YELLOW, LIGHT_GREEN, GREEN, GREEN2, CYAN, LIGHT_BLUE, BLUE, PURPLE, MAGENTA, PINK

    };

    public static float[][] colorLAB;

    PrimaryColors() {
        colorLAB = new float[colorRGB.length][];
        for (int i = 0; i < colorRGB.length; i++) {
            colorLAB[i] = CIELab.rgb2lab(colorRGB[i]);
        }
    }
}