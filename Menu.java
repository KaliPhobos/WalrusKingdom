package CodeW;

public class Menu {
	public static TileSource menuTileFront = new TileSource("/CodeW/assets/menu_front.png", window.blocksize);
	public static TileSource menuTileBack = new TileSource("/CodeW/assets/menu_back.png", window.blocksize);
	public static TileSource menuTileSelect = new TileSource("/CodeW/assets/menu_select.png", window.blocksize);
	public static long NextUpdate = System.currentTimeMillis();
	public static long KeyPause = System.currentTimeMillis()-1;
	public static int SelectedID = 0;
	public static void Menu() {
		if (!window.GameStat.equals("Menu")) {
			// If the game didnt just start but you came from inside the game
			window.GameStat = "Paused";
			KeyPause = System.currentTimeMillis()+250;
		}
		while(true) {
			// This is the main menu
			for(int _shiftX=0;_shiftX<1200;_shiftX++) {
				TileArea.drawTile(menuTileBack, 0, 0, _shiftX, 0, 576+_shiftX, 384);	// add foreground layer (screenPos, SourcePos, SourceDim)
				TileArea.drawTile(menuTileSelect, 191, 136+SelectedID*27, 0, 0, 152, 23);	// add foreground layer (screenPos, SourcePos, SourceDim)
				TileArea.drawTile(menuTileFront, 70, 0, 0, 0, 430, 297);	// add foreground layer (screenPos, SourcePos, SourceDim)
				window.window.repaint();
			   	
				
			   	NextUpdate = System.currentTimeMillis()+40;
			   	while (System.currentTimeMillis()<NextUpdate) {
				   	General.sleep(1);
			   		if(System.currentTimeMillis()>KeyPause) {
			   			Keys.checkInput();
			   		}
			   	}
			}

		}
	}
}
