package whitebox;

public class Coverage {
	enum C3Score {PASS, FAIL};
	
	public int foo(int x, int y, int z) {
		int w = 0;
		if ((x>0 || (y>0)))
			z = z + 10;
		if (z>10)
			w = 10;
		return w;
	}
	
	public int coverage(int x, int y, int z) {
		if ((x>0) && (y>0))
			z = x - y;
		if ((x>2) || (z==0))
			z = z + 1;
		return z;
	}
	
	public int exam(int c1, int c2, C3Score c3) {
		boolean a1, a2, a3;
		int points = 0;
		
		a1 = (c1>50) || (c2>50);
		a2 = a1 && (c3==C3Score.PASS);
		
		if (a2)
			points = (c1+c2)/2;
		
		return points;
	}
}
