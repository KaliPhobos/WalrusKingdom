package CodeW;
public class Player {
	public static int PictureID;
	public static int xPos = 5;
	public static int yPos = 5;
	public static int xPosToResume = 5;
	public static int yPosToResume = 5;
	public static String direction = "right";
	public static int lastXPos = 0;
	public static int lastYPos = 0;
	public static int newLastXPos = 0;
	public static int newLastYPos = 0;
	public static int TileChangeWhileWalking = 0;
	public static int StepDuration = 200;			//time for one animated step in MS
	public static long nextStep = 0;
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
		if (System.currentTimeMillis()>nextStep) {
			Player.TileChangeWhileWalking = Math.abs(Player.TileChangeWhileWalking-1);
			nextStep = System.currentTimeMillis()+StepDuration;
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
	public static int getYPos() {return yPos;}
	public static void setXPos(int _x) {xPos = _x;}
	public static void setXPosToResume(int _x) {xPosToResume = _x;}
	public static void setYPos(int _y) {yPos = _y;}
	public static void setYPosToResume(int _y) {yPosToResume = _y;}
	public static void turn(String _dir) {
		direction = _dir;
	}
	public static int[] facingPos() {
		int _x = getXPos();
		int _y = getYPos();
		switch(Player.getDirectionToINT()+"") {
		case "0":
			_x = getXPos()-1;
			break;
		case "1":
			_x = getXPos()+1;
			break;
		case "2":
			_y = getYPos()-1;
			break;
		case "3":
			_y = getYPos()+1;
			break;
		}
		int[] temp = {_x, _y};
		return temp;
	}
	public static boolean checkPath(String _dir) {
		int _x = getXPos();
		int _y = getYPos();
		//General.DebugLog("checking position ("+_x+" | "+_y+")");
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
		// add the -1 to prevent the walrus from triggering the out of border bug
		_x = General.getBetween(0, _x, Map.getWidth()-1);
		_y = General.getBetween(0, _y, Map.getHeight()-1);
		boolean solid = false;
		if (Map.ChangesPrecheckMap[General.getBetween(0, _y, Map.getHeight()-1)] && Map.ChangesMap[_x][_y]!=0) {		// Get isSolid from shanges map
			solid = Block.getSolidFromID(Map.ChangesMap[_x][_y]%901);
		} else {																										// Get isSolid from regular map
			solid = Block.getSolidFromID(Map.getBackgroundID(_x, _y)) || Block.getSolidFromID(Map.getForegroundID(_x, _y));
		}
		return solid;
	}
	public static void go(String _dir) {
		//long nextStep = System.currentTimeMillis()+StepDuration;				// Not in use
		Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration/2;
		Player.setTileChangeWhileWalking();
		turn(_dir);
		if (!checkPath(_dir)) {
			move(_dir);
		}
	}
	public static void move(String _dir) {
		switch(_dir) {
			case "left":
				setXPos(General.getBetween(0, getXPos()-1, Map.getWidth()-1));
				Screen.scrollX = -1*window.blocksize;							// will be multiplied with ZoomFactor later. Internally working with pixels in 1:1 zoom
				Screen.scrollX();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "right":
				setXPos(General.getBetween(0, getXPos()+1, Map.getWidth()-1));
				Screen.scrollX = window.blocksize;
				Screen.scrollX();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "up":
				setYPos(General.getBetween(0, getYPos()-1, Map.getHeight()-1));
				Screen.scrollY = -1*window.blocksize;
				Screen.scrollY();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "down":
				setYPos(General.getBetween(0, getYPos()+1, Map.getHeight()-1));
				Screen.scrollY = window.blocksize;
				Screen.scrollY();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
		}
	}
	public static void Interact() {
		int[] direction = getDirectionToINT();														// Coordinates of Tile in front of the player
		if (Map.ChangeMap(getXPos()+direction[0], getYPos()+direction[1])) {						// Hey, Map! Do your thing, you know...
			if (General.showInteractions) {
				if (Trigger.currentTrigger==0) {
					General.DebugLog("Trigger (id=" + Trigger.currentTrigger + ") interaction");	// Cut down tree
				} else {
					General.DebugLog("Trigger (id=" + Trigger.currentTrigger + ") interaction");	// actual trigger (statue, chest, door, ...)
				}
			}
		} else {
			if (General.showInteractions) {															// No changes made (no triggers nor trees)
				General.DebugLog("No triggers here");												// Maybe add little easter eggs later? ("You found... a colorful leaf on the ground!")
			}
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
