package caffeine.entity;

import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.action.Motion;
import caffeine.action.StaticMotion;
import caffeine.entity.brain.Brain;
import caffeine.entity.brain.LeftBrain;
import caffeine.util.Vector;
import caffeine.world.Location;

public class Actor extends Entity{
	protected boolean isAlive = true;
	protected Brain brain = new LeftBrain();
	protected int speed = 4; // used for static moves
	protected double accelRate = 1;
	protected Vector velocity = new Vector(); // used for kinetic moves
	protected Motion motion;

	public Actor(Location loc){
		super(loc);
		motion = new StaticMotion(this.loc);
	}

	public Motion motion(){return motion;}

	public boolean alive(){return isAlive;}

	public void alive(Boolean b){isAlive = b;}

	public ArrayList<Action> next(){return brain.next(this);}

	public void tick(){
		if(isAlive){
			for(Action a : next()){
				a.perform(this);
			}
			motion.tick();
		}
	}
}
