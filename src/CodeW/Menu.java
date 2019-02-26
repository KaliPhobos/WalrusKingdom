package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {
	public static TileSource menuTileFront = new TileSource("/CodeW/assets/menu_front.png", window.blocksize);
	public static TileSource menuTileText = new TileSource("/CodeW/assets/menu_text.png", window.blocksize);
	public static TileSource menuTileBack = new TileSource("/CodeW/assets/menu_back.png", window.blocksize);
	public static long NextUpdate = System.currentTimeMillis();
	public static long KeyPause = System.currentTimeMillis()-1;
	public static int SelectedID = 0;
	public static int oldWidth;
	public static int oldHeight;
	static Player Walrii = window.getPlayerObject();
	public static String LastMap = Map.currentMapName;		// used to go back to the right map after opening the menu
	public static void RunMenu() {
		oldWidth = window.getWidth();			// necessary since the menu is not flexible in size
		oldHeight = window.getHeight();			// ... and never will be, not gonna change that
		window.resize(576, 384);
		Map.loadMenu();
		if (!window.GameStat.equals("Menu")) {
			// If the game didnt just start but you came from inside the game
			window.GameStat = "Paused";
			KeyPause = System.currentTimeMillis()+250;			// Key update interval - 4Hz
		}
		Screen.update();
		while(true) {
			// This is the main menu
			Graphics2D g = TileArea.m_image.createGraphics();
			g.setFont(new Font("Arial", Font.BOLD, 12)); 
			g.setColor(Color.white);
			int _shiftX = 0;	// so it actually does a perfect loop no matter what zoom option. else it adds a break after finishing each round
			for(int _shiftXcounter=0;_shiftX<1200;_shiftXcounter++) {
				_shiftX = General.getBetween(1,  (long) _shiftXcounter*(1200*(Screen.getZoomX()/24))/1200, 1200);
				TileArea.drawTile(menuTileBack, 0, 0, _shiftX, 0, 576, 384, "abc");	// add foreground layer (screenPos, SourcePos, SourceDim)
				Screen.renderBackground(true);
				TileArea.drawTile(menuTileText, 0, 0, 0, 0, 576, 384);	// add foreground layer (screenPos, SourcePos, SourceDim)
				Screen.PlayerTile = Walrii.getCurrentTile()+Walrii.TileChangeWhileWalking;
				TileArea.drawTile(Screen.tiles, TileSource.getXPos(Screen.PlayerTile), TileSource.getYPos(Screen.PlayerTile), window.blocksize*Walrii.getXPos(), General.getMax(window.blocksize*Walrii.getYPos()-6, 0));		// render char
				Screen.renderForeground(true);

				window.window.repaint();
			   	
				
			   	NextUpdate = System.currentTimeMillis()+10;
			   	while (System.currentTimeMillis()<NextUpdate) {			// wait for update
				   	General.sleep(1);
			   		if(System.currentTimeMillis()>KeyPause) {
			   			Keys.checkInput();
			   		}
			   	}
			   	int x = Walrii.getXPos();
			   	int y = Walrii.getYPos();
			   	if (x==0) {
			   		if (y==5||y==6) {window.Start();}
			   		if (y==9||y==10) {
			   			window.resize(oldWidth, oldHeight);				// return to previous screen size
			   			if (Map.MapToResume.equals("initial")) {
			   				window.Start();
			   			} else {
			   				window.Resume();}
			   			}
			   		if (y==13||y==14) {if (General.showClasses) {General.DebugLog("Menu.SAVE GAME");}}
			   		
			   	}
			   	if (x==23) {
			   		if (y==5||y==6) {if (General.showClasses) {General.DebugLog("Menu.SETTINGS");}}
			   		if (y==9||y==10) {if (General.showClasses) {General.DebugLog("Menu.CREDITS SCREEN");}}
			   		if (y==13||y==14) {System.exit(0);}
			   		
			   	}
			}
		}
	}
}
