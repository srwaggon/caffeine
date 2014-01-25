package caffeine.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import pixl.Screen;
import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.net.MapListener;
import caffeine.world.tile.Tile;

public class Map implements Serializable {
  private static final long serialVersionUID = -8758917884307083852L;
  // primitive Fields
  protected int backgroundSprite = 4;
  protected int id;
  protected int numRows, numCols;
  public final static int tileSize = 16;
  protected Tile[][] map;
  
  // Object Fields
  protected List<Entity> entities = new ArrayList<Entity>();
  protected static transient List<MapListener> listeners = new ArrayList<MapListener>();
  
  public static Comparator<Entity> spriteSorter = new Comparator<Entity>() {
    @Override
    public int compare(Entity e1, Entity e2) {
      if (e1 instanceof ItemEntity) {
        return -1;
      } else if (e2 instanceof ItemEntity) {
        return 1;
      } else {
        return (int) (e1.getY() - e2.getY());
      }
    }
    
  };
  
  public static final String defaultMapData = "M 0 W 13 H 8 "
  + "DDDDDDDDDDDDD"
  + "D......m..mmD"
  + "D.mmm####mmmD"
  + "D.m~~~~##~m.D"
  + "D.m~~#~##mm.D"
  + "D~~mm###m.m.D"
  + "D~~..mm...mmD"
  + "DDDDDDDDDDDDD";
  
  public Map(int id, int w, int h, String data) {
    this.id = id;
    numCols = w;
    numRows = h;
    
    map = new Tile[numCols][numRows];
    
    for (int i = 0; i < data.length(); i++) {
      int x = i % numCols;
      int y = i / numCols;
      Tile t = Tile.read(x, y, data.charAt(i));
      map[x][y] = t;
    }
  }
  
  /* CONSTRUCTORS */
  public Map(String data) {
    Scanner scan = new Scanner(data);
    
    scan.next(); // M - map id
    id = scan.nextInt();
    
    scan.next(); // W - width
    numCols = scan.nextInt();
    
    scan.next(); // H - height
    numRows = scan.nextInt();
    
    data = scan.next();
    map = new Tile[numCols][numRows];
    
    for (int i = 0; i < data.length(); i++) {
      int x = i % numCols;
      int y = i / numCols;
      Tile t = Tile.read(x, y, data.charAt(i));
      map[x][y] = t;
    }
  }
  
  public void addEntity(Entity e) {
    entities.add(e);
    e.setMap(this);
    for (MapListener l : listeners) {
      l.onAddEntity(e);
    }
  }
  
  public void addMapListener(MapListener listener) {
    listeners.add(listener);
  }
  
  public List<Entity> getEntities() {
    return entities;
  }
  
  public int getBackground() {
    return backgroundSprite;
  }
  
  public List<Entity> getEntities(double left, double top, double right, double bottom) {
    List<Entity> result = new ArrayList<Entity>();
    for (Entity e : getEntities()) {
      if (e.intersects(left, top, right, bottom)) {
        result.add(e);
      }
    }
    return result;
  }
  
  public Entity getEntity(int id) {
    return entities.get(id);
  }
  
  public Entity getEntityByID(String id) {
    for (int i = 0; i < entities.size(); i++) {
      Entity e = entities.get(i);
      if (e.ID.equals(id)) {
        return e;
      }
    }
    return null;
  }
  
  public int getID() {
    return id;
  }
  
  public Tile getTile(int x, int y) {
    return inRange(x, y) ? map[x][y] : null;
  }
  
  public Tile getTileAt(int x, int y) {
    return getTile(x / Map.tileSize, y / Map.tileSize);
  }
  
  public List<Tile> getTiles(double left, double top, double right, double bottom) {
    List<Tile> result = new ArrayList<Tile>();
    for (int j = (int) (top / Map.tileSize * Map.tileSize); j <= bottom; j += Map.tileSize)
      for (int i = (int) (left / Map.tileSize * Map.tileSize); i <= right; i += Map.tileSize)
        if (isValidLoc(i, j))
          result.add(getTileAt(i, j));
    return result;
  }
  
  public Tile getTileSafe(int x, int y) {
    if (x < 0)
      x = 0;
    if (y < 0)
      y = 0;
    if (x >= numCols)
      x = numCols - 1;
    if (y >= numRows)
      y = numRows - 1;
    return map[x][y];
  }
  
  public int height() {
    return numRows * Map.tileSize;
  }
  
  protected boolean inRange(int x, int y) {
    return 0 <= x && x < numCols && 0 <= y && y < numRows;
  }
  
  public boolean isEmpty() {
    return entities.isEmpty();
  }
  
  public boolean isValidLoc(int x, int y) {
    return 0 <= x && x < numCols * Map.tileSize && 0 <= y
        && y < numRows * Map.tileSize;
  }
  
  public void read(String data) {
    Scanner scan = new Scanner(data);
    
    scan.next(); // M - map id
    id = scan.nextInt();
    
    scan.next(); // W - width
    numCols = scan.nextInt();
    
    scan.next(); // H - height
    numRows = scan.nextInt();
    
    data = scan.next();
    
    for (int i = 0; i < data.length(); i++) {
      int x = i % numCols;
      int y = i / numCols;
      Tile t = Tile.read(x, y, data.charAt(i));
      map[x][y] = t;
    }
  }
  
  public boolean removeEntity(String id) {
    Entity e = getEntityByID(id);
    if (e != null) {
      e.remove();
      for (MapListener l : listeners) {
        l.onRemoveEntity(e);
      }
      return true;
    }
    return false;
  }
  
  public void renderBackground(Screen screen) {
    for (int x = 0; x < numCols; x++)
      for (int y = 0; y < numRows; y++) {
        Tile tile = map[x][y];
        tile.render(screen, this, x * Map.tileSize, y * Map.tileSize);
      }
  }
  
  public void renderSprites(Screen screen) {
    List<Entity> sprites = new ArrayList<Entity>();
    sprites.addAll(getEntities());
    Collections.sort(sprites, Map.spriteSorter);
    for (Entity e : sprites) {
      e.render(screen);
    }
  }
  
  public void setTile(int x, int y, Tile tile) {
    map[x][y] = tile;
    for (MapListener l : listeners) {
      l.onTileChange(tile);
    }
  }
  
  public void tick() {
    
    for (int y = 0; y < numRows; y++) {
      for (int x = 0; x < numCols; x++) {
        getTile(x, y).tick();
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
  
  public int tileSize() {
    return Map.tileSize;
  }
  
  @Override
  public String toString() {
    String s = "M " + id + " " + "W " + numCols + " " + "H " + numRows + " ";
    for (int y = 0; y < numRows; y++) {
      for (int x = 0; x < numCols; x++) {
        s += map[x][y];
      }
    }
    for (Entity e : getEntities()) {
      s += "\n" + e.toString();
    }
    return s;
  }
  
  public int width() {
    return numCols * Map.tileSize;
  }
  
}
