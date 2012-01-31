package caffeine.world;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import caffeine.entity.Entity;

public class Map implements Iterable<Tile>{
  protected int numRows, numCols;
  protected Tile[][] map;
  protected int tileSize = 32;

  private static String defaultMapString = "40 20 32 "+
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
      "########################################";

  public Map(){
    this(defaultMapString);
  }

  public Map(int cols, int rows, int tileSize){
    numRows = rows;
    numCols = cols;
    this.tileSize = tileSize;
    map = new Tile[cols][rows];

    for(int y = 0; y < numCols; y++){
      for(int x = 0; x < numRows; x++){
        map[x][y] = new Tile();
      }
    }
  }

  public Map(String s){
    Scanner scans = new Scanner(s);
    numCols = Integer.parseInt(scans.next());
    numRows = Integer.parseInt(scans.next());
    tileSize = Integer.parseInt(scans.next());

    map = new Tile[numCols][numRows];
    String line = scans.next();
    for(int i = 0; i < numRows*numCols; i++){
      char c = line.charAt(i);
      map[i%numCols][i/numCols] = new Tile(c);
    }
  }

  public Tile getTile(int x, int y){
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

  public Tile getTileAt(int x, int y){
    return getTile(x/tileSize, y/tileSize);
  }

  public int numCols(){return numCols;}
  public int numRows(){return numRows;}
  public int height(){return numRows*tileSize;}
  public int width(){return numCols*tileSize;}
  public int tileSize(){return tileSize;}

  public boolean onMap(int x, int y){
    return 0 <= x && x < numCols*tileSize
        && 0 <= y && y < numRows*tileSize;
  }

  public boolean inRange(int x, int y){
    return x >= 0 && x < numCols
        && y >= 0 && y < numRows;
  }

  public void tick(){
    Collection<Entity> entities = new LinkedList<Entity>();
    Iterator<Tile> tileIterator = iterator();
    while(tileIterator.hasNext()){
      Tile t = tileIterator.next();
      entities.addAll(t.occupants());
      t.tick();
    }
    for(Entity e : entities){
      e.tick();
    }
  }

  public String toString(){
    String s = "" + numCols + " " + numRows + " " + tileSize + " ";
    for(int y = 0; y < numRows; y++){
      for(int x = 0; x < numCols; x++){
        s += map[x][y];
      }
    }
    return s;
  }


  public Iterator<Tile> iterator() {
    return new Iterator<Tile>(){
      int x = 0;
      int y = 0;

      public boolean hasNext() {
        return inRange(x, y);
      }

      public Tile next() {
        Tile t = getTile(x, y);
        if(++x == numCols){
          x = 0;
          y++;
        }
        return t;
      }

      public void remove() {
        // :V  Yeah, no.
      }
    };
  }

  public List<Entity> entities() {
    List<Entity> entities = new LinkedList<Entity>();
    Iterator<Tile> tileIt = iterator();
    while(tileIt.hasNext()) {
      Tile t = tileIt.next();
      entities.addAll(t.occupants());
    }
    return entities;
  }

}