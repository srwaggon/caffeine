package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.StaticMove;
import caffeine.util.Angle;
import caffeine.view.InteractionHandler;

public class MoveBrain extends InteractiveBrain{

	public MoveBrain(InteractionHandler i){
		super(i);
		actionMap.put(KeyEvent.VK_UP, new StaticMove(new Angle(270)));
		actionMap.put(KeyEvent.VK_DOWN, new StaticMove(new Angle(90)));
		actionMap.put(KeyEvent.VK_LEFT, new StaticMove(new Angle(180)));
		actionMap.put(KeyEvent.VK_RIGHT, new StaticMove(new Angle(0)));
	}
}
