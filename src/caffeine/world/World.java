package caffeine.world;

import java.util.HashMap;

public class World extends Thread{
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
		return numMaps++;
	}

	public Map get(int mapID){
		return world.get(mapID);
	}

	public void run(){
		for(Map m : world.values()){
			m.run();
		}
	}

	public String toString(){
		String s = "";
		for(Map m : world.values()){
			s += m;
		}
		return s;
	}

}
