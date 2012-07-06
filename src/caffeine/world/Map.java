package caffeine.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import caffeine.entity.Entity;
import caffeine.view.Spritesheet;

public class Map implements Iterable<Tile> {
  private static int numMaps = 0;
  protected int id;
  protected int numRows, numCols;
  protected static int tileSize = 32;
  protected Tile[][] map;

  public static String defaultMapString = "40 20 "
      + "########################################"
      + "#...@...@...@@.........................#"
      + "#............@.........................#"
      + "#.....@......@.........................#"
      + "#@..@........@.........................#"
      + "#.........@..@.........................#"
      + "#..@....@....@@........................#"
      + "#......@.@...@.........................#"
      + "#@....@...@..@.........................#"
      + "#......@.@...@.........................#"
      + "#....@..@....@.........................#"
      + "#...........@..........................#"
      + "#@.........@...........................#"
      + "#@@@@@@@@@@............................#"
      + "#.....@................................#"
      + "#......................................#"
      + "#......................................#"
      + "#......................................#"
      + "#......................................#"
      + "########################################";

  public Map(int cols, int rows) {
    id = Map.numMaps++;
    numRows = rows;
    numCols = cols;
    map = new Tile[cols][rows];

    for (int y = 0; y < numRows; y++) {
      for (int x = 0; x < numCols; x++) {
        map[x][y] = new Tile(x, y, Map.tileSize, this);
      }
    }
  }

  public int getID() {
    return id;
  }

  public List<Tile> getOverlappingTiles(Rectangle r) {
    List<Tile> overlapping = new LinkedList<Tile>();
    for (int y = r.y / Map.tileSize; y <= (r.y + r.height) / Map.tileSize; y++) {
      for (int x = r.x / Map.tileSize; x <= (r.x + r.width) / Map.tileSize; x++) {
        overlapping.add(getTile(x, y));
      }
    }
    return overlapping;
  }

  public Tile getTile(int x, int y) {
    if (x < 0) {
      x = 0;
    }
    if (y < 0) {
      y = 0;
    }
    if (x >= numCols) {
      x = numCols - 1;
    }
    if (y >= numRows) {
      y = numRows - 1;
    }
    return map[x][y];
  }

  public Tile getTileAt(int x, int y) {
    return getTile(x / Map.tileSize, y / Map.tileSize);
  }

  public Tile getTileAt(Loc l) {
    if (l.mapID != id) {
      // throw new MismatchingMapIDException();
    }
    return getTileAt(l.x, l.y);
  }

  public int numCols() {
    return numCols;
  }

  public int numRows() {
    return numRows;
  }

  public int height() {
    return numRows * Map.tileSize;
  }

  public int tileSize() {
    return Map.tileSize;
  }

  public boolean validLoc(Loc loc) {
    int x = loc.x;
    int y = loc.y;
    return 0 <= x && x < numCols * Map.tileSize && 0 <= y
        && y < numRows * Map.tileSize;
  }

  protected boolean inRange(int x, int y) {
    return x >= 0 && x < numCols && y >= 0 && y < numRows;
  }

  public void tick() {
    Set<Entity> entities = new HashSet<Entity>();
    Iterator<Tile> tileIterator = iterator();

    while (tileIterator.hasNext()) {
      Tile t = tileIterator.next();
      entities.addAll(t.occupants());
      t.tick();
    }
    for (Entity e : entities) {
      e.tick(this);
    }
  }

  @Override
  public String toString() {
    String s = "map " + numCols + " " + numRows + " " + Map.tileSize + " ";
    for (int y = 0; y < numRows; y++) {
      for (int x = 0; x < numCols; x++) {
        s += map[x][y];
      }
    }
    s += "\n";
    return s;
  }

  public void renderTiles(Graphics2D g2, Spritesheet tilesheet) {
    Set<Entity> entities = new HashSet<Entity>();
    /* Draw the world, tile by tile */
    Iterator<Tile> tileIterator = iterator();
    while (tileIterator.hasNext()) {
      Tile tile = tileIterator.next();
      int spriteID = tile.getSpriteID();
      Image img = tilesheet.get(spriteID);
      g2.drawImage(img, tile.x * Map.tileSize, tile.y
          * Map.tileSize, Map.tileSize, Map.tileSize, null);
    }
  }

  public void renderEntities(Graphics2D g2) {
    for (Entity e : entities()) {
      e.render(g2);
    }
  }

  public int width() {
    return numCols * Map.tileSize;
  }

  @Override
  public Iterator<Tile> iterator() {
    return new Iterator<Tile>() {
      int x = 0;
      int y = 0;

      @Override
      public boolean hasNext() {
        return inRange(x, y);
      }

      @Override
      public Tile next() {
        Tile t = getTile(x, y);
        if (++x == numCols) {
          x = 0;
          y++;
        }
        return t;
      }

      @Override
      public void remove() {
        // :V Yeah, no.
      }
    };
  }

  public Collection<Entity> entities() {
    Set<Entity> entities = new HashSet<Entity>();
    Iterator<Tile> tileIt = iterator();
    while (tileIt.hasNext()) {
      Tile t = tileIt.next();
      entities.addAll(t.occupants());
    }
    return entities;
  }

  public List<Entity> getEntities(int x0, int y0, int x1, int y1){
    List<Entity> result = new ArrayList<Entity>();
    for (int i = x0; i <= x1; i += tileSize){
      for (int j = y0; j <= y1; j += tileSize){
        if( inRange(i, j) ){
          Tile t = getTileAt(i, j);
          for (Entity e : t.occupants()){
            if (e.getHitbox().intersects(x0, y0, x1, y1)){
              result.add(e);
            }
          }
        }
      }
    }
    return result;
  }
}