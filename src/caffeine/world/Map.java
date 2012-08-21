package caffeine.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.gfx.Screen;

public class Map implements Iterable<Tile> {
  protected List<Entity> entities = new ArrayList<Entity>();
  private static int numMaps = 0;
  protected int id;
  protected int numRows, numCols;
  public final static int tileSize = 16;
  protected Tile[][] map;

  public static Comparator<Entity> spriteSorter = new Comparator<Entity>(){
    @Override
    public int compare(Entity e1, Entity e2) {
      if (e1 instanceof ItemEntity) {
        return -1;
      } else if (e2 instanceof ItemEntity) {
        return 1;
      } else {
        return  e1.getLoc().y - e2.getLoc().y;
      }
    }

  };

  public static final int[][] defaultMapData = {
    {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
    {7,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,7},
    {7,32,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,33,65,33,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,34,7,32,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,34,7,32,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,34,7,32,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,33,1,33,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,32,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,34,7},
    {7,64,65,65,65,65,65,65,65,65,65,65,65,65,65,65,65,66,7},
    {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},

  };

  public Map(int[][] data){
    id = Map.numMaps++;

    numRows = data.length;
    numCols = data[0].length;
    map = new Tile[numCols][numRows];

    for (int y = 0; y < data.length; y++)
      for (int x = 0; x < data[y].length; x++){
        Tile t = new Tile(data[y][x]);
        map[x][y] = t;
      }
  }

  public Map(int cols, int rows) {
    id = Map.numMaps++;
    numRows = rows;
    numCols = cols;
    map = new Tile[cols][rows];

    for (int y = 0; y < numRows; y++)
      for (int x = 0; x < numCols; x++)
        map[x][y] = new Tile(33);
  }

  public Collection<Entity> entities() {
    return entities;
  }

  public List<Entity> getEntities(int x0, int y0, int x1, int y1){
    List<Entity> result = new ArrayList<Entity>();
    for (Entity e : entities) {
      if (e.intersects(x0, y0, x1, y1)) {
        result.add(e);
      }
    }
    return result;
  }

  public int getID() {
    return id;
  }

  protected boolean inRange(int x, int y){
    return 0 <= x && x < numCols && 0 <= y && y < numRows;
  }

  public Tile getTile(int x, int y) {
    return inRange(x, y) ? map[x][y] : null;
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

  public List<Tile> getTiles(int x0, int y0, int x1, int y1) {
    List<Tile> result = new ArrayList<Tile>();
    for (int j = y0 / tileSize * tileSize; j <= y1; j += tileSize)
      for (int i = x0 / tileSize * tileSize; i <= x1; i += tileSize)
        if (isValidLoc(i, j))
          result.add(getTileAt(i, j));
    return result;
  }

  public int height() {
    return numRows * Map.tileSize;
  }

  public int tileSize() {
    return Map.tileSize;
  }

  public boolean isValidLoc(Loc loc) {
    return isValidLoc(loc.x, loc.y);
  }

  public boolean isValidLoc(int x, int y){
    return 0 <= x && x < numCols * Map.tileSize && 0 <= y
        && y < numRows * Map.tileSize;
  }

  public void tick() {

    for(int y = 0; y < numRows; y++){
      for(int x = 0; x < numCols; x++){
        getTile(x,y).tick();
      }
    }

    for (int i = 0; i < entities.size(); i++) {
      Entity e = entities.get(i);
      e.tick();
      if (e.isRemoved()) {
        entities.remove(i--);
      }
    }
  }

  @Override
  public String toString() {
    String s = "map " + numCols + " " + numRows + " " + Map.tileSize + " ";
    for (int y = 0; y < numRows; y++)
      for (int x = 0; x < numCols; x++)
        s += map[x][y];
    s += "\n";
    return s;
  }

  public void renderBackground(Screen screen) {
    for(int x = 0; x < numCols; x++)
      for(int y = 0; y < numRows; y++){
        Tile tile = map[x][y];

        screen.render(tile.getSprite(), x*tileSize, y* tileSize);
      }
  }

  public void renderSprites(Screen screen){
    List<Entity> sprites = new ArrayList<Entity>();
    sprites.addAll(entities);
    Collections.sort(sprites, spriteSorter);
    for (Entity e : sprites) {
      e.render(screen);
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
        return x < numCols && y < numRows;
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


  public void addEntity(Entity e, int x, int y) {
    entities.add(e);
  }

  public void removeEntity(Entity e) {
    entities.remove(e.getID());
  }

  public boolean isEmpty() {
    return entities.isEmpty();
  }

}
