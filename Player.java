package CodeW;
public class Player {
	public static int PictureID;
	public static int xPos = 5;
	public static int yPos = 5;
	public static Player createPlayer(int _PictureID, int _xPos, int _yPos) {
		Player player = new Player();
		player.PictureID = _PictureID;	// Current Frame used for the animated Walrii. Depends on @MSEC and at walking/standing
		player.xPos = _xPos;				// x-Position on global map
		player.yPos = _yPos;				// y-Position on global map
		return player;
	}
	public static int getXPos() {
		return xPos;
	}
	public static int getYPos() {
		return yPos;
	}
	public static void setXPos(int _x) {
		xPos = _x;
	}
	public static void setYPos(int _y) {
		yPos = _y;
	}
}
