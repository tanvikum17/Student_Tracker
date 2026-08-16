import java.awt.*;

//Tanvi
//Interface Lab
public interface Drawable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    void draw(Graphics g, int x, int y);

    void fill(Graphics g, int x, int y);
}

