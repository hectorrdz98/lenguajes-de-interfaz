
public class Ball {

    public int radius;
    public int pos[] = new int[2];;
    public int color[] = new int[3];;
    public int vel[] = new int[2];;

    public Ball(int pos[], int color[], int vel[]) {
        this.radius = 25;
        this.pos = pos;
        this.color = color;
        this.vel = vel;
    }

}