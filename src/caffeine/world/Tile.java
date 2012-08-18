package caffeine.world;


public class Tile {

  protected boolean pass = true;
  protected int damage = 0;
  protected int baseSprite;
  protected int maskSprite;

  public Tile(int sprite) {
    baseSprite = sprite;
    if (sprite == 7) pass = false;
  }

  public boolean canPass() {
    return pass;
  }

  public void setPass(boolean b) {
    pass = b;
  }

  public boolean isSafe() {
    return damage <= 0;
  }

  public int getSprite() {
    return baseSprite;
  }

  public void setSprite(int id) {
    baseSprite = id;
  }

  @Override
  public String toString() {
    return ".";
  }

  public void tick() {
  }
}