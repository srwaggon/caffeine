package caffeine.world;

import java.util.HashMap;

import caffeine.entity.Entity;

public class World {
	HashMap<Integer, Map> world;
	static int numMaps;
	
	public World(){
		world = new HashMap<Integer, Map>();
		numMaps = 0;
	}
	
	public int add(Map m) throws Exception{
		if(world.containsValue(m)){
			throw new Exception("Map already exists");
		}
		world.put(numMaps, m);
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

}
