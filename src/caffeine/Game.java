package caffeine;

// Java Libraries
import java.util.ArrayList;
import java.util.TimerTask;

import caffeine.entity.Actor;
import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.rule.Rule;
import caffeine.rule.UnsafeTileRule;
import caffeine.view.Camera;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;


public class Game{
	InteractionHandler interactionHandler;
	World world = new World();
	ArrayList<Rule> rules = new ArrayList<Rule>();
	Clock clock = Clock.getInstance();
	static Game game = new Game();
	public GUI gui;

	public static void main(String args[]){
		Game g = Game.instance();
		Location l = new Location(0, 32, 32);
		Player adam = new Player(l);
		g.add(adam);
		g.add(new Actor(l));
		g.camera().focusOn(adam);
	}

	public void add(Entity e) {
		e.tile().add(e);
	}

	private Game(){
		interactionHandler = new InteractionHandler();

		// Let there be land
		world.add(new Map());

		// with Physics
		rules.add(new UnsafeTileRule());

		// Let there be light
		gui = new GUI(this);

		// Start time
		clock.add(new TimerTask(){public void run(){world.run();gui.repaint();}});
	}

	public Camera camera(){
		return gui.getContentPane().getCamera();
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

