package CodeW;
public class Player {
	public static int PictureID;
	public static int xPos = 5;
	public static int yPos = 5;
	public static String direction = "right";
	public static int lastXPos = 0;
	public static int lastYPos = 0;
	public static int newLastXPos = 0;
	public static int newLastYPos = 0;
	public static int TileChangeWhileWalking = 0;
	public static int StepDuration = 200;			//time for one animated step in MS
	public static Player createPlayer(int _PictureID, int _xPos, int _yPos) {
		Player player = new Player();
		player.PictureID = _PictureID;	// Current Frame used for the animated Walrii. Depends on @MSEC and at walking/standing
		player.xPos = _xPos;				// x-Position on global map
		player.yPos = _yPos;				// y-Position on global map
		player.direction = direction;
		player.lastXPos = 0;
		player.lastYPos = 0;
		player.newLastXPos = 0;
		player.newLastYPos = 0;
		return player;
	}
	public static int getXPos() {
		return xPos;
	}
	public static void setTileChangeWhileWalking() {
		if ((System.currentTimeMillis()%StepDuration)>(StepDuration/2)) {
			Player.TileChangeWhileWalking = 1;
		} else {
			Player.TileChangeWhileWalking = 0;
		}
	}
	public static int getCurrentTile() {
		int[] _dir = getDirectionToINT();
		int[] tiles = {0, 3, 0, 0, 7, 0, 1, 0, 0, 5};
		return tiles[4*(_dir[0]+1)+_dir[1]+1];
	}
	public static int[] getDirectionToINT() {
		int[] dirINT = {0, 0};
		if(direction.equals("up")){
			dirINT[0] = 0;
			dirINT[1] = -1;
		}
		if(direction.equals("down")){
			dirINT[0] = 0;
			dirINT[1] = 1;
		}
		if(direction.equals("left")){
			dirINT[0] = -1;
			dirINT[1] = 0;
		}
		if(direction.equals("right")){
			dirINT[0] = 1;
			dirINT[1] = 0;
		}
		return dirINT;
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
	public static void turn(String _dir) {
		direction = _dir;
	}
	public static boolean checkPath(String _dir) {
		int _x = getXPos();
		int _y = getYPos();
		//System.out.println("checking position ("+_x+" | "+_y+")");
		switch(_dir) {
			case "left":
				_x = getXPos()-1;
				break;
			case "right":
				_x = getXPos()+1;
				break;
			case "up":
				_y = getYPos()-1;
				break;
			case "down":
				_y = getYPos()+1;
				break;
		}
		//System.out.println("     new position ("+_x+" | "+_y+")");
		//System.out.println("     block at pos ("+Map.getForegroundID(_x, _y)+" | "+Map.getBackgroundID(_x, _y)+")");
		boolean solid = Block.getSolidFromID(Map.getBackgroundID(_x, _y)) || Block.getSolidFromID(Map.getForegroundID(_x, _y));
		return solid;
	}
	public static void go(String _dir) {
		Player.setTileChangeWhileWalking();
		turn(_dir);
		if (!checkPath(_dir)) {
			move(_dir);
		}
	}
	public static void move(String _dir) {
		switch(_dir) {
			case "left":
				setXPos(General.getMin(General.getMax(getXPos()-1, 0), Map.getWidth()));
				break;
			case "right":
				setXPos(General.getMin(General.getMax(getXPos()+1, 0), Map.getWidth()));
				break;
			case "up":
				setYPos(General.getMin(General.getMax(getYPos()-1, 0), Map.getHeight()));
				break;
			case "down":
				setYPos(General.getMin(General.getMax(getYPos()+1, 0), Map.getHeight()));
				break;
		}
	}
	public static boolean isLastPosition(int _x, int _y) {
		if (_x==lastXPos && _y ==lastYPos ) {
			return true;
		} else {
			return false;
		}
	}
}
