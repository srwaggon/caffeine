package caffeine.action;

import caffeine.entity.Entity;

public class Report implements Action {
  String msg;
  
  public Report(String message) {
    msg = message;
  }
  
  @Override
  public void performBy(Entity performer) {
    System.out.println(performer.toString() + ": \"" + msg + "\"");
  }
  
}