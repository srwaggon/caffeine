package caffeine.world;

import java.util.HashMap;

import caffeine.entity.Entity;

public class World {
	public HashMap<Integer, Map> world;
	static int numMaps;
	
	public World(){
		world = new HashMap<Integer, Map>();
		numMaps = 0;
	}
	public int size(){
		return world.size();
	}
	
	public int add(Map m){
		world.put(numMaps, m);
		System.out.println(numMaps);
		// return its map ID, and increment the number of maps.
		return numMaps++;
	}
	
	public Map get(int mapID){
		return world.get(mapID);
	}
	
	public void spawnDudes(int n, int mapID){
		for(int i = 0; i < n; i++){
			world.get(mapID).add(new Entity());
		}
	}
	
	public void tick(){
		for(Map m : world.values()){
			m.tick();
		}
	}
	
	public String toString(){
		String s = "";
		for(Map m : world.values()){
			s += m.display();
		}
		return s;
	}

}
