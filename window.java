package CodeW;
import javax.swing.JFrame;
import javax.swing.JLabel;
import CodeW.TileArea;
import CodeW.TileSource;

public class window {
	public static void main(String[] args) {
		LoadBlocks();										// Loading background block data
		Screen.setSize(12,18);								// Setup Screen (results in 0-11 x 0-17)
		JFrame window = Screen.createWindow();				// Create window object (JFrame)
		JLabel label = Screen.createLabel(window);			// Create stuff in window (JLabel)
		Map.setSize(100, 100);								// Create map with given size
		Player Walrii = Player.createPlayer(0, 10, 10);		// Define Player
		TileArea tileArea = Screen.createTileArea(window);	
		Player.setXPos(20);
		Player.setYPos(10);
		Map.createRandom(0, 96);
		Map.loadCity1();
		for(int _x = 0;_x<100;_x++) {
			System.out.println(TileSource.getXPos(_x)+" - "+TileSource.getYPos(_x));
		}
		Player.setXPos(12);
		Player.setYPos(35);
		Screen.update();
		Screen.render(true);
	    while (true) {				// MAIN GAME LOOP
			
	    	for(int _x=1;_x<100;_x++) {
	    		Player.setXPos(_x);
	    		Player.setYPos(30);
	    		Screen.update();
	    		Screen.render(true);
	    		Screen.DumpOldData();
	    		window.repaint();
	    		General.sleep(1000);
	    	}
		}
	}

	public static void LoadBlocks() {
		Block.AddNew(5, "grass", false, 5, 105);
		Block.AddNew(6, "stone", false, 6, 106);
		Block.AddNew(7, "wood", true, 7, 107);
		Block.AddNew(8, "sheep", false, 8, 108);
		Block.AddNew(9, "apple", false, 9, 109);
		Block.AddNew(new Block(10, "lava", true, 10, 110));
		Block.AddNew(new Block(11, "water", true, 11, 111));
		
		
		
		
	}
}