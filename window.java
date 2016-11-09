package CodeW;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import CodeW.TileArea;
import CodeW.TileSource;
import java.awt.Canvas;


public class window {
	public static int width = 1000;
	public static int height = 528;
	public static int blocksize = 24;
	public static BufferStrategy buffer;
	public static void main(String[] args) {
		LoadBlocks();										// Loading background block data
		Screen.setSize(width/blocksize, height/blocksize);	// Setup Screen (results in 0-11 x 0-17)
		JFrame window = Screen.createWindow();				// Create window object (JFrame)
//		JLabel label = Screen.createLabel(window);			// Create stuff in window (JLabel)
		//Canvas canvas = new Canvas();
		//Screen.prepareCanvas(canvas);
		//buffer = Screen.createBuffer(window, canvas);
		//KeyControls.prepare(canvas);
		//window.add(canvas);
		window.pack();
		window.setVisible(true);
		
		Map.setSize(100, 100);								// Create map with given size
		//Player Walrii = Player.createPlayer(0, 10, 10);		// Define Player
		TileArea tileArea = Screen.createTileArea(window);	
		Player.setXPos(0);
		Player.setYPos(20);
		Map.createRandom(0, 96);
		Map.loadCity1();
		Screen.update();
		Screen.render(true);
		while (true) {				// MAIN GAME LOOP
	    	Screen.update();
	    	Screen.render(true);
	    	Screen.UpdateOldData();
	    	window.repaint();
	    	KeyControls.checkInput();
	    	General.sleep(10);
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