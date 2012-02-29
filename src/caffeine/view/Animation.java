package caffeine.view;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import caffeine.world.Location;

public class Animation {
  List<Sprite> frames = new ArrayList<Sprite>();
  boolean isLooping;
  int frameDuration;
  int currentFrame = 0;
  int currentFrameTime = 0;
  
  public Animation(int[] sprites, int frameDuration, boolean isLooping) {
    for (int n : sprites) {
      frames.add(new Sprite(n));
    }
    this.frameDuration = frameDuration / 30;
    this.isLooping = isLooping;
  }
  
  public void render(Graphics2D g2, Location loc) {
    frames.get(currentFrame).render(g2, loc);
    
    currentFrameTime++;
    if (currentFrameTime < frameDuration) {
      return;
    }
    currentFrameTime = 0;
    currentFrame++;
    
    // handle looping
    if (currentFrame >= frames.size()) {
      if (isLooping) {
        currentFrame = 0;
      } else {
        currentFrame--;
      }
    }
  }
}
