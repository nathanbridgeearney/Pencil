import java.awt.*;
import java.util.ArrayList;
//Stores data per line
public class Chunks {
    ArrayList<Strokes> strokes = new ArrayList<>();

//Creates new chunk for access later
    public Chunks() {

    }
//Adds strokes to the arraylist
    public void StrokeAdd(double lastX, double lastY, double x, double y, double lineWidth,Color Color){
        strokes.add(new Strokes(lastX, lastY, x, y, lineWidth, Color));
    }

}
