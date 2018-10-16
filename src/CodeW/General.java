package CodeW;


// This class is for whatever stuff I need to put somewhere but don't know where exactly ^^
public class General {
	public static long secondOld = 0;
	public static int fps = 0;
	public static boolean showFPS = true;			// show FPS counter
	public static boolean showTrigger = false;		// show information about trigger areas
	public static boolean showMapChanges = false;	// shows information about map overlays
	public static boolean showTeleport = true;		// show information about silent teleportations
	public static boolean showClasses = true;		// show calls of main classes
	public static boolean showToDo = true;			// show current processed item during startup
	public static boolean showTileUpdates = false;	// shows tile update counters and additional information
	public static boolean showDebug = true;			// shows additional information when pressing debug key
	public static boolean showInteractions = true;	// shows information about passive triggers (user input required to trigger)
	public static boolean showCritical = true;		// Information about critical failures (corrupted map data and stuff)
	public static int currentZoom;
	public static int getMax(int _a, int _b) {
		if(_a>_b) {
			return _a;
		} else {
			return _b;
		}
	}
	public static int getMax(int[] _a) {
		int _max = _a[0];
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
	public static int getMin(long _a, long _b) {
		if(_a<_b) {
			return (int) _a;
		} else {
			return (int) _b;
		}
	}
	public static int getMin(int[] _a) {
		int _max = _a[0];
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
	public static int getBetween(int _min, long _x, int _max) {
		int _ans = getMax(_min, (int) getMin((long) _max, _x));
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
		    	if(showFPS==true) {
		    		General.DebugLog(fps + "Frames/s");			// Will give me the current FPS
		    	}
		    	fps = 0;								// 115 heavy load (running around)	(5000 max with no sleep)
		    }											// 185 idle							(9500 max with no sleep)
		    secondOld = millis - millis%1000;
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public static int adaptZoom(int input) {
		return (input*currentZoom)/24;
	}
	public static int adaptResolution(int input) {
		return input+Screen.getWidth()*12;
	}
	public static void updateZoomFactor() {
		currentZoom = Screen.getZoom();
	}
	
	public static void preloadImages() {	// preloading images to avoid a lag the first time they're used
		DebugLog("Preloading images ...");
		Trigger.infobox = new TileSource("/CodeW/assets/infobox.png", window.blocksize);
		Intro.textbox = new TileSource("/CodeW/assets/textbox.png", window.blocksize);
		DebugLog("... Done\nPreloading font...");
		TileArea.m_image.createGraphics().drawString("", 0, 0);
		DebugLog("... Done");
	}
	
	public static void DebugLog(String text) {
		long time = System.currentTimeMillis();
		System.out.println((time-window.LaunchTimestamp)/1000.0 + "s (+" + (time-secondOld)/1000.0 + "s)   " + text);
	}
	public static void DebugLog(double text) {
		long time = System.currentTimeMillis();
		System.out.println((time-window.LaunchTimestamp)/1000.0 + "s (+" + (time-secondOld)/1000.0 + "s)   " + text);
	}
	public static void DebugLog(int text) {
		long time = System.currentTimeMillis();
		System.out.println((time-window.LaunchTimestamp)/1000.0 + "s (+" + (time-secondOld)/1000.0 + "s)   " + text);
	}
	public static void DebugLog(int[] text) {
		String tempString = "" + text[0];
		for(int temp = 1; temp < text.length; temp++) {
			tempString = tempString + ", " + text[temp];
		}
		long time = System.currentTimeMillis();
		System.out.println((time-window.LaunchTimestamp)/1000.0 + "s (+" + (time-secondOld)/1000.0 + "s)   " + tempString);
	}
	public static void DebugLog(long[] text) {
		String tempString = "" + text[0];
		for(int temp = 1; temp < text.length; temp++) {
			tempString = tempString + ", " + text[temp];
		}
		long time = System.currentTimeMillis();
		System.out.println((time-window.LaunchTimestamp)/1000.0 + "s (+" + (time-window.LaunchTimestamp)/1000.0 + "s)   " + tempString);
	}
	public static double[][] wipedMatrix(double[][] matrix) {
		int xmax = matrix.length;
		int ymax = matrix[0].length;
		for (int y=0;y<ymax;y++) {
			for (int x=0;x<xmax;x++) {
				matrix[x][y] = 0.0;
			}
		}
		return matrix;
	}
}
