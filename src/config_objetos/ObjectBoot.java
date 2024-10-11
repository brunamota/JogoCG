
package config_objetos;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjectBoot extends SuperObject{    

    public ObjectBoot() {
       name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/boots.png"));
        } catch (IOException e) {
        }
        collision = true;
    }    
}
