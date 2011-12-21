package caffeine.entity.brain;

import java.awt.event.KeyEvent;

import caffeine.action.Accelerate;
import caffeine.util.Angle;
import caffeine.view.InteractionHandler;

public class AccelerateBrain extends InteractiveBrain{

	public AccelerateBrain(InteractionHandler i){
		super(i);
		actionMap.put(KeyEvent.VK_UP, new Accelerate(new Angle(270)));
		actionMap.put(KeyEvent.VK_DOWN, new Accelerate(new Angle(90)));
		actionMap.put(KeyEvent.VK_LEFT, new Accelerate(new Angle(180)));
		actionMap.put(KeyEvent.VK_RIGHT, new Accelerate(new Angle(0)));
	}
}
