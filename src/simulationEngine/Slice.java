package simulationEngine;

public class Slice {
	private double x, y;
	private double height, width;
	
	public Slice(double x, double y, double w, double h)
	{
		this.x = x;
		this.y = y;
		height = h;
		width = w;
	}
	
	public void setValues(double x, double y, double w, double h)
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
}
