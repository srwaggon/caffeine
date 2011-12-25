package caffeine;

import java.util.ArrayList;
import java.util.TimerTask;

import caffeine.entity.Entity;
import caffeine.entity.Player;
import caffeine.view.Camera;
import caffeine.view.GUI;
import caffeine.view.InteractionHandler;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.World;


public class Game{
	static InteractionHandler interactionHandler= new InteractionHandler();
	static Clock clock = Clock.getInstance();
	public static Game game = new Game();
	public static GUI gui = new GUI();
	World world = new World(new Map());

	public static void main(String args[]){
		Game g = Game.game;
		Location l = new Location(0, 32, 32);
		Player adam = new Player(l);
		g.add(adam);
		//g.add(new Actor(l));
		camera().focusOn(adam);
	}

	public void add(Entity e) {
		e.tile().add(e);
	}

	private Game(){
		clock.add(new TimerTask(){
			public void run(){
				world.run();
				gui.repaint();
			}
		});
	}

	public static Camera camera(){
		return gui.getContentPane().getCamera();
	}

	public ArrayList<Entity> entities(int mapID){
		return world.get(mapID).entities();
	}

	public World world(){
		return world;
	}

	public static InteractionHandler interactionHandler() {
		return interactionHandler;
	}
}

