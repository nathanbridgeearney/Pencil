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
    Stack<Chunks> history = new Stack<Chunks>();
    Stack<Chunks> redoHistory = new Stack<Chunks>();

    private double lastX;
    private double lastY;


    public void undo() {
        if (!history.empty()) {
            UI.clearGraphics();
            redoHistory.push(history.peek());
            history.pop();
            for (Chunks chunk : history) {
                for (Strokes stroke : chunk.strokes) {
                    UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);
                }
            }
        }
    }

    public void redo() {
        if (!history.empty()) {
            UI.clearGraphics();
            history.push(redoHistory.peek());
            for (Strokes stroke : redoHistory.pop().strokes) {
                UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);
            }
            for (Chunks chunk : history) {
                for (Strokes stroke : chunk.strokes) {
                    UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);
                }
            }
        }
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
            history.push(new Chunks());
            redoHistory.clear();
        } else if (action.equals("dragged")) {
            UI.drawLine(lastX, lastY, x, y);
            history.peek().StrokeAdd(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        } else if (action.equals("released")) {
            UI.drawLine(lastX, lastY, x, y);
        }

    }

    public static void main(String[] arguments) {
        new Pencil().setupGUI();
    }

}
