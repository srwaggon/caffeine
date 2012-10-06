package caffeine.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.entity.ItemEntity;
import caffeine.gfx.Screen;
import caffeine.world.tile.Tile;

public class Map {
  protected List<Entity> entities = new ArrayList<Entity>();
  protected int backgroundSprite = 4;
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
        return  e1.getY() - e2.getY();
      }
    }

  };

  public static final String defaultMapData =
      "M 0 W 13 H 8 " +
          "DDDDDDDDDDDDD" +
          "D......m..mmD" +
          "D.mmm####mmmD" +
          "D.m~~~~##.m.D" +
          "D.m~~#.##mm.D" +
          "D~mmm###m.m.D" +
          "D~~..mm....mD" +
          "DDDDDDDDDDDDD";

  public Map(String data){
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

  public Map(int id, int w, int h, String data){
    this.id = id;
    numCols = w;
    numRows = h;

    map = new Tile[numCols][numRows];

    for (int i = 0; i < data.length(); i++){
      int x = i % numCols;
      int y = i / numCols;
      Tile t = Tile.read(x, y, data.charAt(i));
      map[x][y] = t;
    }
  }

  public List<Entity> entities() {
    return entities;
  }

  public List<Entity> getEntities(int x0, int y0, int x1, int y1){
    List<Entity> result = new ArrayList<Entity>();
    for (Entity e : entities()) {
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

  public Tile getTileSafe(int x, int y){
    if (x < 0) x = 0;
    if (y < 0) y = 0;
    if (x >= numCols) x = numCols - 1;
    if (y >= numRows) y = numRows - 1;
    return map[x][y];
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

  public boolean isValidLoc(int x, int y){
    return 0 <= x && x < numCols * Map.tileSize && 0 <= y
        && y < numRows * Map.tileSize;
  }

  public void tick() {

    for(int y = 0; y < numRows; y++){
      for(int x = 0; x < numCols; x++){
        //getTile(x,y).tick();
      }
    }
    for(int i = 0; i < entities.size(); i++){
      Entity e = entities.get(i);
      e.tick();
      if (e.isRemoved()) {
        entities.remove(i--);
      }

    }
  }

  @Override
  public String toString() {
    String s = "M " + id + " " +
        "W " + numCols + " " +
        "H " + numRows + " ";
    for (int y = 0; y < numRows; y++){
      for (int x = 0; x < numCols; x++){
        s += map[x][y];
      }
    }
    s += "\n";
    for(Entity e : entities()){
      s += e.toString() + "\n";
    }
    return s;
  }

  public void renderBackground(Screen screen) {
    for(int x = 0; x < numCols; x++)
      for(int y = 0; y < numRows; y++){
        Tile tile = map[x][y];
        tile.render(screen, this, x*tileSize, y* tileSize);
      }
  }

  public void renderSprites(Screen screen){
    List<Entity> sprites = new ArrayList<Entity>();
    sprites.addAll(entities());
    Collections.sort(sprites, spriteSorter);
    for (Entity e : sprites) {
      e.render(screen);
    }
  }

  public int width() {
    return numCols * Map.tileSize;
  }

  public void addEntity(Entity e){
    entities.add(e);
    e.setMap(this);
  }

  public boolean removeEntity(int id) {
    Entity e = getEntityByID(id);
    if(e != null){
      e.remove();
      return true;
    }
    return false;
  }

  public Entity getEntityByID(int id){
    for(int i = 0; i < entities.size(); i++){
      Entity e = entities.get(i);
      if (e.getID() == id){
        return e;
      }
    }
    return null;
  }


  public Entity getEntity(int id) {
    return entities.get(id);
  }

  public boolean isEmpty() {
    return entities.isEmpty();
  }

  public void setTile(int x, int y, Tile tile) {
    map[x][y] = tile;
  }

  public int getBackground() {
    return backgroundSprite;
  }

  public void read(String data){
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

}
