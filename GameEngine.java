package engine;

import java.awt.Color;

import graphics.GameView;
import objects.*;

public class GameEngine {
	private Map map;

	public GameEngine(){
		init("road.png", "game_over.png");
	}
	
	public void init(String map_n, String game_over){
		map = new Map(map_n, game_over, 7);
				
		for(int i = 0; i < 30; i++){
		Bonus m = new Bonus((int)(Math.random()*map.getWidth()), (int) (Math.random()*10000));
			map.addMapObject(m);
		}
		
		for(int i = 0; i < 10; i++){
			Car m = new Car((int)(Math.random()*map.getWidth()), (int) (Math.random()*10000) + (2 * Const.S_HEIGHT));
				map.addMapObject(m);
				map.addVehicle(m);
		}
		
		for(int i = 0; i < 10; i++){
			Truck m = new Truck((int)(Math.random()*map.getWidth()), (int) (Math.random()*10000)  + (2 * Const.S_HEIGHT));
				map.addMapObject(m);
				map.addVehicle(m);
		}
		
		for(int i = 0; i < 10; i++){
			Motorbike m = new Motorbike((int)(Math.random()*map.getWidth()), (int) (Math.random()*10000)  + (2 * Const.S_HEIGHT));
				map.addMapObject(m);
				map.addVehicle(m);
		}
	}
	
	public void makeObjects(){
		if (map.getVehicles().size() < 30){
			int i = (int)(Math.random()*3);
			switch(i){	
			case 1:
				Car c = new Car((int)(Math.random()*map.getWidth()), camera.getY() + (int) (Math.random()*800) + (Const.W_HEIGHT));
				map.addMapObject(c);
				map.addVehicle(c);
				break;
			case 2:
				Truck t = new Truck((int)(Math.random()*map.getWidth()), camera.getY() + (int) (Math.random()*800) + (Const.W_HEIGHT));
				map.addMapObject(t);
				map.addVehicle(t);
			case 3:
				Motorbike m = new Motorbike((int)(Math.random()*map.getWidth()), camera.getY() + (int) (Math.random()*800) + (Const.W_HEIGHT));
				map.addMapObject(m);
				map.addVehicle(m);
			}
		}
		if (map.getMapObjects().size() < 60){
			Bonus b = new Bonus((int)(Math.random()*map.getWidth()),  camera.getY() + (int) (Math.random()*800) + (Const.W_HEIGHT));
				map.addMapObject(b);
		}
	}
	
	public void removeObjects(Player player){
		for(MapObject m : map.getMapObjects()) {
			if (!(m instanceof Vehicle)){
				if(player.getY() - m.getY() > Const.W_HEIGHT){
					map.removeObject(m);
					break;
				}
			}
			
			else if(player.getY() - m.getY() > Const.W_HEIGHT * 3){
					map.removeVehicle((Vehicle)m);
					map.removeObject(m);
					break;
			}
		}
	}
	
	public boolean isCollision(MapObject m1, MapObject m2){
		int m1_x = (m1.getX() + Const.S_WIDTH / 2);
		int m1_y = (m1.getY() + Const.S_HEIGHT / 2);
		int m2_x = (m2.getX() + Const.S_WIDTH / 2);
		int m2_y = (m2.getY() + Const.S_HEIGHT / 2);
		if((m1_x - m1.getWidth() / 2 < m2_x + m2.getWidth() / 2) && 
			(m1_x + m1.getWidth() / 2 > m2_x - m2.getWidth() / 2) &&
			(m1_y + m1.getHeight() / 2 > m2_y - m2.getHeight() / 2) && 
			(m1_y - m1.getHeight() / 2 < m2_y + m2.getHeight() / 2)){
			return true;
		}
		return false;
	}
	
	public boolean isOutOfRoad(Player player){
		int playerX = (player.getX());
		if(playerX < 0 || ((playerX + Const.S_WIDTH) > Const.W_WIDTH))
			return true;
		
		else if((camera.getY() - player.getY()) > Const.W_HEIGHT / 2)
			return true;
		
		return false;
	}
	
	public void checkPlayerCollisions(Player player){
		if(isOutOfRoad(player))
			setPlayerDestroyed(player);
		
		MapObject m = null;
		for(int i = 1; i < map.getMapObjects().size(); i++){
			m = map.getMapObjects().get(i);
			if (m != player.getVehicle() && isCollision(player.getVehicle(), m)){ 
				if (!(m instanceof Vehicle)){
					player.addPoints(100);
					map.removeObject(m);
				}
				else
					setPlayerDestroyed(player);
			}
		}
	}
	
	public void setPlayerDestroyed(Player player){
		player.setSpeed(0);
		player.setLost();
		//smoke
	}
	
	public void checkOCollisions(Player player){
		for(Vehicle v1 : map.getVehicles()) {
			for(Vehicle v2 : map.getVehicles()) {
				if(v1 != player.getVehicle() && v1 != v2 && isCollision(v1, v2)){
						v1.setSpeed(0);
						//smoke
					break;
				}
			}
		}
	}
	
	public void updateVehicles(){
		for(Vehicle v : map.getVehicles())
			v.update();
	}
	
	public Map getMap(){
		return map;
	}
	
	public void addPlayer(Player player){
		player.getVehicle().setSpeed(10);
		map.addMapObject(player.getVehicle());
		map.addVehicle(player.getVehicle());
	}
	
	
	public void updatePlayer(Player player, boolean up, boolean down, boolean left, boolean right){
		if(up)
			player.setSpeed(player.getSpeed() + 0.1);
		if(down)
			player.setSpeed(player.getSpeed() - 0.3);
		if(left)
			player.setX(player.getX() - (int)Math.ceil(player.getSpeed() * 0.3));
		if(right)
			player.setX(player.getX() + (int)Math.ceil(player.getSpeed() * 0.3));
	}
}
