package game2d.tile;

import java.awt.image.BufferedImage;

public class TileBuilder {
    public BufferedImage image;
    public boolean collidable;

    public static class builder {
        TileBuilder tile;

        public builder() {
            tile = new TileBuilder();
        }

        public TileBuilder build() {
            return tile;
        }

        public builder image(BufferedImage image) {
            tile.image = image;
            return this;
        }

        public builder collidable(boolean collidable) {
            tile.collidable = collidable;
            return this;
        }
    }
}
