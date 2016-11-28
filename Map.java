package CodeW;
public class Map {
	// The Array MAP is an Integer Array containing the Block-IDs of all Blocks used in the map.
	// The Engine addresses the BLOCKS array using the BlockIDs and gets the PictureIDs in return.
	// These Picture-IDs will then be used to address the PICTURES array which is similar to the BLOCKS array
	// as it also uses both ID and POS to define it's items.
	public static long[][] Map;		//ForegroundIDs, BackgroundIDs, TriggerIDs combined at %901
	public static String currentMapName = "initial";
	
	
	public static void setSize(int _x, int _y) {		// Set size of Map;
		Map = new long[_x][_y];
	}
	public static int getForegroundID(int _x, int _y) {
		double _data = Map[General.getBetween(0, _x, getWidth())][General.getBetween(0, _y, getHeight())];;
		return (int) ((_data-(_data%901))/901)%901;
	}
	public static int getForegroundID(double _data) {
		return (int) ((_data-(_data%901))/901)%901;
	}
	public static int getBackgroundID(int _x, int _y) {
		double _data = Map[General.getBetween(0, _x, getWidth())][General.getBetween(0, _y, getHeight())];;
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
		currentMapName = "City1";
		for(int _i=0;_i<100;_i++) {Map[_i][11] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*108+10, 901*107+10, 10, 15, 16, 901*107+10, 10, 10, 10, 10,				10, 10, 10, 10, 901*164+10, 901*165+10, 901*166+10, 901*167+10, 901*168+10, 901*169+10,						14, 10, 10, 14, 13, 14, 11, 10, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][12] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 10, 15, 16, 901*107+10, 10, 10, 10, 10,				10, 901*20+10, 901*21+10, 901*24+10, 901*154+161, 143, 150, 151, 901*152+29, 901*159+160,					901*21+10, 901*24+10, 901*25+10, 10, 13, 12, 10, 12, 901*107+10, 0,												0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][13] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	109, 109, 10, 15, 16, 901*107+10, 10, 10, 10, 10,							10, 30, 27, 28, 901*154+29, 143, 150, 151, 901*152+29, 901*159+26,											27, 28, 29, 10, 10, 10, 12, 14, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][14] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*107+10, 901*107+10, 109, 109, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	10, 10, 10, 15, 16, 901*107+10, 10, 10, 10, 10,											10, 26, 27, 28, 901*154+29, 155, 156, 157, 158, 901*159+26,													27, 28, 29, 10, 14, 12, 12, 14, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][15] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*107+10, 109, 10, 10, 109, 109, 901*107+10, 109,									10, 10, 10, 15, 16, 901*107+10, 10, 901*20+12, 901*21+10, 901*22+10,					901*23+10, 901*34+10, 901*35+10, 901*42+10, 144, 145, 146, 147, 148, 149,									901*35+10, 901*42+10, 901*43+11, 901*20+12, 901*21+13, 901*22+13, 901*23+10, 14, 901*107+10, 0,					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][16] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 109, 901*108+10, 901*108+10, 10, 10, 10, 109, 10,									10, 901*108+10, 10, 15, 16, 901*107+10, 10, 30, 27, 32,									33, 140, 39, 40, 134, 135, 136, 137, 138, 139,																39, 40, 141, 30, 27, 32, 33, 14, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][17] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10,	901*107+10, 901*107+10, 901*108+10, 901*107+10, 109, 10, 10, 10, 10, 10,									901*108+10, 901*107+10, 10, 15, 16, 901*107+13, 10, 26, 27, 28,							29, 14, 15, 111, 17, 17, 110, 111, 17, 17,																	110, 16, 13, 26, 27, 28, 29, 12, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][18] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 109, 901*107+10, 901*107+10, 901*107+10, 901*107+10,			109, 901*107+10, 901*107+10, 901*20+109, 901*21+10, 901*22+10, 901*23+10, 10, 901*20+12, 901*21+10,			901*22+109, 901*23+109, 10, 15, 16, 901*107+12, 10, 901*34+11, 35, 42,					901*43+10, 12, 132, 18, 18, 18, 112, 113, 18, 18,															18, 133, 11, 901*34+11, 35, 42, 901*43+10, 13, 901*107+10, 0,													0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][19] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*108+10, 901*107+10, 901*107+10, 901*107+10, 109,			10, 109, 901*107+10, 30, 27, 32, 33, 10, 30, 27,															32, 33, 10, 15, 16, 901*107+10, 12, 140, 39, 40,										141, 11, 12, 14, 12, 130, 110, 111, 131, 10,																10, 12, 12, 140, 39, 40, 141, 12, 901*107+10, 0,																0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][20] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 14,			10, 10, 109, 26, 27, 28, 29, 10, 26, 27,																	28, 29, 10, 15, 16, 901*107+13, 13, 10, 15, 16,											13, 11, 13, 130, 17, 110, 19, 19, 111, 17,																	131, 12, 10, 14, 15, 16, 13, 11, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][21] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 109, 109, 10,							901*108+10, 901*108+10, 901*108+10, 901*34+11, 35, 42, 901*43+10, 10, 901*34+11, 35,						42, 901*43+10, 10, 15, 16, 901*107+10, 13, 13, 15, 111,									17, 17, 17, 110, 19, 19, 901*46+19, 901*47+19, 19, 19,														111, 17, 17, 17, 110, 16, 13, 13, 901*107+10, 0,																0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][22] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 109, 10, 10, 901*108+10,							901*107+10, 901*107+10, 109, 140, 39, 40, 141, 10, 140, 39,													40, 141, 10, 15, 16, 901*107+10, 13, 10, 132, 18,										18, 18, 18, 112, 19, 19, 48, 49, 19, 19,																	113, 18, 18, 18, 18, 133, 11, 13, 901*107+10, 0,																0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][23] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 109, 10, 10, 10, 901*107+10,									901*107+10, 109, 10, 10, 15, 111, 17, 17, 17, 110,															111, 17, 17, 110, 16, 901*107+10, 13, 12, 12, 12,										11, 12, 12, 132, 18, 18, 112, 113, 18, 18,																	133, 10, 12, 10, 14, 12, 11, 12, 901*107+10, 0,																	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][24] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*108+14, 901*108+10, 10, 10, 109,							109, 14, 14, 10, 132, 18, 18, 18, 18, 18,																	18, 18, 18, 112, 16, 901*107+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10,		901*108+12, 901*108+12, 901*108+12, 901*108+13, 901*108+10, 901*108+10, 15, 16, 901*108+10, 901*108+10,		901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*107+10, 0,	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][25] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*107+10, 901*108+10, 10, 10,					12, 14, 10, 901*108+10, 10, 10, 10, 10, 14, 10,																901*108+10, 10, 10, 15, 16, 109, 109, 109, 109, 901*107+10,								109, 109, 109, 901*107+10, 109, 109, 901*79+142, 16, 109, 109,												109, 109, 109, 109, 109, 109, 109, 109, 109, 109,																0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][26] = new long[] { 901*107+10, 901*107+10, 901*107+10, 109, 901*107+10, 901*20+109, 901*21+109, 901*24+109, 901*25+10, 10, 					12, 901*20+12, 901*21+10, 901*22+109, 901*23+10, 10, 10, 10, 901*20+12, 901*21+10,							901*22+109, 901*23+10, 10, 15, 16, 12, 901*108+12, 901*108+12, 901*108+12, 109,			12, 901*20+12, 901*21+10, 901*22+109, 901*23+10, 10, 15, 16, 10, 901*20+10,									901*21+10, 901*22+10, 901*23+10, 10, 10, 901*20+10, 901*21+10, 901*22+10, 901*23+10, 10,						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][27] = new long[] { 901*107+10, 901*107+10, 901*107+10, 10, 109, 30, 27, 28, 29, 13,															901*108+12, 30, 27, 32, 33, 10, 10, 10, 30, 27,																32, 33, 10, 15, 16, 901*108+12, 901*107+13, 901*107+10, 901*107+10, 901*108+12,			901*108+12, 30, 27, 32, 33, 10, 15, 16, 10, 30,																27, 32, 33, 10, 10, 26, 27, 32, 33, 10,																			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][28] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*108+14, 13, 26, 27, 44, 45, 10,													901*107+10, 26, 27, 28, 29, 10, 14, 10, 26, 27,																28, 29, 14, 15, 16, 901*107+10, 109, 109, 109, 901*107+12, 								901*107+10, 26, 27, 28, 29, 10, 15, 16, 10, 26,																27, 28, 29, 10, 10, 26, 27, 28, 29, 10,																			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][29] = new long[] { 901*107+10, 901*107+10, 109, 901*107+10, 13, 901*34+11, 35, 36, 901*37+10, 10,											109, 901*34+11, 35, 42, 901*43+10, 901*108+10, 12, 12, 901*34+11, 35,										42, 901*43+10, 12, 15, 16, 109, 901*79+13, 901*78+11, 901*78+11, 109,					109, 901*34+11, 35, 42, 901*43+10, 901*108+10, 15, 16, 10, 901*34+11,										35, 42, 901*43+10, 10, 10, 901*34+11, 35, 42, 901*43+10, 10,													0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][30] = new long[] { 109, 109, 901*79+13, 109, 12, 140, 39, 40, 141, 11,																		10, 140, 39, 40, 141, 109, 10, 12, 140, 39,																	40, 141, 10, 15, 16, 12, 13, 11, 13, 12,												10, 140, 39, 40, 141, 109, 15, 16, 10, 140,																	39, 40, 141, 10, 10, 140, 39, 40, 141, 10,																		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][31] = new long[] { 17, 17, 17, 17, 17, 17, 110, 111, 17, 17,																					901*9+17, 17, 110, 111, 17, 17, 17, 17, 17, 110,															111, 17, 17, 110, 111, 17, 17, 17, 17, 17, 												17, 17, 110, 111, 17, 17, 110, 111, 17, 17,																	110, 111, 17, 17, 17, 17, 110, 111, 17, 17,																		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][32] = new long[] { 901*108+18, 901*108+18, 901*108+18, 18, 18, 901*108+18, 18, 18, 112, 113,													901*108+18, 901*108+18, 901*108+18, 18, 18, 901*108+18, 18, 18, 112, 113,									18, 18, 18, 18, 18, 18, 18, 18, 18, 18,													901*108+18, 901*108+18, 901*108+18, 18, 18, 901*108+18, 18, 18, 112, 113,									18, 18, 18, 18, 18, 18, 18, 18, 18, 18,																			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}
		for(int _i=0;_i<100;_i++) {Map[_i][33] = new long[] { 901*107+10, 901*107+10, 901*107+10, 901*108+10, 901*108+10, 901*107+10, 901*108+12, 901*108+13, 15, 16,					901*107+10, 901*107+10, 901*107+10, 901*108+10, 901*108+10, 901*107+10, 901*108+10, 901*108+10, 10, 10,		10, 10, 10, 10, 10, 901*108+10, 901*108+10, 901*108+10, 901*108+10, 901*108+10,			901*107+10, 901*107+10, 901*107+10, 901*108+10, 901*108+10, 901*107+10, 901*108+10, 901*108+10, 10, 10,		10, 10, 10, 10, 10, 10, 10, 12, 12, 10,																			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0}[_i];}

	}

}
