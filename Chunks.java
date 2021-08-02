import java.awt.*;
import java.util.ArrayList;

public class Chunks {
    ArrayList<Strokes> strokes = new ArrayList<>();


    public Chunks() {

    }

    public void StrokeAdd(double lastX, double lastY, double x, double y, double lineWidth,Color Color){
        strokes.add(new Strokes(lastX, lastY, x, y, lineWidth, Color));
    }

}
