package caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
  private final Timer timer = new Timer();
  private List<TimerTask> events = new ArrayList<TimerTask>();
  private boolean running = true;
  private double ticksPerSecond = 1000 / 30;

  public Clock() {
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        if (running) {
          tick();
        }
      }
    }, 0, (long) ticksPerSecond);
  }

  public void add(TimerTask t) {
    events.add(t);
  }

  public void clear() {
    events = new ArrayList<TimerTask>();
  }

  public void start() {
    running = true;
  }

  public void stop() {
    running = false;
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
