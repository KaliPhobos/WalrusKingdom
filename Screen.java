package CodeW;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen {
	public static TileSource tiles = new TileSource("resources\\tiles.png", window.blocksize);

	public static void setSize(int _width, int _height) {
		ScreenWidth = _width;
		ScreenHeight = _height;
		createScreen(_width, _height);
	}
	public static double[][] ScreenMatrix;
	public static double[][] ScreenMatrixOld;
	public static int ScreenWidth;
	public static int ScreenHeight;
	public static Screen Screen = createScreen(ScreenWidth, ScreenHeight);
	public Screen(int _width, int _height) {
		ScreenMatrix = new double[_width][_height];
		ScreenMatrixOld = new double[_width][_height];
	}
	public static JFrame createWindow() {
		JFrame frame = new JFrame("CodeW");
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(window.blocksize*ScreenWidth, window.blocksize*ScreenHeight);		// set window size
	    return frame;
	}
	public static TileArea createTileArea(JFrame frame) {
		TileArea tileArea = new TileArea(window.blocksize*ScreenWidth, window.blocksize*ScreenHeight);
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
	public static void prepareCanvas(Canvas _canvas) {
		_canvas.setIgnoreRepaint(true);
        _canvas.setSize(window.width, window.height);
	}
	public static BufferStrategy createBuffer(JFrame _window, Canvas _canvas) {
        _window.add(_canvas);
        _window.pack();
        _window.setVisible(true);
		_canvas.createBufferStrategy(2);
		BufferStrategy _buffer = _canvas.getBufferStrategy();
        return _buffer;
	}
	public static Screen createScreen(int _width, int _height) {
		Screen screen = new Screen(_width, _height);
		ScreenMatrix = new double[_width][_height];
		ScreenMatrixOld = new double[_width][_height];
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
	public static void renderBackground(boolean ForceUpdate) {
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data = ScreenMatrix[_x][_y];				// load raw data
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true) {
					int _background = Map.getBackgroundID(data);	// extract background data
					TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);						// render background layer
				}
			}
		}
	}
	public static void renderForeground(boolean ForceUpdate) {
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data = ScreenMatrix[_x][_y];				// load raw data
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true) {
					int _foreground = Map.getForegroundID(data);	// extrace foreground data
					if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
				}
			}
		}
	}
	public static void render(boolean ForceUpdate) {
		renderBackground(ForceUpdate);
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				if(General.getMin(Map.getWidth()-getWidth(), General.getMax(0, Player.getXPos()-Math.round(getWidth()/2)))+_x == Player.getXPos() && General.getMin(Map.getHeight()-getHeight(), General.getMax(0, Player.getYPos()-Math.round(getHeight()/2)))+_y == Player.getYPos()) {
					TileArea.drawTile(tiles, TileSource.getXPos(6), TileSource.getYPos(6), window.blocksize*_x, General.getMax(window.blocksize*_y-6, 0));						// render background layer
				}
			}
		}
		renderForeground(ForceUpdate);
	}
	public static void UpdateOldData() {
		ScreenMatrixOld = ScreenMatrix;
	}
	public static void setField(int _x, int _y, double i) {
		ScreenMatrix[_x][_y] = i;		
	}
	public static int getWidth() {
		return ScreenWidth;
	}
	public static int getHeight() {
		return ScreenHeight;
	}
}
