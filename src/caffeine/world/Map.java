package caffeine.world;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import caffeine.entity.Entity;
import caffeine.view.Sprite;

public class Map{
	private ArrayList<Entity> entities;
	private int tileSize = 32;
	private int height, width;
	private HashMap<Direction, Map> neighbors = new HashMap<Direction, Map>();
	private Tile[][] board;

	public Map(int cols, int rows, int tileSize){
		entities = new ArrayList<Entity>();
		height = rows; width = cols;
		this.tileSize = tileSize;
		board = new Tile[cols][rows];

		for(int c = 0; c < cols; c++){
			for(int r = 0; r < rows; r++){ // TODO fix mapIDs
				board[c][r] = new Tile(new Location(0,c*tileSize,r*tileSize), tileSize, new Grass());
			}}}


	public static Map read(String input){
		Scanner scans = new Scanner(input);

		int width = scans.nextInt();
		int height = scans.nextInt();
		int tileSize = scans.nextInt();

		//TODO fix parameters of x and y of map
		Map b = new Map(width, height, tileSize);
		String remaining = scans.next();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				b.getTile(x, y).read(remaining.charAt((y*width)+x));
			}
		}
		return b;
	}

	public Tile getTileAt(int x, int y){
		return getTile(x/tileSize, y/tileSize);
	}
	public Tile getTileAt(Point p){
		return getTileAt(p.x, p.y);
	}

	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public Tile getTile(int x, int y){
		if (x < 0) x = 0;
		if (y < 0) y = 0;
		if (x >= width) x = width - 1;
		if (y >= height) y = height -1;
		return board[x][y];
	}
	public int getTileSize(){
		return tileSize;
	}
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public boolean withinBounds(Point p){
		return  0 <= p.x && p.x < width*tileSize &&
				0 <= p.y && p.y < height*tileSize;
	}

	public ArrayList<Sprite> getSprites(){
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int c = 0; c < board.length; c++){
			for(int r = 0; r < board[c].length; r++){
				sprites.add(board[c][r].getSprite());
			}
		}
		for (Entity e : entities){
			sprites.add(e.getSprite());
		}
		return sprites;
	}
	
	public void tick(){
		clearOutDead();
		for(Entity e : entities){
        	e.tick();
        }
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				s += board[j][i];
			}
			s += "\n";
		}
		return s;
	}

	// MUTATORS
	public void add(Entity e){
		entities.add(e);
	}
	public void remove(Entity e){
		entities.remove(e);
	}

	public void clearOutDead(){
		ArrayList<Entity> dead = new ArrayList<Entity>();
		for (Entity e : entities){
			if(!e.isAlive()){
				dead.add(e);
			}
		}
		for(Entity e : dead){
			entities.remove(e);
		}
	}


	public Map getNeighbors(Direction d) {
		return neighbors.get(d);
	}


	public void setNeighbor(Direction d, Map m) {
		neighbors.put(d, m);
	}
}