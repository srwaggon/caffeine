package caffeine.net.packet;

import java.io.Serializable;


public abstract class Event implements Serializable {

  private static final long serialVersionUID = 596928442397571347L;

  public abstract void apply();

}
