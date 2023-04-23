import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.File;
import java.util.*;
import javax.swing.border.EtchedBorder;
import javax.sound.sampled.*;
public class GameField extends  JFrame{

    private DrawingPanel drawingPanel;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameField frame = new GameField();
                    frame.setVisible(true);
                    frame.addContents();
                    File file = new File("avicii.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameField(){
        super("RockPaperScissors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.CYAN);
        drawingPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        drawingPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(drawingPanel);

        Animator animator =new Animator(this);

        Timer timer = new Timer(50, animator);

        timer.start();
    }

    public void addContents(){
        DrawingPanel dPanel = this.getDrawingPanel();
        Random rand = new Random();

        for(int i = 0; i<rand.nextInt(4,8); i++){
            dPanel.addDrawableItem(new RockPaperScissors(dPanel, "Paper.gif", rand.nextInt(2,7), rand.nextBoolean(), rand.nextBoolean()));
            dPanel.addDrawableItem(new RockPaperScissors(dPanel, "Scissors.gif", rand.nextInt(2,7), rand.nextBoolean(), rand.nextBoolean()));
            dPanel.addDrawableItem(new RockPaperScissors(dPanel, "Rock.gif", rand.nextInt(2,7), rand.nextBoolean(), rand.nextBoolean()));
        }


        Image image1Rock = Toolkit.getDefaultToolkit().getImage("Rock.gif");
        Image image2Paper = Toolkit.getDefaultToolkit().getImage("Paper.gif");
        Image image3Scissors = Toolkit.getDefaultToolkit().getImage("Scissors.gif");


        dPanel.initializeEverything();
//        try{
//            Thread.sleep(15000);
//        }
//        catch (InterruptedException e){
//
//        }
//        int num1=0,num2=0,num3=0;
//        ArrayList<DrawableItem> drawables = dPanel.getDrawableItems();
//        for(DrawableItem item: dPanel.getDrawableItems()){
//            DrawableImage drawable = (DrawableImage) item;
//            if(drawable.getImage()==image1Rock){
//                num1++;
//            }
//            else if(drawable.getImage()==image2Paper){
//                num2++;
//            }
//            else if(drawable.getImage()==image3Scissors){
//                num3++;
//            }
//        }
//        if((num1==0 & num2==0) || (num1 ==0 && num3==0) || (num2==0 && num3==0)){
//            gameOver();
//        }



    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public int gameOver(){

        System.out.println("Game Over");
        return 0;
    }
}
