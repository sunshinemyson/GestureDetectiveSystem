package GestureDetectiveSystem.Detectors.Math;

public class MPoint {

	public static int INVALID_VALUE = -1;
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
		// TODO
		return (s.x - e.x) + (s.y - e.y);
	}

	public static MPoint makeInvalidValue() {
		return new MPoint();
	}

	public boolean valid() {
		return (x != INVALID_VALUE) && (y != INVALID_VALUE);
	}

}
