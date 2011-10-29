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
		map = new Tile[rows][cols];
		
		for(int c = 0; c < width; c++){
			for(int r = 0; r < height; r++){ // TODO fix mapIDs
				map[r][c] = new Tile();
			}
		}
		numMaps++;
	}


	public static Map read(String input){
		Scanner scans = new Scanner(input);

		int width = scans.nextInt();
		int height = scans.nextInt();
		int tileSize = scans.nextInt();

		//TODO fix parameters of x and y of map
		Map b = new Map(numMaps, width, height, tileSize);
		for(int y = 0; y < height; y++){
			String line = scans.nextLine();
			for(int x = 0; x < width; x++){
				b.get(x, y).read(line.charAt(x));
			}
		}
		return b;
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
		for(int r = 0; r < height; r++){
			for(int c = 0; c < width; c++){
				Tile t = map[c][r];
				Location l = new Location(id, c*tileSize, r*tileSize);
				Sprite s = new Sprite(t.getType().getColor(), l, t.shape());
				sprites.add(s);
			}
		}
		for (Entity e : entities()){
			sprites.add(e.getSprite());
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
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				s += map[j][i];
			}
			s += "\n";
		}
		return s;
	}

	// MUTATORS
	public void add(Entity e){
		map[e.x()/tileSize][e.y()/tileSize].add(e);
	}
	public void remove(Entity e){
		map[e.x()/tileSize][e.y()/tileSize].remove(e);
	}

	public void clearOutDead(){
		for (Entity e : entities()){
			if(!e.isAlive()){
				map[e.x()/tileSize][e.y()/tileSize].remove(e);
			}
		}
	}
}