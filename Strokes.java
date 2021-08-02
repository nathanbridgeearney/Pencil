import ecs100.UI;

import java.awt.*;
//Data for the strokes
public class Strokes {
    double lastx;
    double lasty;
    double nx;
    double ny;
    double lineWidth;

    Color color;

    /**
     * Constructor
     */
//Creates each stroke to be stored in chunks
    public Strokes(double lastX, double lastY, double x, double y, double linewidth, Color Color) {
        lastx = lastX;
        lasty = lastY;
        nx = x;
        ny = y;
        lineWidth = linewidth;
        color = Color;
    }


}
