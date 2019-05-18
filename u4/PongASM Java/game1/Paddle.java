
public class Paddle {

    public int width;
    public int heigth;
    public int pos[] = new int[2];
    public int vel;
    public int moving;

    public Paddle(int height, int pos[], int vel) {
        this.width = 25;
        this.heigth = height;
        this.pos = pos;
        this.vel = vel;
        this.moving = 0;
    }

}