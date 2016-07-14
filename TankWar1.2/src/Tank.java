import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by zhiboliu on 16-7-13.
 */
public class Tank {

    public static final int XSPEED = 5;
    public static final int YSPEED = 5;

    public static final int WIDTH =30;
    public static final int HEIGHT =30;


    private Direction dir = Direction.STOP;
    private Direction ptDir = Direction.D;// we need to draw tank barrel according to direction


    TankClient tc = null;

    private int x, y;

    private boolean bL = false, bR = false, bU = false, bD = false;

    public Tank(int x, int y, TankClient tc) {
        //this(x,y);
        this.x = x;
        this.y = y;
        this.tc = tc;
    }


    enum Direction {
        L, LU, U,RU,R,RD,LD,D,STOP
    };


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        //g 有前景色 可以取出来 然后设置成你想要的颜色
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);// draw the circle tank, we wanna make tank move, so we need its position to change
        g.setColor(c);//set the color back to the original one

        //y += 5;  //test if thread can move the tank

        switch (ptDir){
        case L :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT/2);
            break;
        case LU :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y);
            break;
        case U :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
            break;
        case RU :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y);
            break;
        case R :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT/2);
            break;
        case RD :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT);
            break;
        case D :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y + Tank.HEIGHT);
            break;
        case LD :
            g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT);
            break;
        }

        move();
    }

    //now tank move not on average as keyboard does not response on average, so we want keyboard to only change tank direction
    //then owing to direction change, tank change position

    void move(){
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
            case STOP :
                break;
        }

        if(this.dir != Direction.STOP){ //别把dir写成ptDir了  这里是看 只要tank不是停着的 pt就是跟tank dir一样！！！！！！！！！！！！！
            this.ptDir = this.dir;
        }

    }

    public Missile fire(){
        //x is used for missile, this.x is from tank
        int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
        int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        Missile m = new Missile(x, y, ptDir); //x, y from tank
        return m;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();//用这个code来查看按了哪个按键
        switch (key){
            case KeyEvent.VK_CONTROL ://add control to launch missile from tank
                tc.m = fire();
                break;
            case KeyEvent.VK_RIGHT :
                bR = true;
                break;
            case KeyEvent.VK_LEFT :
                bL = true;
                break;
            case KeyEvent.VK_UP :
                bU = true;
                break;
            case KeyEvent.VK_DOWN :
                bD = true;
                break;
        }


        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();//用这个code来查看按了哪个按键
        switch (key){
            case KeyEvent.VK_RIGHT :
                bR = false;
                break;
            case KeyEvent.VK_LEFT :
                bL = false;
                break;
            case KeyEvent.VK_UP :
                bU = false;
                break;
            case KeyEvent.VK_DOWN :
                bD = false;
                break;
        }

        locateDirection();
    }

    void locateDirection(){
        if(bL && !bU && !bR && !bD){
            dir = Direction.L;
        }
        else if(bL && bU && !bR && !bD){
            dir = Direction.LU;
        }
        else if(!bL && bU && !bR && !bD){
            dir = Direction.U;
        }
        else if(!bL && bU && bR && !bD){
            dir = Direction.RU;
        }
        else if(!bL && !bU && bR && !bD){
            dir = Direction.R;
        }
        else if(!bL && !bU && bR && bD){
            dir = Direction.RD;
        }
        else if(!bL && !bU && !bR && bD){
            dir = Direction.D;
        }
        else if(bL && !bU && !bR && bD){
            dir = Direction.LD;
        }
        else if(!bL && !bU && !bR && !bD){
            dir = Direction.STOP;
        }
    }

}
