package dpoints;

import matrix.Vector;

public class Point3d extends AbstractPoint {


	public double x;
	public double y;
	public double z;

	public Point3d(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public AbstractPoint add(AbstractPoint point) {

		Point3d p = (Point3d) point;

		return new Point3d(this.x + p.x, this.y + p.y, this.z + p.z);

	}

	@Override
	public AbstractPoint sub(AbstractPoint point) {

		Point3d p = (Point3d) point;

		return new Point3d(this.x - p.x, this.y - p.y, this.z - p.z);

	}

	@Override
	public AbstractPoint mul(double scalar) {
		return new Point3d(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	@Override
	public AbstractPoint clone() {
		return new Point3d(this.x, this.y, this.z);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public Vector toVector()
	{
		return new Vector(x, y, z, 1);
	}

	@Override
	public void normalise() 
	{
		double factor = 1/Math.sqrt(x * x + y * y + z * z);

		x *= factor;
		y *= factor;
		z *= factor;
	}
	
	@Override
	public void scale(double scale) 
	{
		x *= scale;
		y *= scale;
		z *= scale;
	}

}
