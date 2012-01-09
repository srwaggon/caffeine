package caffeine.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import caffeine.entity.Entity;

public class Map{
  protected int height, width;
  protected Tile[][] map;
  protected int tileSize = 32;

  public Map(){
    this("40 20 32 "+
        "########################################"+
        "#...#...#...#..........................."+
        "#......................................."+
        "#.....#................................."+
        "##..#..................................."+
        "#.........#............................."+
        "#..#....#....#.........................."+
        "#......#.#.............................."+
        "##....#...#............................."+
        "#......#.#.............................."+
        "#....#..#..............................."+
        "#...........#..........................."+
        "##.........#............................"+
        "#.....#................................."+
        "#......................................."+
        "#......................................."+
        "#......................................."+
        "#......................................."+
        "#......................................."+
        "########################################");
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

  public Tile getTile(int x, int y){
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
    return getTile(x/tileSize, y/tileSize);
  }

  public int height(){return height*tileSize;}

  public int width(){return width*tileSize;}

  public List<Entity> entities(){
    List<Entity> entities = new LinkedList<Entity>();
    for (Tile t : tiles()){
      entities.addAll(t.occupants());
    }
    return entities;
  }

  public boolean withinBounds(int x, int y){
    return  0 <= x && x < width*tileSize &&
        0 <= y && y < height*tileSize;
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

  public void paint(Graphics2D g2) {
    /* A place to store entity sprites, we'll draw these after we've drawn the world */
    List<Entity> entities = new LinkedList<Entity>();

    /* Draw the world, tile by tile */
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        Tile t = map[x][y];
        entities.addAll(t.occupants());

        Rectangle r = new Rectangle(x*tileSize, y*tileSize, tileSize, tileSize);
        t.paint(g2, r);
      }
    }

    /* Now draw the entities on the world */
    for(Entity e : entities) {
      e.paint(g2);
    }
  }
}