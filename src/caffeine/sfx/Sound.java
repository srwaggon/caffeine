package caffeine.sfx;

import java.applet.Applet;
import java.applet.AudioClip;


public class Sound {
  public static final Sound COIN = new Sound("coin.wav");
  public static final Sound ENEMY_DIE = new Sound("La_Enemy_Die.wav");
  public static final Sound HURT = new Sound("LA_Enemy_Hit.wav");
  public static final Sound ITEM = new Sound("item.wav");
  public static final Sound JUMP = new Sound("jump.wav");
  public static final Sound SWORD1 = new Sound("LA_Sword_Slash1.wav");
  public static final Sound SWORD2 = new Sound("LA_Sword_Slash2.wav");
  public static final Sound SWORD3 = new Sound("LA_Sword_Slash3.wav");
  public static final Sound SWORD4 = new Sound("LA_Sword_Slash4.wav");
  public static final Sound POWERUP = new Sound("powerup.wav");
  public static final Sound SELECT1 = new Sound("select.wav");
  public static final Sound SELECT2 = new Sound("select2.wav");
  public static final Sound SELECT3 = new Sound("select3.wav");

  private AudioClip clip;

  private Sound(String name) {
    try {
      clip = Applet.newAudioClip(Sound.class.getResource(name));
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public void play() {
    try {
      new Thread() {
        public void run() {
          clip.play();
        }
      }.start();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}