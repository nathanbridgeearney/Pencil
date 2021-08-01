// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2021T2, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

import java.awt.*;
import java.util.*;

/**
 * Pencil
 */
public class Pencil {
    int line = 0;
    int line2 = 0;
    ArrayList<Strokes> history = new ArrayList<>();
    ArrayList<Strokes> redoS = new ArrayList<>();

    boolean temp = false;
    private double lastX;
    private double lastY;


    public void undo() {
        temp = false;
        UI.clearGraphics();
        for (Strokes strokes : history) {
            if (strokes.lineNum == line) {
                redoS.add(new Strokes(strokes.lastx, strokes.lasty, strokes.nx, strokes.ny, line2));
                temp = true;
            }
        }

        history.removeIf(k -> k.lineNum == line);
        for (Strokes i : history) {
            UI.drawLine(i.lastx, i.lasty, i.nx, i.ny);
        }
        line--;
        if (line <= 0) line = 0;
        UI.println("Undo Count " + line);
    }

    public void redo() {
        UI.clearGraphics();

        for (Strokes strokes : redoS) {
            if (strokes.lineNum == line2) {
                history.add(new Strokes(strokes.lastx, strokes.lasty, strokes.nx, strokes.ny, line));

            }
        }




        redoS.removeIf(k -> k.lineNum == line2);

        for (Strokes i : history) {
            UI.drawLine(i.lastx, i.lasty, i.nx, i.ny);
        }

        for (Strokes i : redoS) {
            UI.drawLine(i.lastx, i.lasty, i.nx, i.ny);
        }

        line++;

    }


    /**
     * Setup the GUI
     */
    public void setupGUI() {
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Quit", UI::quit);
        UI.addButton("Undo", this::undo);
        UI.addButton("Redo", this::redo);
        UI.setLineWidth(3);
        UI.setDivider(0.0);
    }

    /**
     * Respond to mouse events
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            lastX = x;
            lastY = y;
            line++;
            redoS.clear();
            line2 = 0;

        } else if (action.equals("dragged")) {
            UI.drawLine(lastX, lastY, x, y);
            history.add(new Strokes(lastX, lastY, x, y, line));
            lastX = x;
            lastY = y;


        } else if (action.equals("released")) {
            UI.drawLine(lastX, lastY, x, y);
            UI.println(line);

        }

    }

    public static void main(String[] arguments) {
        new Pencil().setupGUI();
    }

}
