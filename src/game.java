import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class game extends JPanel implements ActionListener,KeyListener{

    int bwid=800;
    int bhei=300;
    Image dino;
    Image dinodead;
    Image dinojump;
    Image cactus1;
    Image cactus2;
    Image cactus3;
    JLabel l;
    class INIT{
        int hei,wid,x,y;
        Image img;
        public INIT(int hei,int wid,int x,int y,Image img){
            this.hei=hei;
            this.wid=wid;
            this.x=x;
            this.y=y;
            this.img=img;
        }
    }
    // DINO
    int Dhei=94;
    int dwid=88;
    int dX=100;
    int dY=bhei-Dhei;
    INIT DINO;
    Timer di;
    int velocityY=0;
    int velocityX=-12;
    int gravity=1;

    // cactus
    int c1wid=34;
    int c2wid=69;
    int c3wid=102;
    int chei=70;
    int cX=800;
    int cY=bhei-chei;
    ArrayList<INIT> cactuses;
    INIT a;
    Timer ctime;
    Random r=new Random();
    Boolean gameOver=false;
    double score=0;
    public void move(){
      velocityY+=gravity;
      DINO.y+=velocityY;
      if(DINO.y>dY){
        DINO.y=dY;
        velocityY=0;
        DINO.img=dino;
      }
      for(int i=0;i<cactuses.size();i++){
        a=cactuses.get(i);
        a.x+=velocityX;
        if(collision(DINO,a)){
          gameOver=true;
          ctime.stop();
          DINO.img=dinodead;
          di.stop();

        }
      }
    }
    Boolean collision(INIT a,INIT b){
      return (a.x<b.x+b.wid&&
              a.x+a.wid>b.x&&
              a.y<b.y+b.hei&&
              a.y+a.hei>b.y);
      
    }
    game(){
        setPreferredSize(new Dimension(bwid,bhei));
        setBackground(Color.lightGray);
        addKeyListener(this);
        setFocusable(true);
        dino= new ImageIcon(getClass().getResource("\\img\\dino-run.gif")).getImage();
        dinodead= new ImageIcon(getClass().getResource("\\img\\dino-dead.png")).getImage();
        dinojump= new ImageIcon(getClass().getResource("\\img\\dino-jump.png")).getImage();
        cactus1= new ImageIcon(getClass().getResource("\\img\\cactus1.png")).getImage();
        cactus2= new ImageIcon(getClass().getResource("\\img\\cactus2.png")).getImage();
        cactus3= new ImageIcon(getClass().getResource("\\img\\cactus3.png")).getImage();
        // dino
        DINO= new INIT(Dhei,dwid,dX,dY,dino);
        di=new Timer(1000/60,this);
          di.start();

        //cactus
        cactuses=new ArrayList<INIT>();
        
        
        ctime=new Timer(1500,new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            cacplace();
                                  }
                    });
                    ctime.start();    
           }
        protected void cacplace() {
          int i=r.nextInt(0,3);
      
          if(i==0){
         a=new INIT(chei,c1wid,cX,cY,cactus1);
        cactuses.add(a);}
        else if (i==1) {
          a=new INIT(chei,c2wid,cX,cY,cactus2);
          cactuses.add(a);
        }
        else{
        a=new INIT(chei,c3wid,cX,cY,cactus3);
        cactuses.add(a);}
        System.out.println(cactuses.size());
                }
                @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.drawImage(DINO.img,DINO.x,DINO.y,DINO.wid,DINO.hei,null);
      
      System.out.println(a);
      for (int i=0;i<cactuses.size();i++){
        a=cactuses.get(i);
      g.drawImage(a.img,a.x,a.y,a.wid,a.hei,null);
    }
    g.drawString("Your score is "+(int)score,10,20);
     score+=.2;
     if(gameOver){
       l=new JLabel("GAME OVER (ENTER SPACE TO RESTART)");
       
      l.setBounds(300,100,300,100);
      add(l);
     }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      repaint();
      move();
    }
    @Override
    public void keyTyped(KeyEvent e) { 
    }
    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_SPACE){
        System.out.println("jump  ");
        if(DINO.y==dY){
          DINO.img=dinojump;
          velocityY=-17;
          if(gameOver){
            di.start();
            ctime.start();
            cactuses.clear();
            score=0;
            DINO.img=dino;
            DINO.y=dY;
            velocityY=0;
            gameOver=false;
            remove(l);
            
          }
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
