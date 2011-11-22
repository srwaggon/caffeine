package caffeine.rule;

import caffeine.action.Die;
import caffeine.entity.Actor;

public class UnsafeTileRule implements Rule{
	public boolean appliesTo(Object o){
		if(o instanceof Actor){
			Actor e = (Actor) o;
			if(e.alive() && !e.tile().getType().isSafe()){
				return true;
			}
		}
		return false;
	}
	
	public void applyOn(Object o){
		if(o instanceof Actor){
			Actor a = (Actor) o;
			new Die(a.loc().tile().toString()).performOn(a);
		}
	}
}
