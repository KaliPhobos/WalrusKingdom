package CodeW;
public class Map {
	// The Array MAP is an Integer Array containing the Block-IDs of all Blocks used in the map.
	// The Engine addresses the BLOCKS array using the BlockIDs and gets the PictureIDs in return.
	// These Picture-IDs will then be used to address the PICTURES array which is similar to the BLOCKS array
	// as it also uses both ID and POS to define it's items.
	public static int[][] Map;		//ForegroundIDs, BackgroundIDs, TriggerIDs combined at %901
	
	
	public static void setSize(int _x, int _y) {		// Set size of Map;
		Map = new int[_x][_y];
	}
	public static int getForegroundID(int _x, int _y) {
		double _data = Map[_x][_y];
		return (int) ((_data-(_data%901)-901*(((_data-(_data%901))/901)%(901)))/(901*901))%901;
	}
	public static int getForegroundID(int _data) {
		return (int) ((_data-(_data%901)-901*(((_data-(_data%901))/901)%(901)))/(901*901))%901;
	}
	public static int getBackgroundID(int _x, int _y) {
		double _data = Map[_x][_y];
		return (int) ((_data-(_data%901))/901)%(901);
	}
	public static int getBackgroundID(int _data) {
		return (int) ((_data-(_data%901))/901)%(901);
	}
	public static int getTriggerID(int _x, int _y) {
		double _data = Map[_x][_y];
		return (int) _data%901;
	}
	public static int getTriggerID(int _data) {
		return (int) _data%901;
	}
	public static int getWidth() {
		return Map.length;
	}
	public static int getHeight() {
		return Map[0].length;
	}
	public static int get(int _x, int _y) {
		return Map[_x][_y];
	}
	public static void createRandom(int _min, int _max) {
		for(int _x=1;_x<getWidth();_x++) {
			for(int _y=1;_y<getWidth();_y++) {
				int rand1 = (int) (Math.random()*(_max-_min)+_min);
				int rand2 = (int) (Math.random()*(_max-_min)+_min);
				int rand3 = (int) (Math.random()*(_max-_min)+_min);
				int _data = createData(rand1, rand2, rand3);
				Map[_x][_y] = _data;
			}
		}
		
	}
	public static int createData(int _foreground, int _background, int _trigger) {
		return 	_foreground*901*901+_background*901+_trigger;

	}
	
	public static void loadCity1() {
		int[] line = { 6,  7,  0, 69, 70, 71, 72,  0,  3, 39, 40, 41, 42,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
		for(int _i=0;_i<100;_i++) {
			Map[_i][30] = line[_i];
		}
	}
}
