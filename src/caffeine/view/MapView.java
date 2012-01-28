package caffeine.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.LinkedList;

import caffeine.entity.Entity;
import caffeine.world.Location;
import caffeine.world.Map;
import caffeine.world.Tile;


public class MapView{
  BufferedImage view;
  protected Map map;


  public MapView(Map m) {
    map = m;
    view = new BufferedImage(map.width(), map.height(), BufferedImage.TYPE_INT_ARGB);
    paintComponent(view.createGraphics());
  }

  public int getHeight(){
    return view.getHeight();
  }

  public Image getImage(){
    return view;
  }

  public Image getSubimage(int x, int y, int w, int h){
    return view.getSubimage(x, y, w, h);
  }

  public int getWidth(){
    return view.getWidth();
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Collection<Entity> entities = new LinkedList<Entity>();

    int numCols = map.numCols();
    int numRows = map.numRows();
    int tileSize = map.tileSize();

    /* Draw the world, tile by tile */
    for(int y = 0; y < numRows; y++){
      for(int x = 0; x < numCols; x++){

        Tile t = map.getTile(x, y);
        entities.addAll(t.occupants());
        int spriteID = t.spriteID();
        Image img = GFX.spritesheet.get(spriteID);

        g2.drawImage(img, x*tileSize, y*tileSize, tileSize, tileSize, null);
      }
    }

    for(Entity e : entities){
      Location loc = e.loc();
      Image sprite = GFX.spritesheet.get(e.spriteID());
      int spriteWidth  = sprite.getHeight(null);
      int spriteHeight = sprite.getWidth(null);
      /* Center it, by moving the sprite halfway up and halfway left */
      int renderX = loc.x() - spriteWidth / 2;
      int renderY = loc.y() - spriteHeight / 2;

      g2.drawImage(sprite, renderX, renderY, null);
    }
    entities.clear();
  }
}

