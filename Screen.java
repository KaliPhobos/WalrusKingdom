package CodeW;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen {
	public static TileSource tiles = new TileSource("resources\\tiles.png", 24);

	public static void setSize(int _width, int _height) {
		ScreenWidth = _width;
		ScreenHeight = _height;
		createScreen(_width, _height);
	}
	public static int[][] ScreenMatrix;
	public static int ScreenWidth;
	public static int ScreenHeight;
	public static Screen Screen = createScreen(ScreenWidth, ScreenHeight);
	public Screen(int _width, int _height) {
		ScreenMatrix = new int[_width][_height];
	}
	public static JFrame createWindow() {
		JFrame frame = new JFrame("CodeW");
        frame.setIgnoreRepaint(true);
		frame.setSize(24*ScreenWidth, 24*ScreenHeight);		// set window size
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	    return frame;
	}
	public static TileArea createTileArea(JFrame frame) {
		TileArea tileArea = new TileArea(24*ScreenWidth, 24*ScreenHeight);
		frame.add("Center", tileArea);
		frame.pack();
	    frame.setVisible(true);
		return tileArea;
	}
	public static JLabel createLabel(JFrame frame) {	
		JLabel lbl = new JLabel();
		frame.add(lbl);
		return lbl;
	}
	public static BufferStrategy createBuffer(Canvas canvas) {
		canvas.createBufferStrategy(2);
        BufferStrategy buffer = canvas.getBufferStrategy();
        return buffer;
	}
	public static Screen createScreen(int _width, int _height) {
		Screen screen = new Screen(_width, _height);
		ScreenMatrix = new int[_width][_height];
		return screen;
	}
	public static void update() {
		int left = Player.getXPos()-Math.round(getWidth()/2);	// Might be out of border
		int top = Player.getYPos()-Math.round(getHeight()/2);	// Might be out of border
		top = General.getMin(Map.getHeight()-getHeight(), General.getMax(0, top));		// Inside Borders!
		left = General.getMin(Map.getWidth()-getWidth(), General.getMax(0, left));		// Inside Borders!
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				Screen.setField(_x, _y, Map.get(left+_x, top+_y));
				
			}
		}
	}
	public static void render() {
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				int data = ScreenMatrix[_x][_y];
				int _background = Map.getBackgroundID(data);
				int _foreground = Map.getForegroundID(data);
				System.out.println("background:"+_background+"    foreground:"+_foreground);
				System.out.println("");
				System.out.println("Zeichne background id: "+_background);
				TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), 24*_x, 24*_y);
				System.out.println("");
				System.out.println("Zeichne foreground id: "+_foreground);
				TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_background), 24*_x, 24*_y);
			}
		}
	}
	public static void setField(int _x, int _y, int i) {
		ScreenMatrix[_x][_y] = i;		
	}
	public static int getWidth() {
		return ScreenWidth;
	}
	public static int getHeight() {
		return ScreenHeight;
	}
}
