import java.awt.*;

/**
 * Created by zhiboliu on 16-7-9.
 */


//0.1 create the game window

public class TankClient extends Frame{//extends Frame so u can draw a canvas

    public void lauchFrame(){
        this.setLocation(400, 300);
        this.setSize(1600, 1200);
        setVisible(true);
    }

    public static void main(String[] args){
        TankClient tc = new TankClient();
        tc.lauchFrame();

    }
}
