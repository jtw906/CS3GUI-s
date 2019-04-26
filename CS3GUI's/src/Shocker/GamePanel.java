package Shocker;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static int width,height;
    private Thread thread;
    private BufferedImage img;
    private Graphics2D g;
    private boolean running=false;
    private int x = 0;

    public GamePanel(int width, int height){
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        this.width=width;
        this.height=height;
    }
    public void run(){
        init();
        final double GAME_HERTZ=60.0;
        //time before update
        final double TBU=1000000000/GAME_HERTZ;
        //Most updates before render
        final int MUBR = 5;
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
        final double TARGET_FPS = 60;
        //Total time before render
        final double TTBR = 1000000000/TARGET_FPS;
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime/1000000000);
        int oldFrameCount = 0;

        while (running){
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now-lastUpdateTime)>TBU)&&(updateCount<MUBR)){
                update();
                input();
                lastUpdateTime+=TBU;
                updateCount++;
            }
            if(now-lastUpdateTime>TBU){
                lastUpdateTime = now-TBU;
            }
            input();
            render();
            draw();
            lastRenderTime=now;
            frameCount++;
            //Video 2 stop time 12:49
        }
    }
    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread=new Thread(this,"GameThread");
            thread.start();
        }
    }
    public void init(){
        running=true;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D)img.getGraphics();
    }
    public void update(){

    }
    public void render(){
        if(g!= null){
            g.setColor(new Color(66,134,244));
            g.fillRect(0,0,width,height);
        }
    }
    public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img,0,0,width,height,null);
        g2.dispose();
    }
}
