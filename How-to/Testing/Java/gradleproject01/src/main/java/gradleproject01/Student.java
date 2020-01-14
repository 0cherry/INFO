package gradleproject01;

public class Student {
	private double mids;
	private double finals;
	private double homeworks;
	
	public Student(double m, double f, double h) {
		mids = m;
		finals = f;
		homeworks = h;
	}
	
	public double getMids() {
		return mids;
	}
	
	public void setMids(double mids) {
		this.mids = mids;
	}
	
	public double getFinals() {
		return finals;
	}
	
	public void setFinals(double finals) {
		this.finals = finals;
	}
	
	public double getHomeworks() {
		return homeworks;
	}
	
	public void setHomeworks(double homeworks) {
		this.homeworks = homeworks;
	}
}
