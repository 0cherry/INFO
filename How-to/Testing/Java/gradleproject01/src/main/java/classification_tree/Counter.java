package classification_tree;

public class Counter {
	
	public int count(int[] a, int x) {
		int count = 0;
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) count++;
		}
		return count;
	}

}
