package caffeine.net.accounts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import caffeine.entity.mob.PlayerEntity;

public class PlayerAccount implements Serializable {
  
  private static final long serialVersionUID = 3331067126219468538L;
  protected static final String FILE_EXT = ".caf";
  
  public final int ID;
  protected String username;
  protected String password;
  protected PlayerEntity entity;
  
  public PlayerAccount(String username, String password) {
    ID = username.hashCode();
    this.username = username;
    this.password = password;
    entity = new PlayerEntity(username);
  }
  
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("USAGE: <account> <password>");
      System.exit(0);
    }
    
    String user = args[0];
    String password = args[1];
    
    PlayerAccount account = new PlayerAccount(user, password);
    PlayerAccount.savePlayer(account);
    
    PlayerAccount j = PlayerAccount.loadPlayer(args[0]);
    
    if (j.authenticate(password)) {
      System.out.println(j.toString() + " created and logged in.");
    }
  }
  
  public boolean authenticate(String password) {
    return this.password.equals(password);
  }
  
  public static boolean savePlayer(PlayerAccount p) {
    try {
      FileOutputStream fileOut = new FileOutputStream(p.username
          + PlayerAccount.FILE_EXT);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(p);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
      return false;
    }
    return true;
  }
  
  public static PlayerAccount loadPlayer(String name) {
    PlayerAccount player = null;
    try {
      FileInputStream fileIn = new FileInputStream(name
          + PlayerAccount.FILE_EXT);
      
      ObjectInputStream in = new ObjectInputStream(fileIn);
      player = (PlayerAccount) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException c) {
      System.out.println("Player class not found");
      c.printStackTrace();
    }
    return player;
  }
  
  public PlayerEntity getEntity() {
    return entity;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getPassword() {
    
    return this.password;
  }
  
  @Override
  public String toString() {
    return username;
  }
  
}
