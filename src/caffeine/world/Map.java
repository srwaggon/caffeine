package caffeine.world;


import java.util.ArrayList;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.Sprite;

public class Map{
	private static int numMaps = 0;
	public int id;
	private int tileSize = 32;
	private int height, width;
	private Tile[][] map;

	// TODO abstract out tileSize
	public Map(int id, int cols, int rows, int tileSize){
		this.id = id;
		height = rows; width = cols;
		this.tileSize = tileSize;
		Tile.setSize(tileSize);
		map = new Tile[cols][rows];
		
		for(int y = 0; y < width; y++){
			for(int x = 0; x < height; x++){ // TODO fix mapIDs
				map[x][y] = new Tile();
			}
		}
		numMaps++;
	}
	public Map(String s){
		Scanner scans = new Scanner(s);
		this.id = numMaps;
		width = Integer.parseInt(scans.next());
		height = Integer.parseInt(scans.next());
		this.tileSize = Integer.parseInt(scans.next());
		Tile.setSize(tileSize);
		
		map = new Tile[width][height];
		scans.nextLine();
		for(int y = 0; y < height; y++){
			String line = scans.nextLine();
			for(int x = 0; x < width; x++){
				map[x][y] = new Tile(line.charAt(x));
			}
		}
		numMaps++;
	}

	public Tile get(int x, int y){
		if (x < 0) x = 0;
		if (y < 0) y = 0;
		if (x >= width) x = width - 1;
		if (y >= height) y = height -1;
		return map[x][y];
	}
	
	public Tile getTileAt(int x, int y){
		return get(x/tileSize, y/tileSize);
	}

	public int height(){return height;}
	
	public int width(){return width;}
	
	public int tileSize(){return tileSize;}
	
	public ArrayList<Entity> entities(){
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				entities.addAll(get(x, y).entities());
			}
		}
		return entities;
	}
	
	public boolean withinBounds(int x, int y){
		return  0 <= x && x < width*tileSize &&
				0 <= y && y < height*tileSize;
	}

	public ArrayList<Sprite> sprites(){
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Tile t = map[x][y];
				Location l = new Location(id, x*tileSize, y*tileSize);
				Sprite s = new Sprite(t.getType().getColor(), l, t.shape());
				sprites.add(s);
			}
		}
		for (Entity e : entities()){
			sprites.add(e.sprite());
		}
		return sprites;
	}
	
	public void tick(){
		clearOutDead();
		for(Entity e : entities()){
        	e.tick();
        }
	}
	public String toString(){
		return ""+id;
	}
	
	public String display(){
		String s = "";
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				s += map[x][y];
			}
			s += "\n";
		}
		return s;
	}

	// MUTATORS

	public void clearOutDead(){
		for (Entity e : entities()){
			if(!e.alive()){
				e.tile().remove(e);
			}
		}
	}
}