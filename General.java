package CodeW;

import java.sql.Time;

// This class is for whatever stuff I need to put somewhere but don't know where exactly ^^
public class General {
	public static long secondOld = 0;
	public static int fps = 0;
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
	public static int getBetween(int _min, int _x, int _max) {
		int _ans = getMax(_min, getMin(_max, _x));
		return _ans;
	}
	public static void sleep(int _i) {
		try { // wait 10ms to avoid any flicker
		    Thread.sleep(_i);
		    //System.currentTimeMillis();
		    long millis = System.currentTimeMillis();
		    if (millis - millis%1000 == secondOld) {
		    	fps++;
		    } else {
		    	System.out.println(fps);				// Will give me the current FPS
		    	fps = 0;								// 115 heavy load (running around)	(5000 max with no sleep)
		    }											// 185 idle							(9500 max with no sleep)
		    secondOld = millis - millis%1000;
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}
