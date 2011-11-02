package caffeine.entity;

import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.rule.Rule;
import caffeine.util.Vector;
import caffeine.world.Location;

public class Actor extends Entity{
	protected Brain brain = new LeftBrain();
	protected double acceleration =  1;
	protected Vector velocity = new Vector();
	
	public Actor(){}
	public Actor(Location loc){super(loc);}
	
	public double acceleration(){return acceleration;}
	
	public ArrayList<Action> next(){return brain.next(this);}
	
	public void tick(){
		for(Rule r : Game.instance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
		}
		if(isAlive){
			for(Action a : next()){
				a.performOn(this);	
			}
			loc.add(velocity);
		}
	}
	
	public Vector velocity(){return velocity;}
}
