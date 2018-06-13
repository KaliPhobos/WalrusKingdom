package CodeW;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.JFrame;

public class Screen {
	public static long ScreenSizeIndicator = 0;
	public static boolean scrollLocked = false;
	public static int screenLeft;
	public static int screenTop;
	public static int scrollX = 0;	// pixels still left to scroll on X-axis (from this int to 0, so -12 will be 12px to the right)
	public static int scrollY = 0;	// pixels still left to scroll on Y-axis (from this int to 0, so -12 will be 12px down)
	public static boolean forceUpdateNextTime = false; // ment to be set to TRUE after Popup messages (so no fragments are left)
	public static TileSource tiles = new TileSource("/CodeW/assets/tiles.png", window.blocksize);
	public static int TilesDrawn = 0;	// Just for testing purposes
	public static void setSize(int _width, int _height) {
		// set size of screen in px (called by WINDOW)
		ScreenWidth = _width;
		ScreenHeight = _height;
		createScreen(_width, _height);
	}
	public static int PlayerTile = 6;	// the current tile (cuz it's animated)
	public static double[][] ScreenMatrix;		// visible part of map data is copied there
	public static double[][] ScreenMatrixOld;	// same as above, last frame (necessary for modified rendering)
	public static int ScreenWidth;
	public static int ScreenHeight;
	public static TileArea tileArea;
	public static Screen Screen = createScreen(ScreenWidth, ScreenHeight);
	public Screen(int _width, int _height) {
		ScreenMatrix = new double[_width][_height];
		ScreenMatrixOld = new double[_width][_height];
	}
	public static JFrame createWindow() {
		// Pretty obvious, it *creates window*
		JFrame frame = new JFrame("CodeW");
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(window.blocksize*ScreenWidth, window.blocksize*ScreenHeight);		// set window size
	    return frame;
	}
	public static TileArea createTileArea(JFrame frame) {
		// Create Tile area to draw in
		TileArea tileArea = new TileArea(window.blocksize*ScreenWidth, window.blocksize*ScreenHeight);
		frame.add("Center", tileArea);
		frame.pack();
	    frame.setVisible(true);
		return tileArea;
	}
	public static Screen createScreen(int _width, int _height) {
		// Create the screen itself
		Screen screen = new Screen(_width, _height);
		ScreenMatrix = new double[_width][_height];
		ScreenMatrixOld = new double[_width][_height];
		return screen;
	}
	public static void update() {
		// This updates the screen position (what part of the map is shown, is something outside the border, ...)
		screenTop = General.getBetween(0, Map.getHeight()-getHeight(), Player.getYPos()-Math.round(getHeight()/2));
		screenLeft = General.getBetween(0, Map.getWidth()-getWidth(), Player.getXPos()-Math.round(getWidth()/2));
		switch(Map.currentMapName) {
			case "City1":
				if (Player.getXPos()>24 && Player.getXPos()<49 && Player.getYPos()<31 && Player.getYPos()>12) {	// Inside the big garden --> only Y-axis scrolling enabled
					screenLeft = 25;
					scrollLocked = true;
				} else {
					scrollLocked = false;
				}
		}
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				setField(_x, _y, Map.get(screenLeft+_x, screenTop+_y));
				
			}
			if (Map.ChangesPrecheckMap[screenTop+_y]) {
				//General.DebugLog("Changes to map now visible, accessing MapChanges[]");
				for(int _x=0;_x<getWidth();_x++) {
					if (Map.ChangesMap[screenLeft+_x][screenTop+_y] !=0) {
						setField(_x, _y, Map.getChanges(screenLeft+_x, screenTop+_y));
					};
				}
			}
		}
	}
	public static void renderBackground(boolean ForceUpdate) {
		// Renders background layer *obviously*
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data = ScreenMatrix[_x][_y];				// load raw data
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true) {	// Player moved
					if ((screenLeft+_x>Player.getXPos()-2 && screenLeft+_x<Player.getXPos()+2 && screenTop+_y>Player.getYPos()-3 && screenTop+_y<Player.getYPos()+2)==false) {		// fix rendering bux with semi transparence
						int _background = Map.getBackgroundID(data);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
					}
				}
				if (screenLeft+_x>Player.getXPos()-2 && screenLeft+_x<Player.getXPos()+2 && screenTop+_y>Player.getYPos()-3 && screenTop+_y<Player.getYPos()+2) {	// prevent shadows when close to the edge on in ScrollLock regions
					int _background = Map.getBackgroundID(data);	// extract background data
					TilesDrawn++;
					TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
				}
			}
		}
	}
	public static void renderForeground(boolean ForceUpdate) {
		// Renders foreground layer *obviously*
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data = ScreenMatrix[_x][_y];				// load raw data
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true) {		// Update to map data or player moved
					if ((screenLeft+_x>Player.getXPos()-2 && screenLeft+_x<Player.getXPos()+2 && screenTop+_y>Player.getYPos()-3 && screenTop+_y<Player.getYPos()+2)==false) {		// fix rendering bux with semi transparence
						int _foreground = Map.getForegroundID(data);	// extrace foreground data
						if(_foreground>0) {
							TilesDrawn++;
							TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);
						}
					}
				}
				if (screenLeft+_x>=Player.getXPos()-2 && screenLeft+_x<Player.getXPos()+2 && screenTop+_y>Player.getYPos()-3 && screenTop+_y<Player.getYPos()+2) {	// prevent shadows when close to the edge on in ScrollLock regions
					int _foreground = Map.getForegroundID(data);	// extrace foreground data
					if(_foreground>0) {
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(Map.getForegroundID(data)), TileSource.getYPos(Map.getForegroundID(data)), window.blocksize*_x, window.blocksize*_y);
					}
				}
			}
		}
	}
	public static void render(boolean ForceUpdate) {
		// main class that does all the rendering
		General.updateZoomFactor();
		renderBackground(ForceUpdate);
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				if (scrollLocked==false) {
					if(General.getBetween(0, Player.getXPos()-Math.round(getWidth()/2), Map.getWidth()-getWidth())+_x == Player.getXPos() && General.getBetween(0, Player.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Player.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
						PlayerTile = Player.getCurrentTile()+Player.TileChangeWhileWalking;
						TilesDrawn++;
						int __y = Math.round(General.getMax((_y*window.blocksize)-Math.round(window.blocksize/4), 0));
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, __y);					// render char
						Player.newLastXPos = _x;
						Player.newLastYPos = _y;
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
						
					}
				} else {	//This disables the automated screenscrolling for special areas on the map (like in huge gardens) so only the y-axis scrolls. Similar to behavior close to map's edges
					if(Player.getXPos()-screenLeft==_x && General.getBetween(0, Player.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Player.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);
						PlayerTile = Player.getCurrentTile()+Player.TileChangeWhileWalking;
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, General.getMax(window.blocksize*_y-Math.round(getZoom()/4), 0));		// render char
						Player.newLastXPos = _x;
						Player.newLastYPos = _y;
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
					}
				}
			}
		}
		renderForeground(ForceUpdate);
		Trigger.trigger(Player.getXPos(), Player.getYPos());
		if(Trigger.get(Player.getXPos(), Player.getYPos())==0.0 && forceUpdateNextTime==true) {	// To prevent left over fragments from popups
			forceUpdateNextTime = false;
			render(true);
		}
		Player.lastXPos = Player.newLastXPos;
		Player.lastYPos = Player.newLastYPos;
		if (General.ShowTileUpdates) {
			General.DebugLog(TilesDrawn+" Tiles updated");
		}
		TilesDrawn = 0;
	}
	public static void UpdateOldData() {
		// Update the background data necessary for modified rendering algorithm
		for(int _y=0;_y<(int)window.height/24;_y++) {		
			for(int _x=0;_x<(int)window.width/24;_x++) {
				ScreenMatrixOld[_x][_y] = ScreenMatrix[_x][_y];
			}
		}
	}
	public static void setField(int _x, int _y, double i) {
		// To directly alter the screen's content without changing the map (making tiles flicker between different ones and stuff like that)
		ScreenMatrix[_x][_y] = i;		
	}
	public static void loadFont() {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/CodeW/assets/font.ttf")));
		} catch (Exception e) {
		     //Handle exception
			General.DebugLog("Exception in loading fonts");
		}
	}
	public static int getWidth() {
		// Return screen width in PX
		return ScreenWidth;
	}
	public static int getHeight() {
		// Return screen height in PX
		return ScreenHeight;
	}
	public static int getZoom() {
		int zoomX = (window.getWidth()/getWidth());
		int zoomY = ((window.getHeight())/getHeight());
		return General.getMin(zoomX,  zoomY);
	}
	public static int getZoomX() {
		return (window.getWidth()/getWidth());
	}
	public static void scrollX() {
		// not implemented yet
	}
	public static void scrollY() {
		// not implemented yet
	}
}
