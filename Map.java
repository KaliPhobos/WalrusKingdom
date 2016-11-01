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
		for(int _i=0;_i<100;_i++) {Map[_i][24] = new long[] { 107, 107, 107, 107, 107, 901*108+14, 901*108+10, 10, 10, 14,					106, 14, 14, 10, 11, 10, 10, 0, 0, 0,								0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][25] = new long[] { 107, 107, 107, 107, 107, 107, 107, 901*108+10, 10, 10,						12, 14, 10, 901*108+10, 10, 10, 10, 0, 0, 0,						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][26] = new long[] { 107, 107, 107, 106, 107, 901*20+106, 901*21+106, 901*24+106, 901*25+10, 10,	12, 901*20+12, 901*21+10, 901*22+106, 901*23+10, 10, 10, 0, 0, 0,	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][27] = new long[] { 107, 107, 107, 10, 106, 26, 27, 28, 29, 13,									901*108+12, 26, 27, 32, 33, 10, 10, 0, 0, 0,						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][28] = new long[] { 107, 107, 107, 901*108+14, 13, 26, 27, 44, 45, 10,							107, 26, 27, 28, 29, 10, 10, 0, 0, 0,								0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][29] = new long[] { 107, 107, 106, 107, 13, 901*34+11, 35, 36, 901*37+10, 901*4+10,				106, 901*34+11, 35, 42, 901*43+10, 10, 10, 0, 0, 0,					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][30] = new long[] { 106, 106, 10, 106, 12, 901*38+11, 39, 40, 901*41+10, 11,						10, 901*38+11, 39, 40, 901*41+10, 901*109+10, 10, 0, 0, 0,			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][31] = new long[] { 17, 17, 17, 17, 17, 17, 110, 111, 17, 17,										17, 17, 110, 111, 17, 17, 0, 0, 0, 0,								0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}

	}

}
