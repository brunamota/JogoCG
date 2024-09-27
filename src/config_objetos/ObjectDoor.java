
package config_objetos;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{
    public ObjectDoor(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/Door.png"));
        } catch (IOException e) {
        }
        collision = true;
    }
}
