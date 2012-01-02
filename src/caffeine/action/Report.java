package caffeine.action;

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
