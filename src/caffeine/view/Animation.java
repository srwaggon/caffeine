package caffeine.view;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animation {
  List<Frame> frames = new ArrayList<Frame>();
  boolean isLooping;
  int frameDuration;
  int currentFrame = 0;
  int currentFrameTime = 0;

  public Animation(int[] sprites, int frameDuration, boolean isLooping) {
    for (int n : sprites) {
      frames.add(new Frame(new Sprite(n, 0, 0), 0, 0));
    }
    this.frameDuration = frameDuration / GUI.fps();
    this.isLooping = isLooping;
  }

  public Animation(Sprite[] sprites, int frameDuration, boolean isLooping) {
    for (Sprite s : sprites) {
      frames.add(new Frame(s, 0, 0));
    }
    this.frameDuration = frameDuration / GUI.fps();
    this.isLooping = isLooping;
  }

  public Animation(Frame[] frames, int frameDuration, boolean isLooping) {
    this.frames.addAll(Arrays.asList(frames));
    this.frameDuration = frameDuration / GUI.fps();
    this.isLooping = isLooping;
  }

  public void render(Graphics2D g2, int x, int y) {
    frames.get(currentFrame).render(g2, x, y);

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
