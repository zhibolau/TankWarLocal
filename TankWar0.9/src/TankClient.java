import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

//0.5 set game screen size constant to do better changes in future 维护扩展改变

// 0.6 use keyboard to control tank

//0.7 deals with tank class, 若想要建立100个坦克 若无类会很麻烦 每次加属性 每次都要修改 那是面向过程 我们要面向对象 所以需要一个坦克类

//0.8 add tank 8 moving directions

//0.9 problem appears, as long as we press the direction before, it will always be true
// use key Released func

public class TankClient extends Frame{//extends Frame so u can draw a canvas

    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 1200;

   // int x = 50, y = 50;//tank's beginning position
    Tank myTank = new Tank(50, 50);


    Image offScreenImage = null;// repaint -> update -> paint so in update, we need to put image to offScreenImage

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //offScreenImage has a drawing pen, we get pen first then draw on offScreenPage
        Graphics gOffScreenImage = offScreenImage.getGraphics();
        //each time i draw, i draw the background to avoid tank to be a line on screen
        Color c = gOffScreenImage.getColor();
        gOffScreenImage.setColor(Color.GREEN);
        gOffScreenImage.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreenImage.setColor(c);

        paint(gOffScreenImage);
        //now we need to use pen from g of the real screen to draw
        g.drawImage(offScreenImage, 0, 0, null);// 0,0 is left up corner position
    }


    public void lauchFrame(){
        this.setLocation(400, 300);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("Tank War game is locally running. ");
        this.addWindowListener(new WindowAdapter() {//匿名类
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);//不让改变窗口大小
        this.setBackground(Color.GREEN);

        this.addKeyListener(new KeyMonitor());

        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args){
        TankClient tc = new TankClient();
        tc.lauchFrame();

    }

    @Override
    public void paint(Graphics g) {
        //code is moved to tank class to use 面向对象
//        //g 有前景色 可以取出来 然后设置成你想要的颜色
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, 30, 30);// draw the circle tank, we wanna make tank move, so we need its position to change
//        g.setColor(c);//set the color back to the original one
//
//        //y += 5;  //test if thread can move the tank

        myTank.draw(g);

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

    //implements KeyListener 实现接口不好 里面你不关心的方法全得重写 所以用继承
    private class KeyMonitor extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
//System.out.println("ok"); // test if key montior is added
            //code is moved to tank class to use 面向对象
//            int key = e.getKeyCode();//用这个code来查看按了哪个按键
//            switch (key){
//                case KeyEvent.VK_RIGHT :
//                    x += 5;
//                    break;
//                case KeyEvent.VK_LEFT :
//                    x -= 5;
//                    break;
//                case KeyEvent.VK_UP :
//                    y -= 5;
//                    break;
//                case KeyEvent.VK_DOWN :
//                    y += 5;
//                    break;
//            }

            myTank.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
// we use double buffer to deal with tank glare in 0.4_1