package caffeine.world;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.Sprite;
import caffeine.world.tile.Tile;

public class Map{
  protected int height, width;
  protected Tile[][] map;
  protected int tileSize = 32;
  protected List<Entity> entities = new ArrayList<Entity>();

  public Map(){
    this("20 20 32 "+
        "####################"+
        "#...#...#...#......#"+
        "#..................#"+
        "#.....#............#"+
        "##..#..............#"+
        "#.........#........#"+
        "#..#....#....#.....#"+
        "#......#.#.........#"+
        "##....#...#........#"+
        "#......#.#.........#"+
        "#....#..#..........#"+
        "#...........#......#"+
        "##.........#.......#"+
        "#.....#............#"+
        "#..................#"+
        "#..................#"+
        "#..................#"+
        "#..................#"+
        "#..................#"+
        "####################");
  }

  public Map(int cols, int rows, int tileSize){
    height = rows;
    width = cols;
    this.tileSize = tileSize;
    map = new Tile[cols][rows];

    for(int y = 0; y < width; y++){
      for(int x = 0; x < height; x++){
        map[x][y] = new Tile();
      }
    }
  }
  public Map(String s){
    Scanner scans = new Scanner(s);
    width = Integer.parseInt(scans.next());
    height = Integer.parseInt(scans.next());
    tileSize = Integer.parseInt(scans.next());

    map = new Tile[width][height];
    String line = scans.next();
    for(int i = 0; i < height*width; i++){
      char c = line.charAt(i);
      System.err.print(c);
      map[i%width][i/width] = new Tile(c);
    }
    System.err.println();
  }

  public Tile get(int x, int y){
    if (x < 0) {
      x = 0;
    }
    if (y < 0) {
      y = 0;
    }
    if (x >= width) {
      x = width - 1;
    }
    if (y >= height) {
      y = height -1;
    }
    return map[x][y];
  }

  public Tile getTileAt(int x, int y){
    return get(x/tileSize, y/tileSize);
  }

  public int height(){return height;}

  public int width(){return width;}

  public List<Entity> entities(){
    return entities;
  }

  public boolean withinBounds(int x, int y){
    return  0 <= x && x < width*tileSize &&
        0 <= y && y < height*tileSize;
  }

  public List<Sprite> tileSprites(){
    entities = new ArrayList<Entity>();
    List<Sprite> sprites = new ArrayList<Sprite>();

    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        Tile t = map[x][y];
        // Because the map ID isn't important for placing the sprites.
        Location l = new Location(-1, x*tileSize, y*tileSize);
        Sprite s = new Sprite(t.getType().color(), l, new Rectangle(x*tileSize, y*tileSize, tileSize, tileSize));
        sprites.add(s);
        // Side effect.. Necessary to update entities.
        entities.addAll(t.entities());
      }
    }
    return sprites;
  }

  public void tick(){
    for(Entity e : entities()){
      e.tick();
    }
  }

  public List<Tile> tiles() {
    List<Tile> tiles = new LinkedList<Tile>();
    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        tiles.add(map[x][y]);
      }
    }
    return tiles;
  }

  public String toString(){
    String s = "";
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        s += map[x][y];
      }
      s += "\n";
    }
    return s;
  }
}