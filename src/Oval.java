//Tanvi
//Interface Lab

import java.awt.*;

public class Oval implements Drawable
{
    private Color color;
    private int width;
    private int height;


    public Oval(Color c, int w, int h) {
        color = c;
        width = w;
        height = h;
    }
    public void draw(Graphics g, int x, int y) {
        Color old = g.getColor();
        g.setColor(color);
        g.drawOval(x, y, width, height);
        g.setColor(old);
    }
    public void fill(Graphics g, int x, int y) {
        Color old = g.getColor();
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g.setColor(old);
    }
}

