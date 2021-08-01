import ecs100.UI;

public class Strokes {
    double lastx;
    double lasty;
    double nx;
    double ny;
    int lineNum;

    /**
     * Constructor
     */

    public Strokes (double lastX, double lastY, double x, double y, int line) {
        lastx = lastX;
        lasty = lastY;
        nx = x;
        ny=y;
        lineNum = line;
    }


}
