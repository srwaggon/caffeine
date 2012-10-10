package caffeine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import caffeine.entity.PlayerEntity;
import caffeine.entity.brain.Brain;

public class Player implements Serializable {

  private static final long serialVersionUID = 3331067126219468538L;
  public final int ID;
  protected static final String FILE_EXT = ".caf";
  protected String name;
  protected PlayerEntity entity;

  public Player(String name, PlayerEntity pe) {
    ID = pe.ID;
    this.name = name;
    entity = pe;
  }

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("USAGE: <account>");
      System.exit(0);
    }

    PlayerEntity e = new PlayerEntity(((int) Math.random()*99) + 1);
    Player p = new Player(args[0], e);
    Player.savePlayer(p);

    Player j = Player.loadPlayer(args[0]);
    System.out.println( j.toString() + " created");
  }

  public static boolean savePlayer(Player p) {
    try {
      FileOutputStream fileOut =
          new FileOutputStream(p.name + Player.FILE_EXT);
      ObjectOutputStream out =
          new ObjectOutputStream(fileOut);
      out.writeObject(p);
      out.close();
      fileOut.close();
    } catch(IOException i) {
      i.printStackTrace();
      return false;
    }
    return true;
  }

  public static Player loadPlayer(String name) {
    Player player = null;
    try {
      FileInputStream fileIn =
          new FileInputStream(name + Player.FILE_EXT);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      player = (Player) in.readObject();

      player.entity.setBrain(new Brain(player.entity));

      in.close();
      fileIn.close();
    } catch(IOException i) {
      i.printStackTrace();
    } catch(ClassNotFoundException c) {
      System.out.println("Player class not found");
      c.printStackTrace();
    }
    return player;
  }

  public PlayerEntity getEntity(){
    return entity;
  }

  public String toString() {
    return name;
  }

}

