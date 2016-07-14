import java.awt.*;

/**
 * Created by zhiboliu on 16-7-13.
 */
public class Missile {

    private static final int XSPEED = 10;
    private static final int YSPEED = 10;

    int x,y;
    Tank.Direction dir;

    public Missile(int x, int y, Tank.Direction dir) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 10, 10);
        g.setColor(c);

        move();
    }

    private void move() {
        switch (dir){
            case L :
                x -= XSPEED;
                break;
            case LU :
                x -= XSPEED;
                y -= YSPEED;
                break;
            case U :
                y -= YSPEED;
                break;
            case RU :
                x += XSPEED;
                y -= YSPEED;
                break;
            case R :
                x += XSPEED;
                break;
            case RD :
                x += XSPEED;
                y += YSPEED;
                break;
            case D :
                y += YSPEED;
                break;
            case LD :
                x -= XSPEED;
                y += YSPEED;
                break;
        }
    }


}
