package simulationEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	ActorSet()
	{
		playerShips = new CopyOnWriteArrayList<PlayerShip>(new ArrayList<PlayerShip>());

		alienShips = new CopyOnWriteArrayList<AlienShip>(new ArrayList<AlienShip>());

		playerBullets = new CopyOnWriteArrayList<PlayerBullet>(new ArrayList<PlayerBullet>());
		alienBullets = new CopyOnWriteArrayList<AlienBullet>(new ArrayList<AlienBullet>());
	}
	
	public List<Ship> getShips()
	{
		List<Ship> allShips = new CopyOnWriteArrayList<Ship>(new ArrayList<Ship>());
		allShips.addAll(playerShips);
		allShips.addAll(alienShips);
		return  allShips;
	}
	
	public List<Bullet> getBullets()
	{
		List<Bullet> allBullets = new CopyOnWriteArrayList<Bullet>(new ArrayList<Bullet>());
		allBullets.addAll(playerBullets);
		allBullets.addAll(alienBullets);
		return  allBullets;
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
