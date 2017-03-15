package unit_testing;

import java.awt.Color;

/**
 * 
 * @author dihuang
 *
 */
public class TestingSquare {

    public int x, y;
    public final int width = 20;

    private Color color;
    private boolean pressed = false;

    public TestingSquare(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean isWithin(int xmouse, int ymouse) {
        return xmouse >= x && xmouse <= x + width && ymouse >= y && ymouse <= y + width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void pressed() {
        pressed = true;
    }

    public void released() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }
}