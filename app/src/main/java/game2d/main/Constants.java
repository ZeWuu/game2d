package game2d.main;

public class Constants {
    public static final int notScaledTileSize = 16;
    public static final int scale = 3;
    public static final int FPS = 60;

    public static final int tileSize = notScaledTileSize * scale;

    public static final int visibleColumns = 16;
    public static final int visibleRows = 12;
    public static final int maxWorldCol = 64;
    public static final int maxWorldRow = 64;

    public static final int visibleWidth = tileSize * visibleColumns;
    public static final int visibleHeight = tileSize * visibleRows;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;

    public static final int playerX = visibleWidth / 2 - (tileSize / 2);
    public static final int playerY = visibleHeight / 2 - (tileSize / 2);
}
