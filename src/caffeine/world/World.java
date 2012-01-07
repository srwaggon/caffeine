package caffeine.world;

import java.awt.Graphics2D;
import java.util.HashMap;

public class World{
  public HashMap<Integer, Map> world = new HashMap<Integer, Map>();
  static int numMaps = 0;

  public World(){world.put(numMaps, new Map());}

  public World(Map m){world.put(numMaps, m);}

  public int size(){return world.size();}

  public int add(Map m){
    world.put(numMaps, m);
    return numMaps++;
  }

  public Map get(int mapID){
    return world.get(mapID);
  }

  public void tick(){
    for(Map m : world.values()){
      m.tick();
    }
  }

  public String toString(){
    String s = "";
    for(Map m : world.values()){
      s += m;
    }
    return s;
  }

  public void paint(Graphics2D g2) {
    for (Map m : world.values()){
      m.paint(g2);
    }
  }

}
