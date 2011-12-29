package caffeine.action;

import caffeine.entity.Actor;
import caffeine.util.Vector;

public interface Motion {

	public void handleAcceleration(Accelerate accel, Actor target);

	public void handleMove(Move move, Actor target);

	public void tick();

	public boolean validMove(Move m, Actor a);

	public Vector velocity();
}
