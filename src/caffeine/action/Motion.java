package caffeine.action;

import caffeine.entity.Actor;

public interface Motion {

	public void handleAcceleration(Accelerate accel, Actor target);

	public void handleMove(Move move, Actor target);

	public void tick();
}
