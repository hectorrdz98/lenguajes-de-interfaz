import processing.core.*;

public class Interfaz extends PApplet {
    
    int posX = 50;

    public void settings() { 
        size(1000,600);
    }

    public void draw() {
        clear();
        ellipse(posX, 50, 50, 50);
        posX++;
    }
}