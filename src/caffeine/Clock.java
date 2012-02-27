package caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
  private final Timer timer  = new Timer();
  List<TimerTask>     events = new ArrayList<TimerTask>();
  
  public Clock() {
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        tick();
      }
    }, 0, 1000 / 30);
  }
  
  public void add(TimerTask t) {
    events.add(t);
  }
  
  public void clear() {
    events = new ArrayList<TimerTask>();
  }
  
  private void tick() {
    try {
      for (TimerTask task : events) {
        task.run();
      }
    } catch (Throwable ex) {
      System.err.println("Exception caught: " + ex.toString());
      ex.printStackTrace();
    }
  }
}
