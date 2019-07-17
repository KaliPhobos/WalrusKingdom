package CodeW;

public class MultiplayerUser {
	boolean isActive;
	boolean isNPC;
	String ip;
	int port;
	Player playerObject;
	String mapName;
	public MultiplayerUser(boolean isActive, boolean isNPC, String ip, int port, Player playerObject) {
		this.isActive = isActive;
		this.isNPC = isNPC;
		this.ip = ip;
		this.port = port;
		this.playerObject = playerObject;
	}
	public boolean isActive() {return this.isActive;}
	public boolean isNPC() {return this.isNPC;}
	public String getIP() {return this.ip;}
	public int getPort() {return this.port;}
	public int getXPos() {return this.playerObject.getXPos();}
	public int getYPos() {return this.playerObject.getYPos();}
	public int[] getDirectionToINT() {return this.playerObject.getDirectionToINT();}
	public String getDirection() {return this.playerObject.getDirection();}
	public int getCurrentTile() {return this.playerObject.getCurrentTile();}
	public String getCurrentMapName() {return this.playerObject.getCurrentMapName();}
	public void setNewLastXPos(int _x) {this.playerObject.setNewLastXPos(_x);}
	public void setNewLastYPos(int _y) {this.playerObject.setNewLastYPos(_y);}

	public static MultiplayerUser createDemoMonkey() {
		MultiplayerUser DemoMonkey = new MultiplayerUser(true, true, null, 0, Player.createPlayer(5,  16, 37, "City1"));
		return DemoMonkey;
	}
}
