package GestureDetectiveSystem.Detectors.Math;

public class MPoint {

	public static final int INVALID_VALUE = -1;
	public int x;
	public int y;

	public MPoint() {
		x = y = INVALID_VALUE;
	}

	public MPoint(int ax, int ay) {
		x = ax;
		y = ay;
	}

	public static float distance(MPoint s, MPoint e) {
		return (float)Math.sqrt( Math.pow((s.x - e.x),2.0) + Math.pow((s.y - e.y),2.0) );
	}

	public static MPoint makeInvalidValue() {
		return new MPoint();
	}

	public boolean valid() {
		return (x != INVALID_VALUE) && (y != INVALID_VALUE);
	}

}
