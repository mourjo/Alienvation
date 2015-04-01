package simulationEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import entities.Actor;
import entities.AlienBullet;
import entities.AlienShip;
import entities.Bullet;
import entities.PlayerBullet;
import entities.PlayerShip;
import entities.Ship;

public class ActorSet {

	private List<PlayerShip> playerShips;
	private List<AlienShip> alienShips;
	
	private List<PlayerBullet> playerBullets;
	private List<AlienBullet> alienBullets;
	
	private Map<Integer, List<? extends Actor>> allActors;
	
	/*ActorSet()
	{
		playerShips = new CopyOnWriteArrayList<PlayerShip>(new ArrayList<PlayerShip>());
		alienShips = new CopyOnWriteArrayList<AlienShip>(new ArrayList<AlienShip>());

		playerBullets = new CopyOnWriteArrayList<PlayerBullet>(new ArrayList<PlayerBullet>());
		alienBullets = new CopyOnWriteArrayList<AlienBullet>(new ArrayList<AlienBullet>());
		
		allActors = new HashMap<Integer, List<? extends Actor>>();
		allActors.put(Actor.PLAYER_BULLET, playerBullets);
		allActors.put(Actor.ALIEN_BULLET, alienBullets);
		allActors.put(Actor.PLAYER_SHIP, playerShips);
		allActors.put(Actor.ALIEN_SHIP, alienShips);
	}*/
	
	ActorSet()
	{
		playerShips = new ArrayList<PlayerShip>();
		alienShips = new ArrayList<AlienShip>();

		playerBullets = new ArrayList<PlayerBullet>();
		alienBullets = new ArrayList<AlienBullet>();
		
		allActors = new HashMap<Integer, List<? extends Actor>>();
		allActors.put(Actor.PLAYER_BULLET, playerBullets);
		allActors.put(Actor.ALIEN_BULLET, alienBullets);
		allActors.put(Actor.PLAYER_SHIP, playerShips);
		allActors.put(Actor.ALIEN_SHIP, alienShips);
	}
	
	public Map<Integer, List<? extends Actor>> getActors()
	{
		return allActors;
	}
	
	public List<Ship> getShips()
	{
		List<Ship> ships = new CopyOnWriteArrayList<Ship>(new ArrayList<Ship>());
		ships.addAll(playerShips);
		ships.addAll(alienShips);
		return ships;
	}
	
	public List<Bullet> getBullets()
	{
		List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>(new ArrayList<Bullet>());
		bullets.addAll(playerBullets);
		bullets.addAll(alienBullets);
		return  bullets;
	}
	
	public List<PlayerShip> getPlayerShips()
	{
		return playerShips;
	}
	
	public List<AlienShip> getAlienShips()
	{
		return alienShips;
	}
	
	public List<PlayerBullet> getPlayerBullets()
	{
		return playerBullets;
	}
	
	public List<AlienBullet> getAlienBullets()
	{
		return alienBullets;
	}
}
