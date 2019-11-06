package dpoints;

import matrix.Vector;

public class Point2d extends AbstractPoint {


	public double x;
	public double y;

	public Point2d(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public AbstractPoint add(AbstractPoint point) {

		Point2d p = (Point2d) point;
		
		return new Point2d(this.x + p.x, this.y + p.y);
	}

	@Override
	public AbstractPoint sub(AbstractPoint point) {

		Point2d p = (Point2d) point;
		
		return new Point2d(this.x - p.x, this.y - p.y);
	}

	@Override
	public AbstractPoint mul(double scalar) {
		return new Point2d(this.x * scalar, this.y * scalar);
	}

	@Override
	public AbstractPoint clone() {
		return new Point2d(this.x, this.y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public Vector toVector() 
	{
		throw new RuntimeException("Unimplemented");
	}
	
	
	@Override
	public void normalise() 
	{
		double factor = 1/Math.sqrt(x * x + y * y);
		
		x *= factor;
		y *= factor;
	}
	
	@Override
	public void scale(double scale) 
	{
		x *= scale;
		y *= scale;
	}
}
