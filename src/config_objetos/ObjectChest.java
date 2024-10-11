
package config_objetos;

import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjectChest extends SuperObject{
    public ObjectChest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/chest.png"));
        } catch (IOException e) {
        }
        
        collision = true;
    }
}
