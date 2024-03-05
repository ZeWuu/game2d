package game2d.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game2d.main.Constants;
import game2d.main.GamePanel;
import game2d.main.Main;

public class TileManager {

    private GamePanel gp;
    private Map<Tiles, TileBuilder> tiles = new HashMap<Tiles, TileBuilder>();
    public int tilesMap[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tilesMap = new int[Constants.maxWorldCol][Constants.maxWorldRow];
        getTileImage();
        loadMap("/maps/Map.txt");
    }

    private void getTileImage() {
        try {
            tiles.put(Tiles.GOLDEN_GRASS, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/GoldenGrass.png"))).collidable(false)
                    .build());
            tiles.put(Tiles.GOLDEN_TREE, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/GoldenTree.png"))).collidable(true)
                    .build());
            tiles.put(Tiles.WATER, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Water.png"))).collidable(true)
                    .build());
            tiles.put(Tiles.BRICK_WALL,new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/BrickWall.png"))).collidable(true)
                    .build());
            tiles.put(Tiles.SAND, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Sand.png"))).collidable(false)
                    .build());
            tiles.put(Tiles.ROAD, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/Road.png"))).collidable(false)
                    .build());
            tiles.put(Tiles.LIGHT_BRICK_ROAD, new TileBuilder.builder()
                    .image(ImageIO.read(Main.class.getResourceAsStream("/tiles/LightBrickRoad.png"))).collidable(false)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTileCollidable(int tileID) {
        return tiles.get(Tiles.values()[tileID]).collidable;
    }

    private void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < Constants.maxWorldRow; i++) {
                String line = br.readLine();
                String[] tilesNumbers = line.split(" ");
                for (int j = 0; j < Constants.maxWorldCol; j++) {
                    int num = Integer.parseInt(tilesNumbers[j]);
                    tilesMap[j][i] = num;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < Constants.maxWorldRow; i++) {
            for (int j = 0; j < Constants.maxWorldCol; j++) {
                int tileNum = tilesMap[j][i];
                int worldX = j * Constants.tileSize;
                int worldY = i * Constants.tileSize;
                int screenX = worldX - gp.player.entityXPos + Constants.playerX;
                int screenY = worldY - gp.player.entityYPos + Constants.playerY;
                g2.drawImage(tiles.get(Tiles.values()[tileNum]).image, screenX, screenY, Constants.tileSize,
                        Constants.tileSize, null);
            }
        }
    }

    public Map<Tiles, TileBuilder> getTiles() {
        return tiles;
    }

    public int getTileID(int tileX, int tileY) {
        if (tileX < 0 || tileX >= Constants.maxWorldCol || tileY < 0 || tileY >= Constants.maxWorldRow) {
            return 0;
        }
        return tilesMap[tileX][tileY];
    }
}
