package CodeW;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import CodeW.TileArea;


public class window {
	public static String GameStat = "Menu";				// Tells where in the program we are (Game, Menu, Paused, ...) Used for different key inputs
	public static int width = 576*1 +816*0 +408*0;
	public static int height = 384*1 +504*0 +288*0;
	public static final int blocksize = 24;				// size of tiles used in the game (24px)
	public static JFrame window;						// Never change this setting except for HD texture packs
	public static BufferStrategy buffer;
	public static long LaunchTimestamp;
	public static void main(String[] args) {
		LaunchTimestamp = System.currentTimeMillis();
		General.DebugLog("Run!");
		Screen.loadFont();
		Map.loadIntro1();
		GameStat = "Menu";			// just to prevent the bug
		LoadBlocks();																// Loading background block data
		prepare();
		General.preloadImages();
		//Menu.RunMenu();			// Call Start Menu								// Deactivate to skip intro
		Menu.oldWidth = width;		// Backup those values to fall back to after intro
		Menu.oldHeight = height;
		Intro.loadMainGame();
	}
	public static void prepare() {
		Screen.setSize(Math.round(width/blocksize), Math.round(height/blocksize));	// Setup Screen (results in 0-11 x 0-17)
		window = Screen.createWindow();												// Create window object (JFrame)
		new Keys(window);			// Keys keys = new Keys(window) <--- never used variable "keys"
		window.pack();
		window.setVisible(true);
		Screen.ScreenSizeIndicator = window.getWidth()* window.getHeight();
		TileArea tileArea = Screen.createTileArea(window);
	}
	public static void resize (int _width, int _height) {
		_width = General.getBetween(17*blocksize, _width, 48*blocksize);			// limit to width and height
		_height = General.getBetween(12*blocksize, _height, 32*blocksize);			// have fun on 4k (just use the zoom option)
		_width = _width - _width%(blocksize*2)+blocksize*0;
		_height = _height - _height%(blocksize*2)+blocksize;
		window.dispose();
		width = _width;
		height = _height;
		prepare();
	}
	
	public static void Start() {
		Player.direction = "right";
		GameStat = "Game";
		General.DebugLog("window.start");
		//Player Walrii = Player.createPlayer(0, 10, 10);	// Define Player ONLY FOR LOCAL MULTIPLAYER
		Map.loadIntro1();
		Screen.update();
		Screen.render(true);
		Intro.RunIntro();
		resize(Menu.oldWidth, Menu.oldWidth);
		Resume();
	}
	
	
	public static void Resume() {
		resize(Menu.oldWidth, Menu.oldHeight);
		GameStat = "Game";
		General.DebugLog("window.resume");
		if (Map.MapToResume=="City1") {Map.loadCity1();}
		Player.xPos = Player.xPosToResume;			// resume with old position on the map
		Player.yPos = Player.yPosToResume;			// Note to self: add player walking direction  but change to RICHT when going to the menu
		Screen.update();
	   	Screen.render(true);
		while (true) {								// MAIN GAME LOOP
		   	Screen.update();
		   	Screen.render(false);
		   	Screen.UpdateOldData();
		   	Keys.checkInput();
		   	window.repaint();
		   	General.sleep(70);
		}
	}
	public static int getWidth() {
		return window.getWidth();
	}
	public static int getHeight() {
		return window.getHeight()-38+16;			// the window.getHeight get the height of the rendered window PLUS the window's bar. Add -38(px) to fix it
	}												// Aaaaand added 16 again to fix resizing bug (no more bottom border)
	public static void LoadBlocks() {
		Block.AddNew(0, "void", false, 0, 1);		// no walrus shall ever go there
		Block.AddNew(1, "walrus", false, 1, 2);		// a walrus
		Block.AddNew(2, "walrus", true, 2, 2);
		Block.AddNew(3, "walrus", false, 3, 2);
		Block.AddNew(4, "walrus", false, 4, 2);
		Block.AddNew(5, "walrus", false, 5, 2);
		Block.AddNew(6, "walrus", false, 6, 2);
		Block.AddNew(7, "walrus", false, 7, 2);
		Block.AddNew(8, "walrus", false, 8, 2);
		Block.AddNew(9, "void SOLID", true, 9, 3);	// used for menu
		Block.AddNew(10, "grass", false, 10, 4);	// nice grass
		Block.AddNew(11, "flower", false, 11, 5);	// nice flower
		Block.AddNew(12, "flower", false, 12, 5);
		Block.AddNew(13, "flower", false, 13, 5);
		Block.AddNew(14, "flower", false, 14, 5);
		Block.AddNew(15, "street", false, 15, 6);	// a street
		Block.AddNew(16, "street", false, 16, 6);
		Block.AddNew(17, "street", false, 17, 6);
		Block.AddNew(18, "street", false, 18, 6);
		Block.AddNew(19, "street", false, 19, 6);
		Block.AddNew(20, "house", false, 20, 7);	// a house
		Block.AddNew(21, "house", false, 21, 7);
		Block.AddNew(22, "house", false, 22, 7);
		Block.AddNew(23, "house", false, 23, 7);
		Block.AddNew(24, "house", false, 24, 7);
		Block.AddNew(25, "house", false, 25, 7);
		Block.AddNew(26, "house", true, 26, 7);
		Block.AddNew(27, "house", true, 27, 7);
		Block.AddNew(28, "house", true, 28, 7);
		Block.AddNew(29, "house", true, 29, 7);
		Block.AddNew(30, "house", true, 30, 7);
		Block.AddNew(31, "house", true, 31, 7);
		Block.AddNew(32, "house", true, 32, 7);
		Block.AddNew(33, "house", true, 33, 7);
		Block.AddNew(34, "house", true, 34, 7);
		Block.AddNew(35, "house", true, 35, 7);
		Block.AddNew(36, "house", true, 36, 7);
		Block.AddNew(37, "house", true, 37, 7);
		Block.AddNew(38, "house", true, 38, 7);
		Block.AddNew(39, "house", true, 39, 8);		// DOOR
		Block.AddNew(40, "house", true, 40, 8);		// DOOR
		Block.AddNew(41, "house", true, 41, 7);
		Block.AddNew(42, "house", true, 42, 7);
		Block.AddNew(43, "house", true, 43, 7);
		Block.AddNew(44, "house", true, 44, 7);
		Block.AddNew(45, "house", true, 45, 7);
		Block.AddNew(46, "statue", false, 46, 9);	// statue
		Block.AddNew(47, "statue", false, 47, 9);
		Block.AddNew(48, "statue", true, 48, 9);
		Block.AddNew(49, "statue", true, 49, 9);
		Block.AddNew(50, "house", false, 50, 7);
		Block.AddNew(51, "house", false, 51, 7);
		Block.AddNew(52, "house", false, 52, 7);
		Block.AddNew(53, "house", false, 53, 7);
		Block.AddNew(54, "house", false, 54, 7);
		Block.AddNew(55, "house", false, 55, 7);
		Block.AddNew(56, "house", true, 56, 7);
		Block.AddNew(57, "house", true, 57, 7);
		Block.AddNew(58, "house", true, 58, 7);
		Block.AddNew(59, "house", true, 59, 7);
		Block.AddNew(60, "house", true, 60, 7);
		Block.AddNew(61, "house", true, 61, 7);
		Block.AddNew(62, "house", true, 62, 7);
		Block.AddNew(63, "house", true, 63, 7);
		Block.AddNew(64, "house", true, 64, 7);
		Block.AddNew(65, "house", true, 65, 7);
		Block.AddNew(66, "house", true, 66, 7);
		Block.AddNew(67, "house", true, 67, 7);
		Block.AddNew(68, "house", true, 68, 7);
		Block.AddNew(69, "house", true, 69, 8);		// DOOR
		Block.AddNew(70, "house", true, 70, 8);		//DOOR
		Block.AddNew(71, "house", true, 71, 7);
		Block.AddNew(72, "house", true, 72, 7);
		Block.AddNew(73, "house", true, 73, 7);
		Block.AddNew(74, "house", true, 74, 7);
		Block.AddNew(75, "house", true, 75, 7);
		Block.AddNew(76, "sign", false, 76, 10);	// arrow signs NOT solid. Only text-signs are
		Block.AddNew(77, "sign", false, 77, 10);
		Block.AddNew(78, "sign", true, 78, 10);
		Block.AddNew(79, "sign", true, 79, 10);
		Block.AddNew(80, "house", false, 80, 7);
		Block.AddNew(81, "house", false, 81, 7);
		Block.AddNew(82, "house", false, 82, 7);
		Block.AddNew(83, "house", false, 83, 7);
		Block.AddNew(84, "house", true, 84, 7);
		Block.AddNew(85, "house", true, 85, 7);
		Block.AddNew(86, "house", true, 86, 7);
		Block.AddNew(87, "house", true, 87, 7);
		Block.AddNew(88, "house", true, 88, 7);
		Block.AddNew(89, "house", true, 89, 7);
		Block.AddNew(90, "house", true, 90, 7);
		Block.AddNew(91, "house", true, 91, 7);
		Block.AddNew(92, "house", true, 92, 7);
		Block.AddNew(93, "house", true, 93, 7);
		Block.AddNew(94, "house", true, 94, 7);
		Block.AddNew(95, "house", true, 95, 7);
		Block.AddNew(96, "house", true, 96, 7);
		Block.AddNew(97, "house", true, 97, 7);
		Block.AddNew(98, "house", true, 98, 7);
		Block.AddNew(99, "house", true, 99, 8);		// DOOR
		Block.AddNew(100, "house", true, 100, 8);	// DOOR
		Block.AddNew(101, "house", true, 101, 7);
		Block.AddNew(102, "house", true, 102, 7);
		Block.AddNew(103, "house", true, 103, 7);
		Block.AddNew(104, "house", true, 104, 7);
		Block.AddNew(105, "house", true, 105, 7);
		Block.AddNew(106, "tree", true, 106, 11);	// TREE
		Block.AddNew(107, "tree", true, 107, 11);	// TREE
		Block.AddNew(108, "tree", false, 108, 12);	// BEHIND TREE
		Block.AddNew(109, "tree", true, 109, 11);	// TREE					// #######################
		Block.AddNew(110, "street", false, 110, 6);
		Block.AddNew(111, "street", false, 111, 6);
		Block.AddNew(112, "street", false, 112, 6);
		Block.AddNew(113, "street", false, 113, 6);
		Block.AddNew(114, "statue", true, 114, 9);	// statue
		Block.AddNew(115, "statue", true, 115, 0);	// statue
		Block.AddNew(116, "tree", true, 116, 11);	// TREE
		Block.AddNew(117, "tree", true, 117, 11);	// TREE
		Block.AddNew(118, "tree", false, 118, 12);	// BEHIND TREE
		Block.AddNew(119, "tree", true, 119, 11);	// TREE
		Block.AddNew(120, "NotInUse", false, 120, 0);
		Block.AddNew(121, "NotInUse", false, 121, 0);
		Block.AddNew(122, "NotInUse", false, 122, 0);
		Block.AddNew(123, "NotInUse", false, 123, 0);
		Block.AddNew(124, "NotInUse", false, 124, 0);
		Block.AddNew(125, "NotInUse", false, 125, 0);
		Block.AddNew(126, "tree", true, 126, 11);	// TREE
		Block.AddNew(127, "tree", true, 127, 11);	// TREE
		Block.AddNew(128, "NotInUse", false, 128, 0);
		Block.AddNew(129, "NotInUse", false, 129, 0);
		Block.AddNew(130, "street", false, 130, 6);
		Block.AddNew(131, "street", false, 131, 6);
		Block.AddNew(132, "street", false, 132, 6);
		Block.AddNew(133, "street", false, 133, 6);
		Block.AddNew(134, "house", true, 134, 7);
		Block.AddNew(135, "house", true, 135, 7);
		Block.AddNew(136, "house", true, 136, 8);	// A DOOR
		Block.AddNew(137, "house", true, 137, 8);	// A DOOR
		Block.AddNew(138, "house", true, 138, 7);
		Block.AddNew(139, "house", true, 139, 7);
		Block.AddNew(140, "house", true, 140, 7);
		Block.AddNew(141, "house", true, 141, 7);
		Block.AddNew(142, "street", false, 142, 6);	// A sign should stand on this
		Block.AddNew(143, "house", true, 143, 7);
		Block.AddNew(144, "house", true, 144, 7);
		Block.AddNew(145, "house", true, 145, 7);
		Block.AddNew(146, "house", true, 146, 7);
		Block.AddNew(147, "house", true, 147, 7);
		Block.AddNew(148, "house", true, 148, 7);
		Block.AddNew(149, "house", true, 149, 7);
		Block.AddNew(150, "NotInUse", false, 150, 0);
		Block.AddNew(151, "NotInUse", false, 151, 0);
		Block.AddNew(152, "NotInUse", false, 152, 0);
		Block.AddNew(153, "house", true, 153, 7);
		Block.AddNew(154, "house", true, 154, 7);
		Block.AddNew(155, "house", true, 155, 7);
		Block.AddNew(156, "house", true, 156, 7);
		Block.AddNew(157, "house", true, 157, 7);
		Block.AddNew(158, "house", true, 158, 7);
		Block.AddNew(159, "house", true, 159, 7);
		Block.AddNew(160, "house", false, 160, 0);
		Block.AddNew(161, "house", false, 161, 0);
		Block.AddNew(162, "NotInUse", false, 162, 0);
		Block.AddNew(163, "house", true, 163, 7);
		Block.AddNew(164, "house", false, 164, 7);
		Block.AddNew(165, "house", false, 165, 7);
		Block.AddNew(166, "house", false, 166, 7);
		Block.AddNew(167, "house", false, 167, 7);
		Block.AddNew(168, "house", false, 168, 7);
		Block.AddNew(169, "house", false, 169, 7);	// ############################# end
		Block.AddNew(170, "walrus", false, 170, 2);
		Block.AddNew(171, "walrus", false, 171, 2);
		Block.AddNew(172, "walrus", false, 172, 2);
		Block.AddNew(173, "walrus", false, 173, 2);
		Block.AddNew(174, "walrus", false, 174, 2);
		Block.AddNew(175, "walrus", false, 175, 2);
		Block.AddNew(176, "walrus", false, 176, 2);
		Block.AddNew(177, "walrus", false, 177, 2);
		Block.AddNew(178, "walrus", false, 178, 2);
		Block.AddNew(179, "walrus", false, 179, 2);
		Block.AddNew(180, "walrus", false, 180, 2);
		Block.AddNew(181, "walrus", false, 181, 2);
		Block.AddNew(182, "walrus", false, 182, 2);
		Block.AddNew(183, "walrus", false, 183, 2);
		Block.AddNew(184, "walrus", false, 184, 2);
		Block.AddNew(185, "walrus", false, 185, 2);
		Block.AddNew(186, "walrus", false, 186, 2);
		Block.AddNew(187, "walrus", false, 187, 2);
		Block.AddNew(188, "walrus", false, 188, 2);
		Block.AddNew(189, "walrus", false, 189, 2);
		Block.AddNew(190, "Stone", true, 190, 2);	// Stone table
		Block.AddNew(191, "-", false, 191, 2);
		Block.AddNew(192, "-", false, 192, 2);
		Block.AddNew(193, "-", false, 193, 2);
		Block.AddNew(194, "-", false, 194, 2);
		Block.AddNew(195, "-", false, 195, 2);
		Block.AddNew(196, "-", false, 196, 2);
		Block.AddNew(197, "library", true, 197, 7);
		Block.AddNew(198, "library", true, 198, 7);
		Block.AddNew(199, "library", true, 199, 7);
		Block.AddNew(200, "library", true, 200, 7);
		Block.AddNew(201, "library", true, 201, 7);
		Block.AddNew(202, "library", true, 202, 7);
		Block.AddNew(203, "library", true, 203, 7);
		Block.AddNew(204, "library", true, 204, 7);
		Block.AddNew(205, "library", true, 205, 7);
		Block.AddNew(206, "library", true, 206, 7);
		Block.AddNew(207, "library", true, 207, 7);
		Block.AddNew(208, "library", true, 208, 7);
		Block.AddNew(209, "library", true, 209, 7);
		Block.AddNew(210, "-", false, 199, 2);
		Block.AddNew(211, "-", false, 199, 2);
		Block.AddNew(212, "library", true, 212, 7);
		Block.AddNew(213, "library", true, 213, 7);
		Block.AddNew(214, "library", true, 214, 7);
		Block.AddNew(215, "library", true, 215, 7);
		Block.AddNew(216, "library", true, 216, 7);
		Block.AddNew(217, "library", true, 217, 7);
		Block.AddNew(218, "library", true, 218, 7);
		Block.AddNew(219, "library", true, 219, 7);
		Block.AddNew(220, "-", false, 220, 2);
		Block.AddNew(221, "-", false, 221, 2);
		Block.AddNew(222, "-", false, 222, 2);
		Block.AddNew(223, "-", false, 223, 2);
		Block.AddNew(224, "library", true, 224, 2);
		Block.AddNew(225, "library", true, 225, 2);
		Block.AddNew(226, "library", true, 226, 2);
		Block.AddNew(227, "library", true, 227, 2);
		Block.AddNew(228, "library", true, 228, 2);
		Block.AddNew(229, "library", true, 229, 2);

	}
}