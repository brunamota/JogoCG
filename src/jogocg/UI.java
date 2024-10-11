
package jogocg;

import config_objetos.ObjectKey;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter  = 0;
    
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        ObjectKey key = new ObjectKey();
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        
        if(gameFinished == true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
            String text;
            int tamanhoTexto, x, y;
            
            text = "Você achou o tesouro!";
            tamanhoTexto = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.telaLargura/2 - tamanhoTexto/2;
            y = gp.telaAltura/2 + (gp.tileFinal*3);
            g2.drawString(text, x, y);
            
            text = "Seu tempo foi: " + dFormat.format(playTime) + "!";
            tamanhoTexto = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.telaLargura/2 - tamanhoTexto/2;
            y = gp.telaAltura/2 + (gp.tileFinal*4);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = "Parabéns!";
            tamanhoTexto = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.telaLargura/2 - tamanhoTexto/2;
            y = gp.telaAltura/2 + (gp.tileFinal*2);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
        }else{
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileFinal/2, gp.tileFinal/2, gp.tileFinal, gp.tileFinal, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);
        
        playTime += (double) 1/60;
        g2.drawString("Tempo: " + dFormat.format(playTime), gp.tileFinal*11, 65);
        
        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gp.tileFinal/2, gp.tileFinal*5);
            
            messageCounter++;
            
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
          }
        }    
    }
}
