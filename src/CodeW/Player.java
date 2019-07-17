package CodeW;
public class Player {
	public int PictureID;
	public int xPos = 5;
	public int yPos = 5;
	public int xPosToResume = 5;
	public int yPosToResume = 5;
	public String direction = "right";
	public int lastXPos = 0;
	public int lastYPos = 0;
	public int newLastXPos = 0;
	public int newLastYPos = 0;
	public int TileChangeWhileWalking = 0;
	public static int StepDuration = 200;			//time for one animated step in MS
	public long nextStep = 0;
	public String currentMapName = "initial";
	
	public static Player createPlayer(int pictureID, int xPos, int yPos, String currentMapName) {
		return new Player(pictureID, xPos, yPos, "", xPos, yPos, xPos, yPos, currentMapName);
	}
	public Player(int pictureID, int xPos, int yPos, String direction, int lastXPos, int lastYPos, int newLastXPos, int newLastYPos, String currentMapName) {
		//this.PictureID = _PictureID;	// Current Frame used for the animated Walrii. Depends on @MSEC and at walking/standing
		this.xPos = xPos;				// x-Position on global map
		this.yPos = yPos;				// y-Position on global map
		this.direction = direction;
		this.lastXPos = lastXPos;
		this.lastYPos = lastYPos;
		this.newLastXPos = newLastXPos;
		this.newLastYPos = newLastYPos;
		this.currentMapName = currentMapName;
	}
	public void setTileChangeWhileWalking() {
		if (System.currentTimeMillis()>nextStep) {
			this.TileChangeWhileWalking = Math.abs(this.TileChangeWhileWalking-1);
			nextStep = System.currentTimeMillis()+StepDuration;
		}
	}
	public String getDirection() {return this.direction;}
	public int getCurrentTile() {
		int[] _dir = this.getDirectionToINT();
		int[] tiles = {0, 3, 0, 0, 7, 0, 1, 0, 0, 5};								// Still ignoring the "pictureID setting - change this later to implement different characters
		return tiles[4*(_dir[0]+1)+_dir[1]+1];
	}
	public String getCurrentMapName() {return this.currentMapName;}
	public void setCurrentMapName(String name) {this.currentMapName = name;}
	public int[] getDirectionToINT() {
		int[] dirINT = {0, 0};
		if(this.direction.equals("up")){
			dirINT[0] = 0;
			dirINT[1] = -1;
		}
		if(this.direction.equals("down")){
			dirINT[0] = 0;
			dirINT[1] = 1;
		}
		if(this.direction.equals("left")){
			dirINT[0] = -1;
			dirINT[1] = 0;
		}
		if(this.direction.equals("right")){
			dirINT[0] = 1;
			dirINT[1] = 0;
		}
		return dirINT;
	}
	public int getXPos() {return this.xPos;}
	public int getYPos() {return this.yPos;}
	public void setXPos(int _x) {this.xPos = _x;}
	public void setYPos(int _y) {this.yPos = _y;}
	public void setXPosToResume(int _x) {this.xPosToResume = _x;}
	public void setYPosToResume(int _y) {this.yPosToResume = _y;}
	public void setLastXPos(int _x) {this.lastXPos = _x;}
	public void setLastYPos(int _y) {this.lastXPos = _y;}
	public void setNewLastXPos(int _x) {this.newLastXPos = _x;}
	public void setNewLastYPos(int _y) {this.newLastXPos = _y;}
	public void turn(String _dir) {
		this.direction = _dir;
	}
	public int[] facingPos() {
		int _x = this.getXPos();
		int _y = this.getYPos();
		switch(this.getDirectionToINT()+"") {
		case "0":
			_x = this.getXPos()-1;
			break;
		case "1":
			_x = this.getXPos()+1;
			break;
		case "2":
			_y = this.getYPos()-1;
			break;
		case "3":
			_y = this.getYPos()+1;
			break;
		}
		int[] temp = {_x, _y};
		return temp;
	}
	public boolean checkPath(String _dir) {
		int _x = this.getXPos();
		int _y = this.getYPos();
		//General.DebugLog("checking position ("+_x+" | "+_y+")");
		switch(_dir) {
			case "left":
				_x = this.getXPos()-1;
				break;
			case "right":
				_x = this.getXPos()+1;
				break;
			case "up":
				_y = this.getYPos()-1;
				break;
			case "down":
				_y = this.getYPos()+1;
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
	public void go(String _dir) {
		//long nextStep = System.currentTimeMillis()+StepDuration;				// Not in use
		Menu.KeyPause = System.currentTimeMillis()+StepDuration/2;		// DELAY
		this.setTileChangeWhileWalking();
		turn(_dir);
		if (!checkPath(_dir)) {
			this.move(_dir);
		}
	}
	public void move(String _dir) {
		switch(_dir) {
			case "left":
				setXPos(General.getBetween(0, this.getXPos()-1, Map.getWidth()-1));
				Screen.scrollX = -1*window.blocksize;							// will be multiplied with ZoomFactor later. Internally working with pixels in 1:1 zoom
				Screen.scrollX();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "right":
				setXPos(General.getBetween(0, this.getXPos()+1, Map.getWidth()-1));
				Screen.scrollX = window.blocksize;
				Screen.scrollX();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "up":
				setYPos(General.getBetween(0, this.getYPos()-1, Map.getHeight()-1));
				Screen.scrollY = -1*window.blocksize;
				Screen.scrollY();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
			case "down":
				setYPos(General.getBetween(0, this.getYPos()+1, Map.getHeight()-1));
				Screen.scrollY = window.blocksize;
				Screen.scrollY();	// do the smooth scrolling animation
				//Trigger.trigger(getXPos(), getYPos());
				break;
		}
	}
	public void Interact() {
		int[] direction = this.getDirectionToINT();														// Coordinates of Tile in front of the player
		if (Map.ChangeMap(this.getXPos()+direction[0], this.getYPos()+direction[1])) {						// Hey, Map! Do your thing, you know...
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
	public boolean isLastPosition(int _x, int _y) {
		if (_x==this.lastXPos && _y ==this.lastYPos ) {
			return true;
		} else {
			return false;
		}
	}
}
