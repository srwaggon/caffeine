package caffeine.entity;

import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.KineticMove;
import caffeine.entity.brain.Brain;
import caffeine.entity.brain.LeftBrain;
import caffeine.rule.Rule;
import caffeine.util.Vector;
import caffeine.world.Location;

public class Actor extends Entity{
	protected boolean isAlive = true;
	protected Brain brain = new LeftBrain();
	protected int speed = 4; // used for static moves
	protected double accelRate = 1;
	protected Vector velocity = new Vector(); // used for kinetic moves

	public Actor(){}

	public Actor(Location loc){super(loc);}

	public double accelRate(){return accelRate;}

	public boolean alive(){return isAlive;}

	public void alive(Boolean b){isAlive = b;}

	public ArrayList<Action> next(){return brain.next(this);}
	
	public int speed(){return speed;}

	public void tick(){
		// apply valid rules, though this is inefficient
		// TODO think of better rule system
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this)) {
				r.applyOn(this);
			}
		}

		if(isAlive){
			for(Action a : next()){
				a.perform(this, this);
			}
			// used for acceleration.  if no acceleration, applies a (0,0) vector
			new KineticMove(velocity).perform(this);
		}
	}

	public Vector velocity(){return velocity;}
}
