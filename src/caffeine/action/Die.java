package caffeine.action;

import caffeine.entity.Actor;

public class Die implements Action {
	String cause;
	public Die(String cause){
		this.cause = cause;
	}
	
	@Override
	public void performOn(Actor a) {
		a.tile().remove(a);
		a.alive(false);
		System.err.println(a + " has died from " + cause);
	}

}
