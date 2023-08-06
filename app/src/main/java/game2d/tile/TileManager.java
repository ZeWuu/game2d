package game2d.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game2d.entity.Tiles;
import game2d.main.GamePanel;
import game2d.main.Main;

public class TileManager {

    GamePanel gp;
    Map<Tiles, Tile> tiles = new HashMap<Tiles, Tile>();
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tiles.put(Tiles.GOLDEN_GRASS, new Tile.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/GoldenGrass.png")))
                    .build());
            tiles.put(Tiles.GOLDEN_TREE, new Tile.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/GoldenTree.png")))
                    .build());
            tiles.put(Tiles.WATER, new Tile.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Water.png"))).build());
            tiles.put(Tiles.BRICK_WALL,
                    new Tile.builder()
                            .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/BrickWall.png")))
                            .build());
            tiles.put(Tiles.SAND, new Tile.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Sand.png"))).build());
            tiles.put(Tiles.ROAD, new Tile.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Road.png"))).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(tiles);
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenColumn && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenColumn) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenColumn) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenColumn && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles.get(Tiles.values()[tileNum]).image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenColumn) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
