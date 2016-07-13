import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by zhiboliu on 16-7-9.
 */


//0.1 create the game window

//0.2 close window and not make window resizable

//0.3 draw tank

//0.4 make tank move, we can use a thread to paint tank every second.

//0.4_1 deal with tank glare using double buffer： 用一个虚拟图片全部加载完刷新的坦克 然后再整体显示在屏幕上

public class TankClient extends Frame{//extends Frame so u can draw a canvas

    int x = 50, y = 50;//tank's beginning position


    Image offScreenImage = null;// repaint -> update -> paint so in update, we need to put image to offScreenImage

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(1600, 1200);
        }
        //offScreenImage has a drawing pen, we get pen first then draw on offScreenPage
        Graphics gOffScreenImage = offScreenImage.getGraphics();
        //each time i draw, i draw the background to avoid tank to be a line on screen
        Color c = gOffScreenImage.getColor();
        gOffScreenImage.setColor(Color.GREEN);
        gOffScreenImage.fillRect(0, 0, 1600, 1200);
        gOffScreenImage.setColor(c);

        paint(gOffScreenImage);
        //now we need to use pen from g of the real screen to draw
        g.drawImage(offScreenImage, 0, 0, null);// 0,0 is left up corner position
    }


    public void lauchFrame(){
        this.setLocation(400, 300);
        this.setSize(1600, 1200);
        this.setTitle("Tank War game is locally running. ");
        this.addWindowListener(new WindowAdapter() {//匿名类
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);//不让改变窗口大小
        this.setBackground(Color.GREEN);
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args){
        TankClient tc = new TankClient();
        tc.lauchFrame();

    }

    @Override
    public void paint(Graphics g) {
        //g 有前景色 可以取出来 然后设置成你想要的颜色
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);// draw the circle tank, we wanna make tank move, so we need its position to change
        g.setColor(c);//set the color back to the original one

        y += 5;

    }



    //this thread only works for tank to make tank move, so we can use internal class
    private class PaintThread implements Runnable{
    //internal class can call external class's method, external class surrounds internal class
        @Override
        public void run() {
            while(true){
                repaint();// repaint belong to external class, repaint will call paint
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
// we use double buffer to deal with tank glare in 0.4_1