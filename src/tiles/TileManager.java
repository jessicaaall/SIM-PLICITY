package tiles;

import game.HousePanel;
import game.RoomPanel;
import game.WorldPanel;
import tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    WorldPanel wp;
    RoomPanel rp;
    Tile[] tiles = new Tile[10];;
    public TileManager(WorldPanel worldPanel){
        wp = worldPanel;
        getTileImage();
    }
    public TileManager(RoomPanel rp){
        this.rp = rp;
        getTileImage();
    }
    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[1] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/grassTile.png")));
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/houseTile1.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d, int x, int y){
        g2d.drawImage(tiles[0].image, x,y, wp.UNIT_SIZE, wp.UNIT_SIZE, null);
    }
    public void drawFloor(Graphics2D g2d, int x, int y){
        g2d.drawImage(tiles[1].image, x, y, rp.unitSize, rp.unitSize, null);
    }
}
