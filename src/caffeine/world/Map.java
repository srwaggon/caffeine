package caffeine.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import caffeine.entity.Entity;
import caffeine.gfx.Screen;

public class Map implements Iterable<Tile> {
  protected HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
  private static int numMaps = 0;
  protected int id;
  protected int numRows, numCols;
  public final static int tileSize = 16;
  protected Tile[][] map;

  public static final int[][] defaultMapData = {
    {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
    {7,33,65,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,66,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,34,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,34,33,7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
    {7,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,7},
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
    return entities.values();
  }

  public List<Entity> getEntities(int x0, int y0, int x1, int y1){
    List<Entity> result = new ArrayList<Entity>();
    for (Entity e : entities.values())
      if (e.intersects(x0, y0, x1, y1))
        result.add(e);
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

    Iterator<Tile> tileIterator = iterator();
    while (tileIterator.hasNext()) {
      Tile t = tileIterator.next();
      t.tick();
    }
    Iterator<Entity> entityIter = entities.values().iterator();
    while (entityIter.hasNext()) {
      Entity e = entityIter.next();
      if (e.isRemoved())
        entityIter.remove();
      else e.tick();
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
    for (Entity e : entities())
      e.render(screen);
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


  public void addEntity(Entity e) {
    entities.put(e.getID(), e);
  }

  public boolean containsEntityByID(int id) {
    return entities.containsKey(id);
  }

  public boolean containsEntity(Entity entity) {
    return entities.containsValue(entity);
  }


  public Entity getEntity(int id) {
    return containsEntityByID(id) ? entities.get(id) : null;
  }

  public void removeEntity(Entity e) {
    entities.remove(e.getID());
  }

  public Collection<Entity> occupants() {
    return entities.values();
  }

  public boolean isEmpty() {
    return entities.isEmpty();
  }

}