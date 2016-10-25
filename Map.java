package CodeW;
public class Map {
	// The Array MAP is an Integer Array containing the Block-IDs of all Blocks used in the map.
	// The Engine addresses the BLOCKS array using the BlockIDs and gets the PictureIDs in return.
	// These Picture-IDs will then be used to address the PICTURES array which is similar to the BLOCKS array
	// as it also uses both ID and POS to define it's items.
	public static long[][] Map;		//ForegroundIDs, BackgroundIDs, TriggerIDs combined at %901
	
	
	public static void setSize(int _x, int _y) {		// Set size of Map;
		Map = new long[_x][_y];
	}
	public static int getForegroundID(int _x, int _y) {
		double _data = Map[_x][_y];
		return (int) ((_data-(_data%901))/901)%901;
	}
	public static int getForegroundID(double _data) {
		return (int) ((_data-(_data%901))/901)%901;
	}
	public static int getBackgroundID(int _x, int _y) {
		double _data = Map[_x][_y];
		return (int) _data%901;
	}
	public static int getBackgroundID(double _data) {
		return (int) _data%901;
	}
	public static int getWidth() {
		return Map.length;
	}
	public static int getHeight() {
		return Map[0].length;
	}
	public static long get(int _x, int _y) {
		return Map[_x][_y];
	}
	public static void createRandom(int _min, int _max) {
		for(int _x=0;_x<getWidth();_x++) {
			for(int _y=0;_y<getWidth();_y++) {
				int _background = 12;
				int _foreground = 0;
				long _data = createData(_foreground, _background);
				Map[_x][_y] = _data;
			}
		}
		
	}
	public static long createData(int _foreground, int _background) {
		return (long) _foreground*901+_background;

	}
	
	public static void loadCity1() {
		long[] line30 = { 106, 106, 10, 106, 12, 901*38+11, 39, 40, 901*41+10, 9019,  11, 51, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0};
		for(int _i=0;_i<100;_i++) {Map[_i][30] = line30[_i];}		
	}
}
