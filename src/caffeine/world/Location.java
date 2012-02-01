package caffeine.world;

import caffeine.Server;
import caffeine.util.Util;

public class Location {
  protected int mapID;
  protected int x;
  protected int y;

  public Location(){
    mapID = 0;
    x = 0;
    y = 0;
  }

  public Location(int mapID, int x, int y){
    this.mapID = mapID;
    this.x = x;
    this.y = y;
  }

  public Map map(){
    return Server.instance().world().get(mapID);
  }

  public Tile tile(){
    return map().getTileAt(x, y);
  }

  public Location project(int xdist, int ydist){
    return new Location(mapID, x+xdist, y+ydist);
  }

  public String toString(){
    return "[" + mapID + "(" + x + "," + y + ")]";
  }


  /**
   * Returns a boolean representing whether or not this location exists within the width and height of the board.
   * @return boolean representing whether or not this location exists within the width and height of the board.
   */
  public boolean validLocation() {
    return map().onMap(x,  y);
  }

  /**
   * Returns a reference to a new location determined by projecting this location in the given direction by the given distance.
   * @param direction of projection
   * @param distance being projected
   * @return Projected Location
   */
  public Location project(Direction d, int dist){
    int newX = x;
    int newY = y;

    switch (d) {
    case  W: newX -= dist; break;
    case  N: newY -= dist; break;
    case  E: newX += dist; break;
    case  S: newY += dist; break;
    default: break;
    }
    return new Location(mapID, newX, newY);

  }

  /**
   * Returns a reference to a new location with the same mapID, x-coordinate and y-coordinate.
   * @return A new Location with the same mapID, x-coordinate and y-coordinate.
   */
  public Location copy() {
    return new Location(mapID, x, y);
  }

  /**
   * Changes the state of this location to appear similar to the given location.
   * <br />Useful to take advantage of shared references between other objects to this location object.
   * @param location to mimic.
   */
  public void set(Location l){
    mapID = l.mapID;
    x = l.x;
    y = l.y;
  }

  /**
   * Calculates and returns the distance between this location and the given location.
   * @param loc
   * @return the distance between this location and the given location.
   */
  public double distanceTo(Location loc) {
    return Util.pythagoras(loc.x-x, loc.y-y);
  }

  /**
   * Returns the mapID of this location.
   * @return the mapID of this location.
   */
  public int mapID() {
    return mapID;
  }

  /**
   * Returns the x-coordinate of this location;
   * @return the x-coordinate of this location;
   */
  public int x(){
    return x;
  }

  /**
   * Returns the y-coordinate of this location;
   * @return the y-coordinate of this location;
   */
  public int y(){
    return y;
  }
}
