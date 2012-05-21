package caffeine.action;

import caffeine.entity.Entity;

/* Represents a general action to be performed by an Actor */
public interface Action {
  /* An example/basic action: Inaction! */
  public static Action Inaction = new Action() {
    @Override
    public boolean performBy(Entity performer) {
      return true; // do nothing
    }
  };

  // Could require a 'target' in constructor

  /**
   * Takes an Actor and applies the result of this action on it.
   * 
   * @param performer
   * @return true if this action succeeds
   */
  boolean performBy(Entity performer);

  @Override
  String toString();
}
