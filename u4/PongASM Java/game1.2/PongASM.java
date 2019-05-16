
import processing.core.PApplet;

public class PongASM {

    public static void main(String[] args) {
        HectorASM asm = new HectorASM();
        Interfaz interfaz = new Interfaz(asm);

        System.out.println("Prueba de processing");
        
        String[] processingArgs = { "PongASM" };
        PApplet.runSketch(processingArgs, interfaz);
    }

}