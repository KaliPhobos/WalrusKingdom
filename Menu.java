package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {
	public static TileSource menuTileFront = new TileSource("/CodeW/assets/menu_front.png", window.blocksize);
	public static TileSource menuTileBack = new TileSource("/CodeW/assets/menu_back.png", window.blocksize);
	public static long NextUpdate = System.currentTimeMillis();
	public static long KeyPause = System.currentTimeMillis()-1;
	public static int SelectedID = 0;
	public static String LastMap = Map.currentMapName;		// used to go back to the right map after opening the menu
	public static void Menu() {
		Map.loadMenu();
		if (!window.GameStat.equals("Menu")) {
			// If the game didnt just start but you came from inside the game
			window.GameStat = "Paused";
			KeyPause = System.currentTimeMillis()+250;
		}
		Screen.update();
		while(true) {
			// This is the main menu
			Graphics2D g = TileArea.m_image.createGraphics();
			g.setFont(new Font("Arial", Font.BOLD, 12)); 
			g.setColor(Color.white);
			for(int _shiftX=0;_shiftX<1200;_shiftX++) {
				TileArea.drawTile(menuTileBack, 0, 0, _shiftX, 0, 576+_shiftX, 384);	// add foreground layer (screenPos, SourcePos, SourceDim)
				TileArea.drawTile(menuTileFront, 0, 0, 0, 0, 576, 381);	// add foreground layer (screenPos, SourcePos, SourceDim)
				Screen.PlayerTile = Player.getCurrentTile()+Player.TileChangeWhileWalking;
				TileArea.drawTile(Screen.tiles, TileSource.getXPos(Screen.PlayerTile), TileSource.getYPos(Screen.PlayerTile), window.blocksize*Player.getXPos(), General.getMax(window.blocksize*Player.getYPos()-Math.round(Screen.getZoom()/4), 0));		// render char

				window.window.repaint();
			   	
				
			   	NextUpdate = System.currentTimeMillis()+20;
				System.out.println(_shiftX);
			   	while (System.currentTimeMillis()<NextUpdate) {
				   	General.sleep(1);
			   		if(System.currentTimeMillis()>KeyPause) {
			   			Keys.checkInput();
			   		}
			   	}
			   	int x = Player.getXPos();
			   	int y = Player.getYPos();
			   	if (x==0) {
			   		if (y==5||y==6) {window.Start();}
			   		if (y==9||y==10) {
			   			if (Map.MapToResume.equals("initial")) {
			   				window.Start();
			   			} else {
			   				window.Resume();}
			   			}
			   		if (y==13||y==14) {System.out.println("SAVE GAME");}
			   		
			   	}
			   	if (x==23) {
			   		if (y==5||y==6) {System.out.println("SETTINGS");}
			   		if (y==9||y==10) {System.out.println("CREDITS SCREEN");}
			   		if (y==13||y==14) {System.exit(0);}
			   		
			   	}
			}
		}
	}
}
