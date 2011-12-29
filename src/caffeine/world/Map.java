package caffeine.world;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.Sprite;
import caffeine.world.tile.Tile;

public class Map{
	protected int height, width;
	protected Tile[][] map;
	protected int tileSize = 32;
	protected ArrayList<Entity> entities = new ArrayList<Entity>();

	public Map(){
		this("10 10 32 "+
				"##########"+
				"#........#"+
				"#.....#..#"+
				"#.#......#"+
				"#.......##"+
				"#..#.....#"+
				"#......#.#"+
				"#...#....#"+
				"#........#"+
				"##########");
	}

	public Map(int cols, int rows, int tileSize){
		height = rows;
		width = cols;
		this.tileSize = tileSize;
		map = new Tile[cols][rows];

		for(int y = 0; y < width; y++){
			for(int x = 0; x < height; x++){
				map[x][y] = new Tile();
			}
		}
	}
	public Map(String s){
		Scanner scans = new Scanner(s);
		width = Integer.parseInt(scans.next());
		height = Integer.parseInt(scans.next());
		tileSize = Integer.parseInt(scans.next());

		map = new Tile[width][height];
		String line = scans.next();
		for(int i = 0; i < height*width; i++){
			char c = line.charAt(i);
			System.err.print(c);
			map[i%width][i/width] = new Tile(c);
		}
		System.err.println();
	}

	public Tile get(int x, int y){
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (x >= width) {
			x = width - 1;
		}
		if (y >= height) {
			y = height -1;
		}
		return map[x][y];
	}

	public Tile getTileAt(int x, int y){
		return get(x/tileSize, y/tileSize);
	}

	public int height(){return height;}

	public int width(){return width;}

	//public int tileSize(){return Tile.size;}

	public ArrayList<Entity> entities(){

		return entities;

		/* Entities is updated on every screen render.
		 * Not really safe, but should work fine as long as the screen is rendering.
		 * AKA, if the screen isn't rendering, the game isn't running. =(
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				entities.addAll(get(x, y).entities());
			}
		}
		return entities;
		 */
	}

	public boolean withinBounds(int x, int y){
		return  0 <= x && x < width*tileSize &&
				0 <= y && y < height*tileSize;
	}

	public ArrayList<Sprite> tileSprites(){
		entities = new ArrayList<Entity>();
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();

		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Tile t = map[x][y];
				// Because the map ID isn't important for placing the sprites.
				Location l = new Location(-1, x*tileSize, y*tileSize);
				Sprite s = new Sprite(t.getType().color(), l, new Rectangle(x*tileSize, y*tileSize, tileSize, tileSize));
				sprites.add(s);
				// Side effect.. Necessary to update entities.
				entities.addAll(t.entities());
			}
		}
		/*
		for (Entity e : entities()){
			sprites.add(e.sprite());
		}
		 */
		return sprites;
	}

	public void run(){
		for(Entity e : entities()){
			e.tick();
		}
	}
	public String toString(){
		String s = "";
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				s += map[x][y];
			}
			s += "\n";
		}
		return s;
	}
}