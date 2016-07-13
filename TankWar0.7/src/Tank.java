import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by zhiboliu on 16-7-13.
 */
public class Tank {
    int x, y;


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        //g 有前景色 可以取出来 然后设置成你想要的颜色
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);// draw the circle tank, we wanna make tank move, so we need its position to change
        g.setColor(c);//set the color back to the original one

        //y += 5;  //test if thread can move the tank
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();//用这个code来查看按了哪个按键
        switch (key){
            case KeyEvent.VK_RIGHT :
                x += 5;
                break;
            case KeyEvent.VK_LEFT :
                x -= 5;
                break;
            case KeyEvent.VK_UP :
                y -= 5;
                break;
            case KeyEvent.VK_DOWN :
                y += 5;
                break;
        }
    }

}
