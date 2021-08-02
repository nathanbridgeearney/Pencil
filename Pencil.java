// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2021T2, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Pencil
 */
public class Pencil {
    Stack<Chunks> history = new Stack<Chunks>();
    Stack<Chunks> redoHistory = new Stack<Chunks>();
    double lineWidth = 1;
    int r = 0;
    int g = 0;
    int b = 0;
    Color lineColor = Color.black;
    private double lastX;
    private double lastY;


    public void undo() {
        if (!history.empty()) {
            UI.clearGraphics();
            redoHistory.push(history.peek());
            history.pop();
            for (Chunks chunk : history) {
                for (Strokes stroke : chunk.strokes) {
                    lineColor = stroke.color;
                    UI.setColor(stroke.color);
                    UI.setLineWidth(stroke.lineWidth);
                    UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);
                    UI.setColor(lineColor);
                }
            }
        }

        UI.setLineWidth(lineWidth);
    }

    public void redo() {
        if (!redoHistory.empty()) {
            UI.clearGraphics();
            history.push(redoHistory.peek());
            for (Strokes stroke : redoHistory.pop().strokes) {
                lineColor = stroke.color;
                UI.setColor(stroke.color);
                UI.setLineWidth(stroke.lineWidth);
                UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);
            }
            for (Chunks chunk : history) {
                for (Strokes stroke : chunk.strokes) {
                    lineColor = stroke.color;
                    UI.setColor(stroke.color);
                    UI.setLineWidth(stroke.lineWidth);
                    UI.drawLine(stroke.lastx, stroke.lasty, stroke.nx, stroke.ny);

                }
            }

            UI.setLineWidth(lineWidth);
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
        UI.addSlider("Stroke Size", 1, 20, 5, this::lineWidth);
        UI.addSlider("Red Value", 0, 255, 0,this::redVal);
        UI.addSlider("Green Value", 0, 255, 0, this::greenVal);
        UI.addSlider("Blue Value", 0, 255, 0, this::blueVal);
        UI.setDivider(0.0);
        new JColorChooser(Color.black);
    }

    private void blueVal(double v) {
        b = (int) v;
        lineColor = new Color(r,g,b);
    }
    private void greenVal(double v) {
        g = (int) v;
        lineColor = new Color(r,g,b);
    }

    private void redVal(double v) {
        r = (int) v;
        lineColor = new Color(r,g,b);
    }


    public void lineWidth(double v) {
        UI.setLineWidth(v);
        lineWidth = v;
    }


    /**
     * Respond to mouse events
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            UI.setColor(lineColor);
            lastX = x;
            lastY = y;
            history.push(new Chunks());
            redoHistory.clear();
        } else if (action.equals("dragged")) {

            UI.drawLine(lastX, lastY, x, y);
            history.peek().StrokeAdd(lastX, lastY, x, y, lineWidth, lineColor);
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
