import java.awt.*;

/**
 * Created by zhiboliu on 16-7-19.
 */
public class Blood {
    int x,y,w,h;
    TankClient tc;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    int step = 0;

    private int[][] pos = {
        {350,300}, {360, 300},{375,275},{400, 200},{359, 269}, {367,298},{349,279}
    };

    public Blood(){
        x = pos[0][0];
        y = pos[0][1];
        w = h = 15;

    }

    public void draw(Graphics g){
        if(!live){
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, w, h);
        g.setColor(c);

        move();
    }

    public void move(){
        step++;
        if(step == pos.length){
            step = 0;
        }
        x = pos[step][0];
        y = pos[step][1];
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, w, h);
    }
}
