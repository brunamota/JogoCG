
package jogocg;

import config_objetos.ObjectChest;
import config_objetos.ObjectDoor;
import config_objetos.ObjectKey;


public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = 23 * gp.tileFinal;
        gp.obj[0].worldY = 7 * gp.tileFinal;
        
        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = 23 * gp.tileFinal;
        gp.obj[1].worldY = 40 * gp.tileFinal;
        
        gp.obj[2] = new ObjectKey();
        gp.obj[2].worldX = 38 * gp.tileFinal;
        gp.obj[2].worldY = 8 * gp.tileFinal;
        
        gp.obj[3] = new ObjectDoor();
        gp.obj[3].worldX = 10 * gp.tileFinal;
        gp.obj[3].worldY = 11 * gp.tileFinal;
        
        gp.obj[4] = new ObjectDoor();
        gp.obj[4].worldX = 8 * gp.tileFinal;
        gp.obj[4].worldY = 28 * gp.tileFinal;
        
        gp.obj[5] = new ObjectDoor();
        gp.obj[5].worldX = 12 * gp.tileFinal;
        gp.obj[5].worldY = 22 * gp.tileFinal;
        
        gp.obj[6] = new ObjectChest();
        gp.obj[6].worldX = 10 * gp.tileFinal;
        gp.obj[6].worldY = 7 * gp.tileFinal;
    }
    
}
