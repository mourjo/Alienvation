package simulationEngine;

public class Slice {
	private int x, y;
	private int height, width;
	
	public Slice(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		height = h;
		width = w;
	}
	
	public void setValues(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		height = h;
		width = w;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	@Override
	public String toString()
	{
		return (int)x+","+(int)y;
	}
}
