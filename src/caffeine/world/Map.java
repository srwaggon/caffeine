package caffeine.world;

import java.util.ArrayList;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.Sprite;
import caffeine.world.tile.Tile;

public class Map extends Thread{
	private int height, width;
	private Tile[][] map;

	public Map(){
		this("10 10 32 "+
				"##########"+
				"#........#"+
				"#........#"+
				"#........#"+
				"#........#"+
				"#........#"+
				"#........#"+
				"#........#"+
				"#........#"+
				"##########");
	}

	public Map(int cols, int rows, int tileSize){
		height = rows; width = cols;
		Tile.setSize(tileSize);
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
		Tile.setSize(Integer.parseInt(scans.next()));

		map = new Tile[width][height];
		String line = scans.next();
		for(int i = 0; i < height*width; i++){
			char c = line.charAt(i);
			System.out.println(c);
			map[i%width][i/width] = new Tile(c);
		}
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
		return get(x/Tile.size(), y/Tile.size());
	}

	public int height(){return height;}

	public int width(){return width;}

	//public int tileSize(){return Tile.size;}

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
		return  0 <= x && x < width*Tile.size() &&
				0 <= y && y < height*Tile.size();
	}

	public ArrayList<Sprite> sprites(){
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Tile t = map[x][y];
				// Because the map ID isn't important for placing the sprites.
				Location l = new Location(-1, x*Tile.size(), y*Tile.size());
				Sprite s = new Sprite(t.getType().color(), l, t.shape());
				sprites.add(s);
			}
		}
		for (Entity e : entities()){
			sprites.add(e.sprite());
		}
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