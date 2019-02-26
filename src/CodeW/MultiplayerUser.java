package CodeW;

public class MultiplayerUser {
	boolean isOnline;
	boolean isNPC;
	String ip;
	int port;
	Player playerObject;
	String mapName;
	public MultiplayerUser(boolean isOnline, boolean isNPC, String ip, int port, Player playerObject, String mapName) {
		this.isOnline = isOnline;
		this.isNPC = isNPC;
		this.ip = ip;
		this.port = port;
		this.playerObject = playerObject;
		this.mapName = mapName;
	}
	public boolean isOnline() {return this.isOnline;}
	public boolean isNPC() {return this.isNPC;}
	public String getIP() {return this.ip;}
	public int getPort() {return this.port;}
	public int getXPos() {return this.playerObject.getXPos();}
	public int getYPos() {return this.playerObject.getYPos();}
	public int[] getDirectionToINT() {return this.playerObject.getDirectionToINT();}
	public String getDirection() {return this.playerObject.getDirection();}
	public int getCurrentTile() {return this.playerObject.getCurrentTile();}
	public int getCurrentMapName() {return this.port;}
	public String getMapName() {return this.mapName;}
}
