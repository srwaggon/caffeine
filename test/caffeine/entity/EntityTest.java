package caffeine.entity;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import caffeine.world.Map;
import caffeine.world.tile.Tile;

public class EntityTest {
  
  private Entity testObject;
  private Map map = new Map("M 0 W 3 H 3 .........\n");
  
  @Before
  public void setUp() {
    testObject = new Entity();
    testObject.setMap(map);
  }

  @Test
  public void getTiles_ReturnsTilesOccupiedByEntity() {
    testObject.setLoc(8, 8, 0);
    int top = 0;
    int left = 0;
    int right = Map.tileSize;
    int bottom = Map.tileSize;
    Tile topLeft = map.getTileAt(left, top);
    Tile topRight = map.getTileAt(right, top);
    Tile bottomLeft = map.getTileAt(left, bottom);
    Tile bottomRight = map.getTileAt(right, bottom);
    
    List<Tile> tiles = testObject.getTiles();
    
    assertEquals(4, tiles.size());
    assertTrue(tiles.contains(topLeft));
    assertTrue(tiles.contains(topRight));
    assertTrue(tiles.contains(bottomLeft));
    assertTrue(tiles.contains(bottomRight));
  }
  
  @Test
  public void getTiles_ReturnsTilesOccupiedByEntity_GivenEntityHasRelocated() {
    // [. . .]
    // [. x x]
    // [. x x]
    testObject.setLoc(Map.tileSize * 2, Map.tileSize * 2, 0);
    int top = Map.tileSize;
    int left = Map.tileSize;
    int right = Map.tileSize * 2;
    int bottom = Map.tileSize * 2;
    Tile topLeft = map.getTileAt(left, top);
    Tile topRight = map.getTileAt(right, top);
    Tile bottomLeft = map.getTileAt(left, bottom);
    Tile bottomRight = map.getTileAt(right, bottom);
    
    List<Tile> tiles = testObject.getTiles();
    
    assertTrue(tiles.contains(topLeft));
    assertTrue(tiles.contains(topRight));
    assertTrue(tiles.contains(bottomLeft));
    assertTrue(tiles.contains(bottomRight));
    assertEquals(4, tiles.size());
  }
}
