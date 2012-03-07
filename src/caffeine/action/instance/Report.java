package caffeine.action.instance;

import caffeine.action.Action;
import caffeine.entity.Actor;

public class Report implements Action {
  String msg;
  public Report(String message){
    msg = message;
  }
  @Override
  public void perform(Actor performer) {
    System.out.println(performer.toString() + ": \"" + msg + "\"");
  }

}
