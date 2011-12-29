package caffeine.action;

import caffeine.entity.Actor;

public class Hurt implements Action{
	int amt;
	Actor sender = null;


	public Hurt(int amount){
		amt = amount;
	}

	public Hurt(int amount, Actor sender){
		amt = amount;
		this.sender = sender;
	}

	public void perform(Actor performer) {
		performer.health -= amt;
		System.out.println(performer + " hurt by " + sender);

		if(performer.health <= 0){
			new Die(sender.toString()).perform(performer);
		}
	}

}
