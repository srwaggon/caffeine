package caffeine.action;

import caffeine.entity.Actor;
import caffeine.entity.Entity;

public class Report implements Action {
	String msg;
	public Report(String message){
		msg = message;
	}
	@Override
	public void perform(Actor performer, Entity target) {
		System.out.println(performer.toString() + ": \"" + msg + "\"");
	}

}
