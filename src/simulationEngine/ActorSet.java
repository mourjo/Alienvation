package simulationEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entities.Actor;

public class ActorSet {

	public List<Actor> allActorList;
	
	ActorSet()
	{
		allActorList = new CopyOnWriteArrayList<Actor>(new ArrayList<Actor>());
	}
	
	synchronized void removeActors(List<?extends Actor> list)
	{
		allActorList.removeAll(list);
	}
	
	public List<Actor> getActorList()
	{		
		return allActorList;
	}
	
	public int getAlienShipCount()
	{
		int alienShipCount = 0;
		for(Actor a : allActorList)
			if(a.getType() == Actor.ALIEN_SHIP)
				alienShipCount++;
		return alienShipCount;
	}
	
}
