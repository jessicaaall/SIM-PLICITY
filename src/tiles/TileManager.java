package tiles;

import game.WorldPanel;
import tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    WorldPanel wp;
    Tile[] tiles;
    public TileManager(WorldPanel worldPanel){
        wp = worldPanel;
        tiles = new Tile[10];
        getTileImage();
    }
    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/grassTile.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d, int x, int y){
        g2d.drawImage(tiles[0].image, x,y, wp.UNIT_SIZE, wp.UNIT_SIZE, null);
    }
}
