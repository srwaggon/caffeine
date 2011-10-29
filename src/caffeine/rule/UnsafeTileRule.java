package caffeine.rule;

import caffeine.entity.Entity;

public class UnsafeTileRule implements Rule{
	public boolean appliesTo(Object o){
		if(o instanceof Entity){
			Entity e = (Entity) o;
			if(!e.tile().getType().isSafe()){
				return true;
			}
		}
		return false;
	}
	
	public void applyOn(Object o){
		if(o instanceof Entity){
			((Entity) o).die();
		}
	}
}
