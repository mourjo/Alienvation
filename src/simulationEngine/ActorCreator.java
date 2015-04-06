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
		Slice[][] slices = Simulator.getInstance().getSlices();
		
		for(int i = 0; i < count; i++)
			basicPlayerShips.add(new BasicPlayerShip(slices[gen.nextInt(slices.length)][gen.nextInt(slices[0].length)]));
	}
	
	public void createBasicAlienShip(List<? super BasicAlienShip> basicAlienShips, int count)
	{
		Simulator simulator = Simulator.getInstance();
		for(int i = 0; i < simulator.getHeight(); i += (simulator.getHeight())/count)
			basicAlienShips.add(new BasicAlienShip(simulator.getWidth(), (i + gen.nextInt(40))%(simulator.getHeight()+20)));
	}
}
