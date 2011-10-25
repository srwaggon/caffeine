package caffeine;


import java.util.ArrayList;
import java.util.HashMap;

import caffeine.entity.Entity;
import caffeine.entity.PlayerBrain;
import caffeine.rule.Rule;
import caffeine.rule.UnsafeTileRule;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Map;
import caffeine.world.Point;
import caffeine.world.Warp;

public class Game{
	InteractionHandler interactionHandler = new InteractionHandler();
	HashMap<Integer, Map> world = new HashMap<Integer, Map>();
	ArrayList<Rule> rules = new ArrayList<Rule>();
	Integer currentMapID = 0;
	Clock gameClock = Clock.getInstance();
	static Game game = new Game();
	public GUI gui;

	public static void main(String args[]){
		Game.getInstance();
	}

	private Game(){
		// Let there be land
		world.put(currentMapID, Map.read("20 14 48 " +
				"...###.......##...##" +
				".............##...~#" + 
				"....#######..##...~#" +
				"#...#~~~.....##....#" +
				"#...#.....#........#" +
				"....#..####..##....#" +
				".............##....#" +
				"#..##...###..##....#" +
				"~..##..............." +
				"#.....#.....#..#####" +
				"..###.#..~..#......." +
				"......#.....#...#..." +
				"#~.................#" +
				"##......###.......##"
				));
		world.get(currentMapID).getTile(9, 10).setWarp(new Warp(currentMapID,60,60));
		// Let there be light
		gui = new GUI(this);
		// Let there be time
		gameClock.scheduleAtFixedRate(new GameTick(), 0, 100);
		world.get(currentMapID).add(new Entity(
				new PlayerBrain(interactionHandler),
				new Point()));
		gui.getContentPane().getCamera().focusOn(getCurrentMap().getEntities().get(0));
		for(int i = 0; i < 10; i++){
			world.get(currentMapID).add(new Entity());
		}
		rules.add(new UnsafeTileRule());
	}

	public Map getCurrentMap(){
		return world.get(currentMapID);
	}
	public ArrayList<Entity> getEntities(){
		return world.get(currentMapID).getEntities();
	}
	
	public HashMap<Integer, Map> getWorld(){
		return world;
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

	public ArrayList<Rule> getRules() {
		return rules;
	}
}

