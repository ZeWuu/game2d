package game2d.tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image; 
    public boolean collision = false;

    public static class builder {
        Tile tile;

        public builder() {
            tile = new Tile();
        }

        public Tile build() {
            return tile;
        }

        public builder image(BufferedImage image) {
            tile.image = image;
            return this;
        }
    }
}
