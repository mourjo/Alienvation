package simulationEngine;

import java.util.Set;

import entities.Actor;
import entities.BasicAlienShip;
import entities.BasicPlayerShip;

public class ActorCreator {
	
	static ActorCreator _singleton = null;

	private ActorCreator()
	{
		
	}
	
	public ActorCreator getInstance()
	{
		if(_singleton == null)
			return new ActorCreator();
		else
			return _singleton;
	}
	
	public void createBasicPlayerShip(Set<Actor> basicPlayerShips)
	{
		basicPlayerShips.add(new BasicPlayerShip());
	}
	
	public void createBasicAlienShip(Set<Actor> basicAlienShips)
	{
		basicAlienShips.add(new BasicAlienShip());
	}
}
