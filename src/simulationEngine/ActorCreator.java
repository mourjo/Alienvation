package simulationEngine;

import java.util.List;

import entities.BasicAlienShip;
import entities.BasicPlayerShip;

public class ActorCreator {
	
	static ActorCreator _singleton = null;

	private ActorCreator()
	{
		
	}
	
	static public ActorCreator getInstance()
	{
		if(_singleton == null)
			_singleton =  new ActorCreator();
		return _singleton;
	}
	
	public void createBasicPlayerShip(List<? super BasicPlayerShip> basicPlayerShips)
	{
		basicPlayerShips.add(new BasicPlayerShip());
	}
	
	public void createBasicAlienShip(List<? super BasicAlienShip> basicAlienShips)
	{
		basicAlienShips.add(new BasicAlienShip());
	}
}
