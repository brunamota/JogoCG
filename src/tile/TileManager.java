package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import jogocg.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxMundoColuna][gp.maxMundoLinha];
        getTileImage();
        loadMap("/mapa/world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/fundo/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/fundo/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/fundo/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/fundo/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/fundo/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/fundo/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxMundoColuna && row < gp.maxMundoLinha) {
                String line = br.readLine();
                while (col < gp.maxMundoColuna) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxMundoColuna) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxMundoColuna && worldRow < gp.maxMundoLinha) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileFinal;
            int worldY = worldRow * gp.tileFinal;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileFinal > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileFinal < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileFinal > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileFinal < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileFinal, gp.tileFinal, null);
            }

            worldCol++;

            if (worldCol == gp.maxMundoColuna) {
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
