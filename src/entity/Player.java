package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import jogocg.GamePanel;
import jogocg.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler tecla;
    
    public final int screenX;
    public final int screenY;  
    
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler tecla) {
        this.gp = gp;
        this.tecla = tecla;
        
        screenX = gp.telaLargura/2 - (gp.tileFinal/2);
        screenY = gp.telaAltura/2 - (gp.tileFinal/2);
        
        solidArea = new Rectangle(8, 16, 32,32);
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImagePlayer();
    }

    public void setDefaultValues() {
        worldX = gp.tileFinal * 23;
        worldY = gp.tileFinal * 21;
        speed = 4;
        direction = "down";
    }

    public void getImagePlayer() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(tecla.upPressed == true || tecla.downPressed == true || tecla.leftPressed == true || tecla.rightPressed == true){
            if (tecla.upPressed == true) {
                direction = "up";               
            }
            if(tecla.downPressed == true) {
                direction = "down";                
            }
            if(tecla.leftPressed == true) {
                direction = "left";                
            }
            if(tecla.rightPressed == true) {
                direction = "right";                
            }
            
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            if(collisionOn == false){
                switch (direction) {
                    case "up":
                    worldY -= speed;
                    break;
                    case "down":
                    worldY += speed;
                    break; 
                    case "left":
                    worldX -= speed;
                    break; 
                    case "right":
                    worldX += speed;
                    break; 
                }
            }
            
            spriteCounter++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
            }
        }
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objName = gp.obj[i].name;
            
            switch (objName) {
                case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;
                break;
                case "Door":
                if(hasKey > 0){
                    gp.playSE(3);
                    gp.obj[i] = null;
                    hasKey--;
                }    
                break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                break;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
            break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
            break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
            break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileFinal, gp.tileFinal, null);
    }
}

