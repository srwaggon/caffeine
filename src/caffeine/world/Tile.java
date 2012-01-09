package caffeine.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import caffeine.entity.Entity;
import caffeine.view.GFX;

public class Tile{
  public static Tile block = new Tile(false, 0, 1, '#');
  public static Tile safe = new Tile(true, 0, 0, '.');

  protected ArrayList<Entity> occupants = new ArrayList<Entity>();
  protected boolean pass = true;
  protected int damage = 0;
  protected Image sprite = GFX.getSprite(0);
  protected char symbol;

  public Tile(){}

  public Tile(char c){
    symbol = c;
    if(c == '#'){
      pass = false;
      sprite = GFX.getSprite(1);
    }
  }

  private Tile(boolean pass, int damage, int spriteID, char symbol){
    this.pass = pass;
    this.damage = damage;
    sprite = GFX.getSprite(spriteID);
    this.symbol = symbol;
  }

  public void add(Entity e){
    occupants.add(e);
  }

  public ArrayList<Entity> occupants(){
    return occupants;
  }

  public boolean isEmpty(){
    return occupants.isEmpty();
  }

  public boolean pass(){
    return pass;
  }

  public void paint(Graphics2D g2, Rectangle box){
    AffineTransform xform = new AffineTransform(box.width / sprite.getWidth(null), 0, 0, box.height / sprite.getHeight(null), box.x, box.y);
    g2.drawImage(sprite, box.x, box.y, null);
    //g2.drawImage(sprite, xform, null);
  }

  public void remove(Entity e){
    occupants.remove(e);
  }

  public boolean safe(){
    return damage <= 0;
  }

  public String toString(){
    return ""+symbol;
  }

  public Image sprite() {
    return sprite;
  }
}