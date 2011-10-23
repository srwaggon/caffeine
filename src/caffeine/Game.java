package caffeine;


import java.util.ArrayList;
import java.util.HashMap;

import caffeine.entity.Entity;
import caffeine.entity.PlayerBrain;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Map;
import caffeine.world.Point;

public class Game{
	InteractionHandler interactionHandler = new InteractionHandler();
	HashMap<Integer, Map> world = new HashMap<Integer, Map>();
	Integer currentMapID = 0;
	Clock gameClock = Clock.getInstance();
	static Game game = new Game();
	GUI gui;
	

	public static void main(String args[]){
		Game.getInstance();
	}

	private Game(){
		world.put(currentMapID, Map.read("10 8 64 " +
				"....##...." +
				".........." + 
				".........." +
				"#...~~...#" +
				"#...~~...#" +
				".........." +
				".........." +
				"....##...."));
		/*
		world.add(Map.read("10 8 64 " +
				"....##...." +
				".........." + 
				".........." +
				"#...~~...#" +
				"#...~~...#" +
				".........." +
				".........." +
				"....##...."));
		 */
		System.out.println(world.get(currentMapID));
		world.get(currentMapID).add(new Entity(new PlayerBrain(interactionHandler), new Point(45, 45)));
		gui = new GUI(this);
		gameClock.scheduleAtFixedRate(new GameTick(), 0, 100);
	}

	public Map getCurrentMap(){
		return world.get(currentMapID);
	}
	public ArrayList<Entity> getEntities(){
		return world.get(currentMapID).getEntities();
	}

	public GUI getGUI(){
		return gui;
	}
	public static Game getInstance(){
		return game;
	}
	public InteractionHandler getInteractionHandler() {
		return interactionHandler;
	}
}

