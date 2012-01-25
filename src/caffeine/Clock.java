package caffeine;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
  private Timer timer = new Timer();
  private boolean running = false;
  List<TimerTask> events = new ArrayList<TimerTask>();

  public Clock(){
    timer.scheduleAtFixedRate(new TimerTask(){
      public void run(){
        tick();
      }
    }, 0, 1000/30);
  }

  public void add(TimerTask t){
    events.add(t);
  }

  public void clear(){
    events = new ArrayList<TimerTask>();
  }

  public void start(){
    running = true;
  }

  public void stop(){
    running = false;
  }

  private void tick(){
    if(running){
      try{
        for(TimerTask t : events){
          t.run();
        }
      }
      catch(Throwable ex){
        System.err.println("Exception caught: " + ex.toString());
        ex.printStackTrace();
      }
    }
  }
}

