package caffeine.action;

import caffeine.entity.Actor;

/* Represents a general action to be performed by an Actor */
public interface Action {
  /* An example/basic action: Inaction! */
  public static Action Inaction = new Action() {
    public void performBy(Actor performer) {
      ;// Do nothing
    }
  };
  
  // Could require a 'target' in constructor
  
  /**
   * Takes an Actor and applies the result of this action on it.
   * 
   * @param performer
   */
  void performBy(Actor performer);
  
  String toString();
}
