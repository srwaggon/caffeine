package caffeine;

// Java Libraries
import java.util.ArrayList;
// Local Libraries
import caffeine.entity.*;
import caffeine.rule.*;
import caffeine.view.*;
import caffeine.world.*;


public class Game{
	InteractionHandler interactionHandler;
	World world;
	ArrayList<Rule> rules;
	Integer currentMapID;
	Clock gameClock = Clock.getInstance();
	static Game game = new Game();
	public GUI gui;

	public static void main(String args[]){
		Game g = Game.getInstance();
		Player adam = new Player(new Location());
		// Adam
		g.world.get(0).add(adam);
		g.getCamera().focusOn(g.world.get(0).getEntities().get(0));
		
		// Animal Kingdom
		g.world.spawnDudes(5, 0);
	}

	private Game(){
		interactionHandler = new InteractionHandler();

		// Let there be land
		world = new World();
		try {
			Map m = Map.read("20 14 48 " +
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
					);
			currentMapID = world.add(m);
			//world.add(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		world.get(currentMapID).getTile(9, 10).setWarp(new Warp(currentMapID,60,60));

		// with Physics
		rules = new ArrayList<Rule>();
		//rules.add(new UnsafeTileRule());
		/*
		rules.add(new Rule(){
			public boolean appliesTo(Object o){
				return true;
			}
			
			public void applyOn(Object o){
				System.out.println(o.toString());
			}
		});
		*/
				
		// Let there be light
		gui = new GUI(this);
	}

	protected void tick() {
		world.get(currentMapID).tick();
		gui.tick();
	}
	
	public Camera getCamera(){
		return this.gui.getContentPane().getCamera();
	}
	
	public ArrayList<Entity> getEntities(int mapID){
		return world.get(mapID).getEntities();
	}
	
	public World getWorld(){
		return world;
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

