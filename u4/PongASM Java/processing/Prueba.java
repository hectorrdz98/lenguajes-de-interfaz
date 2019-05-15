
import processing.core.PApplet;

public class Prueba {
    public static void main(String[] args) {
        System.out.println("Prueba de processing");
        Interfaz interfaz = new Interfaz();
        String[] processingArgs = { "Interfaz" };
        PApplet.runSketch(processingArgs, interfaz);
    }
}