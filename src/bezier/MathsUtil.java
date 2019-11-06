package bezier;

public class MathsUtil {

	public static int factorial(int n)
	{
		assert(n > -1);
		
		int result = 1;
		
		for (int i = 1; i <= n; i++)
			result = result * i;
			
		return result;
	}

	public static int nCk(int n, int k)
	{

		int denom = (factorial(k) * factorial(n - k));
		int numer =  factorial(n);

		if (denom == 0)
			throw new RuntimeException("Denominator was 0 in nCk, n=" + n + ", k=" + k);
		
		return numer / denom;
	}

	public static double bernstein(int n, int i, double t)
	{
		return nCk(n, i) * Math.pow(1 - t, n - i) * Math.pow(t, i);
	}
	

}
