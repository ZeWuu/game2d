package game2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import game2d.entity.Player;
import game2d.main.Constants;
import game2d.main.GamePanel;
import game2d.main.KeyHandler;
import game2d.tile.TileManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PlayerTest {
    private KeyHandler key;
    private TileManager tm;
    private GamePanel gp;
    private Player player;

    @BeforeEach
    void setUp() {
        key = mock(KeyHandler.class);
        tm = mock(TileManager.class);
        gp = mock(GamePanel.class);
        player = new Player(key, tm, gp);
    }

    @Test
    void testSetDefaultValues() {
        player.setDefaultValues();

        assertEquals(Constants.tileSize * 8, player.entityXPos);
        assertEquals(Constants.tileSize * 56, player.entityYPos);
        assertEquals(4, player.speed);
        assertEquals("down", player.direction);
    }

    @Test
    void testCheckBorderCollision() {
        player.entityXPos = -10;
        player.entityYPos = -10;
        player.checkBorderCollision();

        assertEquals(0, player.entityXPos);
        assertEquals(0, player.entityYPos);

        player.entityXPos = Constants.worldWidth + 10;
        player.entityYPos = Constants.worldHeight + 10;
        player.checkBorderCollision();

        assertEquals(Constants.worldWidth - Constants.tileSize, player.entityXPos);
        assertEquals(Constants.worldHeight - Constants.tileSize, player.entityYPos);
    }
    @Test 
    void checkTileCollision(){
        Player player = new Player(key, tm, gp);

        int entityX = 10;
        int entityY = 20;

        Mockito.when(tm.getTileID(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);
        Mockito.when(tm.isTileCollidable(Mockito.anyInt())).thenReturn(true);

        boolean result = player.checkTileCollision(entityX, entityY);

        assertTrue(result);
    }

    @Test
    void testEntityXPos() {
        assertEquals(Constants.tileSize * 8, player.entityXPos);
    }

    @Test
    void testGetEntityYPos() {
        assertEquals(Constants.tileSize * 56, player.entityYPos);
    }

}