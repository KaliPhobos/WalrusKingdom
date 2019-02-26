package CodeW;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.JFrame;

public class Screen {
	static Player Walrii = window.getPlayerObject();

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
	public static double[][] ScreenMatrixOverlay;	// Used to add changes to the map, like paths through the forest that take different turns depending on player position
	public static boolean useScreenMatrixOverlay = false;	//see above
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
		JFrame frame = new JFrame("CodeW "+getWidth()+"x"+getHeight());
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
		ScreenMatrix = new double[_width][_height];				// current screen content
		ScreenMatrixOld = new double[_width][_height];			// to compare to last frames - render changes
		ScreenMatrixOverlay = new double[_width][_height];		// used only in special area - map manipulations
		return screen;
	}
	public static void update() {
		// This updates the screen position (what part of the map is shown, is something outside the border, ...)
		// top left tile shown on screen
		screenTop = General.getBetween(0, Map.getHeight()-getHeight(), Walrii.getYPos()-Math.round(getHeight()/2));
		screenLeft = General.getBetween(0, Map.getWidth()-getWidth(), Walrii.getXPos()-Math.round(getWidth()/2));
		switch(Walrii.getCurrentMapName()) {
			case "City1":
				if (Walrii.getXPos()>24 && Walrii.getXPos()<49 && Walrii.getYPos()<31 && Walrii.getYPos()>12) {	// Inside the big garden --> only Y-axis scrolling enabled
					// PALAST GARDEN
					if(getWidth()<=24) {		// Window is the physical window border, Screen is the ingame area shown
						screenLeft = General.getBetween(25, Walrii.getXPos()-Math.round(getWidth()/2), 49-getWidth());		//17 tiles is the min width, 49 the x pos
					} else {
						screenLeft = 37-(getWidth()-getWidth()%2)/2;	// centered entrence x-coord is 37
					}
					scrollLocked = true;
				} else if ( (Walrii.getYPos()<43) && (Walrii.getYPos()+(getHeight()/2-(getHeight()+1)%2)>47) && (Walrii.getXPos()+(getWidth()/2)>55) && (Walrii.getXPos()-(getWidth()/2)<57) ) {	// The -(getHeight()+1)%2) makes it work on both odd and even screen heights
					// FAKE FOREST PATH, first tile after turn south already visible, Character not on the forest path (north of it)
					// hide the *secret* forest level, no one shall see it <3
					
					
					// Note to self: little bug on screen with width of 31 tiles - the last tile won't trigger overlay. check formulas for borders
					
					if (General.showTrigger) {
						General.DebugLog("Matrix overlay active! *it's magic* --> Secret forest path takes a fake turn south");
					}
					ScreenMatrixOverlay = General.wipedMatrix(ScreenMatrixOverlay);				// THIS IS UGLY --> but at least it works
					// path after turn south is x=56 and x=57
					
					if ( ((Walrii.getXPos()+getWidth()/2)>55) && ((Walrii.getXPos()-getWidth()/2)<57) ) {
						int x = 56-Walrii.getXPos()+(getWidth()/2);
						for(int i=Walrii.getYPos()+((getHeight())/2);i>47;i--) {
							int y = i-Walrii.getYPos()+(getHeight()/2);
							if (General.showTrigger) General.DebugLog("x="+x+", y="+y+", max="+getWidth());
							ScreenMatrixOverlay[General.getMin(getWidth()-1, x)][General.getMin(getHeight()-1, y)] = 15;
							//ScreenMatrixOld[General.getMin(getWidth()-1, x+1)][y] = 0;
							ScreenMatrixOld[General.getMax(0, x-1)][General.getMin(getHeight()-1, y)] = 0;
						}
					}
					// Right side of path, x=57
					if ( ((Walrii.getXPos()+getWidth()/2)>57) && ((Walrii.getXPos()-getWidth()/2)<58) ) {
						int x = 57-Walrii.getXPos()+getWidth()/2;
						for(int i=Walrii.getYPos()+getHeight()/2;i>47;i--) {
							int y = i-Walrii.getYPos()+(getHeight())/2;
							ScreenMatrixOverlay[General.getMin(getWidth()-1, x)][General.getMin(getHeight()-1, y)] = 16;
							ScreenMatrixOld[General.getMin(getWidth()-1, x+1)][General.getMin(getHeight()-1, y)] = 0;
							//ScreenMatrixOld[General.getMax(0, x-1)][y] = 0;
						}
					}
					useScreenMatrixOverlay = true;		// ScreenMatrixOverlay[] will be used as source for (double)data instead of the actual map where ever it is not 0.0
					scrollLocked = true;
				} else if (Walrii.getYPos()>45 && Walrii.getYPos()<49 && Walrii.getXPos()+getWidth()/2>55 && Walrii.getXPos()<55) {
					// On the forest path
					// ScreenMatrixOverlay[56+((getWidth()-1)%2/2)-Walrii.getXPos()][45+((getHeight()-1)%2/2)-Walrii.getYPos()] = 0;	// one tile to the left
					
					
					
					// redo formulas for y-coords
					ScreenMatrixOld[toOnScreenXCoord(55)][toOnScreenYCoord(45)] = 0;
					ScreenMatrixOld[toOnScreenXCoord(55)][toOnScreenYCoord(46)] = 0;
					ScreenMatrixOld[toOnScreenXCoord(55)][toOnScreenYCoord(47)] = 0;
					ScreenMatrixOld[toOnScreenXCoord(55)][toOnScreenYCoord(48)] = 0;
					for (int x=toOnScreenXCoord(56);x<getWidth();x++) {
						ScreenMatrixOverlay[x][toOnScreenYCoord(45)] = 109;
						ScreenMatrixOverlay[x][toOnScreenYCoord(46)] = 17;
						ScreenMatrixOverlay[x][toOnScreenYCoord(47)] = 18;
						ScreenMatrixOverlay[x][toOnScreenYCoord(48)] = 106328;
						ScreenMatrixOld[x][toOnScreenYCoord(44)] = 0;			// No artefacts when moving up and down
						ScreenMatrixOld[x][toOnScreenYCoord(45)] = 0;
						ScreenMatrixOld[x][toOnScreenYCoord(46)] = 0;
						ScreenMatrixOld[x][toOnScreenYCoord(47)] = 0;
						ScreenMatrixOld[x][toOnScreenYCoord(48)] = 0;
						ScreenMatrixOld[x][toOnScreenYCoord(49)] = 0;
					}
					useScreenMatrixOverlay = true;
					
					
					
					
				} else {
					// boooring
					scrollLocked = false;
					useScreenMatrixOverlay = false;
				}
				break;
			case "ForestHouse":
				// General.DebugLog("FOREST");
				break;
			default:
				General.DebugLog("Screen: Unknown map");
				break;
		}
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				setField(_x, _y, Map.get(screenLeft+_x, screenTop+_y));
				// Copies map content to Screen Map
			}
			if (Map.ChangesPrecheckMap[screenTop+_y]) {
				if (General.showMapChanges) {
					General.DebugLog("Changes to map now visible, accessing MapChanges[]");
				}
				for(int _x=0;_x<getWidth();_x++) {
					if (Map.ChangesMap[screenLeft+_x][screenTop+_y] !=0) {
						setField(_x, _y, Map.getChanges(screenLeft+_x, screenTop+_y));
						// Applies changes to Screen Map
					};
				}
			}
		}
	}
	public static void clearScreenMatrixOverlay() {
		ScreenMatrixOverlay = General.wipedMatrix(ScreenMatrixOverlay);
		if (General.showMapChanges) { General.DebugLog("ScreenMatrixOverlay wiped"); }
	}
	public static int toOnScreenXCoord(int in) {
		return in-Walrii.getXPos()+(getWidth()/2);
	}
	public static int toOnScreenYCoord(int in) {
		return in-Walrii.getYPos()+(getHeight()/2);
	}
	public static void renderBackground(boolean ForceUpdate) {
		// Renders background layer *obviously*
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data;
				if(useScreenMatrixOverlay==true && ScreenMatrixOverlay[_x][_y]!=0) {		// BROKEN
					data = ScreenMatrixOverlay[_x][_y];			// changes
				} else {
					data = ScreenMatrix[_x][_y];				// no changes
				}
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld || ForceUpdate) {	// Player moved
					if ((screenLeft+_x>Walrii.getXPos()-2 && screenLeft+_x<Walrii.getXPos()+2 && screenTop+_y>Walrii.getYPos()-3 && screenTop+_y<Walrii.getYPos()+2)==false) {		// fix rendering bux with semi transparence
						int _background = Map.getBackgroundID(data);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
					}
				}
				if (screenLeft+_x>Walrii.getXPos()-2 && screenLeft+_x<Walrii.getXPos()+2 && screenTop+_y>Walrii.getYPos()-3 && screenTop+_y<Walrii.getYPos()+2) {	// prevent shadows when close to the edge on in ScrollLock regions
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
			for(int _x=0;_x<getWidth();_x++) {					// load raw data
				double data;
				if(useScreenMatrixOverlay==true && ScreenMatrixOverlay[_x][_y]>0) {		// BROKEN
					data = ScreenMatrixOverlay[_x][_y];			// changes
				} else {
					data = ScreenMatrix[_x][_y];				// no changes
				}
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true) {		// Update to map data or player moved
					if ((screenLeft+_x>Walrii.getXPos()-2 && screenLeft+_x<Walrii.getXPos()+2 && screenTop+_y>Walrii.getYPos()-3 && screenTop+_y<Walrii.getYPos()+2)==false) {		// fix rendering bux with semi transparence
						int _foreground = Map.getForegroundID(data);	// extrace foreground data
						if(_foreground>0) {
							TilesDrawn++;
							TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);
						}
					}
				}
				if (screenLeft+_x>=Walrii.getXPos()-2 && screenLeft+_x<Walrii.getXPos()+2 && screenTop+_y>Walrii.getYPos()-3 && screenTop+_y<Walrii.getYPos()+2) {	// prevent shadows when close to the edge on in ScrollLock regions
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
		ForceUpdate = (ForceUpdate || forceUpdateNextTime);				// why is this necessary? Do I already do this elsewhere?
		forceUpdateNextTime = false;
		General.updateZoomFactor();
		renderBackground(ForceUpdate);
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				if (scrollLocked==false) {
					if(General.getBetween(0, Walrii.getXPos()-Math.round(getWidth()/2), Map.getWidth()-getWidth())+_x == Walrii.getXPos() && General.getBetween(0, Walrii.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Walrii.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
						PlayerTile = Walrii.getCurrentTile()+Walrii.TileChangeWhileWalking;
						TilesDrawn++;
						int __y = Math.round(General.getMax((_y*window.blocksize)-Math.round(window.blocksize/4), 0));
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, __y);					// render char
						Walrii.setNewLastXPos(_x);
						Walrii.setNewLastYPos(_y);
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {
							TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);
						}	// add foreground layer
					}
				} else {	//This disables the automated screenscrolling for special areas on the map (like in huge gardens) so only the y-axis scrolls. Similar to behavior close to map's edges
					if(Walrii.getXPos()-screenLeft==_x && General.getBetween(0, Walrii.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Walrii.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);
						PlayerTile = Walrii.getCurrentTile()+Walrii.TileChangeWhileWalking;
						TilesDrawn++;
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, General.getMax(window.blocksize*_y-Math.round(getZoom()/4), 0));		// render char
						Walrii.setNewLastXPos(_x);
						Walrii.setNewLastYPos(_y);
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {
							TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);
						}	// add foreground layer
					}
				}
			}
		}
		renderForeground(ForceUpdate);
		Trigger.trigger(Walrii.getXPos(), Walrii.getYPos());
		if(Trigger.get(Walrii.getXPos(), Walrii.getYPos())==0.0 && forceUpdateNextTime==true) {	// To prevent left over fragments from popups
			forceUpdateNextTime = false;
			render(true);
		}
		Walrii.setLastXPos(Walrii.newLastXPos);
		Walrii.setLastYPos(Walrii.newLastYPos);
		if (General.showTileUpdates) {
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
		// Also used to copy Map contents to Screen Map
		// General.DebugLog("set x="+_x+" y="+_y+" to id="+i);
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