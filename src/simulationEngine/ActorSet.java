package simulationEngine;

import java.util.HashSet;
import java.util.Set;

import entities.AlienBullet;
import entities.AlienShip;
import entities.Bullet;
import entities.PlayerBullet;
import entities.PlayerShip;
import entities.Ship;

public class ActorSet {

	private Set<PlayerShip> playerShips;
	private Set<AlienShip> alienShips;
	
	private Set<PlayerBullet> playerBullets;
	private Set<AlienBullet> alienBullets;
	
	ActorSet()
	{
		playerShips = new HashSet<PlayerShip>();
		alienShips = new HashSet<AlienShip>();

		playerBullets = new HashSet<PlayerBullet>();
		alienBullets = new HashSet<AlienBullet>();
	}
	
	public Set<Ship> getShips()
	{
		Set<Ship> allShips = new HashSet<Ship>();
		allShips.addAll(playerShips);
		allShips.addAll(alienShips);
		return  allShips;
	}
	
	public Set<Bullet> getBullets()
	{
		Set<Bullet> allBullets = new HashSet<Bullet>();
		allBullets.addAll(playerBullets);
		allBullets.addAll(alienBullets);
		return  allBullets;
	}
	
	public Set<PlayerShip> getPlayerShips()
	{
		return playerShips;
	}
	
	public Set<AlienShip> getAlienShips()
	{
		return alienShips;
	}
	
	public Set<PlayerBullet> getPlayerBullets()
	{
		return playerBullets;
	}
	
	public Set<AlienBullet> getAlienBullets()
	{
		return alienBullets;
	}
}
