package CodeW;

// This class is for whatever stuff I need to put somewhere but don't know where exactly ^^
public class General {
	public static int getMax(int _a, int _b) {
		if(_a>_b) {
			return _a;
		} else {
			return _b;
		}
	}
	public static int getMax(int[] _a) {
		int _max = -999999;
		for(int i=0;i<_a.length;i++) {
			if(_a[i]>_max) {
				_max=_a[i];
			}
		}
		return _max;
	}
	public static int getMin(int _a, int _b) {
		if(_a<_b) {
			return _a;
		} else {
			return _b;
		}
	}
	public static int getMin(int[] _a) {
		int _max = 999999;
		for(int i=0;i<_a.length;i++) {
			if(_a[i]<_max) {
				_max=_a[i];
			}
		}
		return _max;
	}
	public static void sleep(int _i) {
		try { // wait 10ms to avoid any flicker
		    Thread.sleep(_i);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}
