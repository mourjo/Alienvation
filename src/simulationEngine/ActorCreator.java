package simulationEngine;

import java.util.List;
import java.util.Random;

import entities.BasicAlienShip;
import entities.BasicPlayerShip;

public class ActorCreator {
	
	static ActorCreator _singleton = null;
	Random gen;

	private ActorCreator()
	{
		gen = new Random();
	}
	
	static public ActorCreator getInstance()
	{
		if(_singleton == null)
			_singleton =  new ActorCreator();
		return _singleton;
	}
	
	public void createBasicPlayerShip(List<? super BasicPlayerShip> basicPlayerShips, int count)
	{
		for(int i = 0; i < count; i++)
			basicPlayerShips.add(new BasicPlayerShip());
	}
	
	public void createBasicAlienShip(List<? super BasicAlienShip> basicAlienShips, int count)
	{
		Simulator simulator = Simulator.getInstance();
		for(int i = 30; i < simulator.getHeight() - 50; i += (simulator.getHeight() - 30 )/count)
			basicAlienShips.add(new BasicAlienShip(simulator.getWidth(), i - gen.nextInt(40)));
	}
}
