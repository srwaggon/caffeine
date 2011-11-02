package caffeine;

// Java Libraries
import java.util.ArrayList;
import java.util.TimerTask;
// Local Libraries
import caffeine.entity.*;
import caffeine.rule.*;
import caffeine.view.*;
import caffeine.world.*;


public class Game{
	InteractionHandler interactionHandler;
	World world;
	ArrayList<Rule> rules;
	Clock clock = Clock.getInstance();
	static Game game = new Game();
	public GUI gui;

	public static void main(String args[]){
		Game g = Game.instance();
		g.start();
		
		Player adam = new Player(new Location());
		
		// Adam
		g.add(adam);
		g.camera().focusOn(g.world.get(0).entities().get(0));
		
		//g.add(new Entity());
	}

	public void add(Entity e) {
		e.tile().add(e);
	}

	private Game(){
		interactionHandler = new InteractionHandler();

		// Let there be land
		world = new World();
		Map m = new Map("10 6 32\n" +
				"..##......\n"+
				".........~\n"+
				"...#.....~\n"+
				".........~\n"+
				"...#.....~\n"+
				"~~##.....~\n"
				);
		world.add(m);
		//world.add(m);

		// with Physics
		rules = new ArrayList<Rule>();
		//rules.add(new UnsafeTileRule());
				
		// Let there be light
		gui = new GUI(this);
	}
	
	public void start(){
		clock.scheduleAtFixedRate(new TimerTask(){
			public void run() {
				tick();
			}}, 0, 100);
	}

	public void tick() {
		world.tick();
		gui.tick();
	}
	
	public Camera camera(){
		return this.gui.getContentPane().getCamera();
	}
	
	public ArrayList<Entity> entities(int mapID){
		return world.get(mapID).entities();
	}
	
	public World world(){
		return world;
	}

	public static Game instance(){
		return game;
	}
	public InteractionHandler interactionHandler() {
		return interactionHandler;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}
}

