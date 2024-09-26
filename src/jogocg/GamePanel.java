
package jogocg;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    public final int tileOriginal = 16;
    public final int escala = 3;
    
    public final int tileFinal = tileOriginal * escala;
    
    //4x3
    public final int maxTelaColuna = 16;
    public final int maxTelaLinha = 12;
    public final int telaLargura = tileFinal * maxTelaColuna; //769 pixels
    public final int telaAltura = tileFinal * maxTelaLinha; //576 pixels
    
    public final int maxMundoColuna = 50;
    public final int maxMundoLinha = 50;
    public final int mundoLargura = tileFinal * maxMundoColuna;
    public final int mundoAltura = tileFinal * maxMundoLinha;
    
    Thread gameThread;
    
    int FPS = 60;
    
    KeyHandler tecla = new KeyHandler();
    
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public Player player = new Player(this, tecla);
    
    TileManager tileM = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(telaLargura, telaAltura));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
        this.addKeyListener(tecla);
        this.setFocusable(true);
    }
    
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        double desenhaIntervalo = 1000000000/FPS; //0,016
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/desenhaIntervalo;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
           
        }
    }
    
    public void update(){
        player.update();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);
        
        player.draw(g2);
        g2.dispose();
        
    }
    
}
