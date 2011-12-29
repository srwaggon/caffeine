package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.Attack;
import caffeine.action.Move;
import caffeine.util.Angle;
import caffeine.view.InteractionHandler;

public class PlayerBrain extends InteractiveBrain{

	public PlayerBrain(InteractionHandler i){
		super(i);
		actionMap.put(KeyEvent.VK_UP, new Move(new Angle(270)));
		actionMap.put(KeyEvent.VK_DOWN, new Move(new Angle(90)));
		actionMap.put(KeyEvent.VK_LEFT, new Move(new Angle(180)));
		actionMap.put(KeyEvent.VK_RIGHT, new Move(new Angle(0)));
		actionMap.put(KeyEvent.VK_SPACE, new Attack());
	}
}
