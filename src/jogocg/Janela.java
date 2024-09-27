
package jogocg;

import javax.swing.JFrame;

public class Janela extends JFrame{
    
    JFrame janela = new JFrame();
    
    Janela(){
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Caça ao Tesouro");
        
        GamePanel gamePanel = new GamePanel();
        
        janela.add(gamePanel);
        
        janela.pack();
        
        gamePanel.setUpGame();
        
        gamePanel.startGameThread();
        
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    
}
