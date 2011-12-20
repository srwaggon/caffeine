package caffeine.entity;

import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.action.Move;
import caffeine.rule.Rule;
import caffeine.util.Vector;
import caffeine.world.Location;

public class Actor extends Entity{
	protected boolean isAlive = true;
	protected Brain brain = new LeftBrain();
	protected double accelRate =  1;
	protected Vector accelVec = new Vector();
	protected double drag = .1;
	protected Vector velocity = new Vector();
	
	public Actor(){}
	
	public Actor(Location loc){super(loc);}
	
	public double accelRate(){return accelRate;}
	
	public Vector accelVec(){return accelVec;}
	
	public boolean alive(){return isAlive;}
	
	public void alive(Boolean b){isAlive = b;}
	
	public double drag(){return drag;}
	
	public ArrayList<Action> next(){return brain.next(this);}
	
	public void tick(){
		// apply valid rules, though this is inefficient
		// TODO think of better rule system
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
		}
		
		if(isAlive){
			for(Action a : next()){
				a.performOn(this);	
			}
			new Move(velocity).performOn(this);
		}
	}
	
	public Vector velocity(){return velocity;}
}
