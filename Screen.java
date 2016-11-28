package CodeW;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen {
	public static boolean scrollLocked = false;
	public static int screenLeft;
	public static int screenTop;
	public static TileSource tiles = new TileSource("resources\\tiles.png", window.blocksize);

	public static void setSize(int _width, int _height) {
		ScreenWidth = _width;
		ScreenHeight = _height;
		createScreen(_width, _height);
	}
	public static int PlayerTile = 6;
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
		screenLeft = Player.getXPos()-Math.round(getWidth()/2);	// Might be out of border
		screenTop = Player.getYPos()-Math.round(getHeight()/2);	// Might be out of border
		screenTop = General.getMin(Map.getHeight()-getHeight(), General.getMax(0, screenTop));		// Inside Borders!
		screenLeft = General.getMin(Map.getWidth()-getWidth(), General.getMax(0, screenLeft));		// Inside Borders!
		switch(Map.currentMapName) {
			case "City1":
				if (Player.getXPos()>24 && Player.getXPos()<49 && Player.getYPos()<25) {	// Inside the big garden --> only Y-axis scrolling enabled
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
		}
	}
	public static void renderBackground(boolean ForceUpdate) {
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				double data = ScreenMatrix[_x][_y];				// load raw data
				double dataOld = ScreenMatrixOld[_x][_y];
				if (data!=dataOld | ForceUpdate == true | Player.isLastPosition(_x, _y) | Player.isLastPosition(_x, _y+1)) {
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
				if (data!=dataOld | ForceUpdate == true | Player.isLastPosition(_x, _y) | Player.isLastPosition(_x, _y+1)) {
					int _foreground = Map.getForegroundID(data);	// extrace foreground data
					if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
				}
				if (Player.isLastPosition(_x, _y) | Player.isLastPosition(_x, _y+1)) {
					if(Map.getForegroundID(data)>0) {
						TileArea.drawTile(tiles, TileSource.getXPos(Map.getForegroundID(data)), TileSource.getYPos(Map.getForegroundID(data)), window.blocksize*_x, window.blocksize*_y);
					}
				}
			}
		}
	}
	public static void render(boolean ForceUpdate) {
		renderBackground(ForceUpdate);
		for(int _y=0;_y<getHeight();_y++) {
			for(int _x=0;_x<getWidth();_x++) {
				if (scrollLocked==false) {
					if(General.getBetween(0, Player.getXPos()-Math.round(getWidth()/2), Map.getWidth()-getWidth())+_x == Player.getXPos() && General.getBetween(0, Player.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Player.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);
						PlayerTile = Player.getCurrentTile()+Player.TileChangeWhileWalking;
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, General.getMax(window.blocksize*_y-6, 0));					// render background layer
						Player.newLastXPos = _x;
						Player.newLastYPos = _y;
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
						
					}
				} else {	//This disables the automated screenscrolling for special areas on the map (like in huge gardens) so only the y-axis scrolls. Similar behaviour like close to edges
					if(Player.getXPos()-screenLeft==_x && General.getBetween(0, Player.getYPos()-Math.round(getHeight()/2), Map.getHeight()-getHeight())+_y == Player.getYPos()) {
						int _background = Map.getBackgroundID(ScreenMatrix[_x][_y]);	// extract background data
						TileArea.drawTile(tiles, TileSource.getXPos(_background), TileSource.getYPos(_background), window.blocksize*_x, window.blocksize*_y);
						PlayerTile = Player.getCurrentTile()+Player.TileChangeWhileWalking;
						TileArea.drawTile(tiles, TileSource.getXPos(PlayerTile), TileSource.getYPos(PlayerTile), window.blocksize*_x, General.getMax(window.blocksize*_y-6, 0));
						Player.newLastXPos = _x;
						Player.newLastYPos = _y;
						int _foreground = Map.getForegroundID(ScreenMatrix[_x][_y]);	// extrace foreground data
						if(_foreground>0) {TileArea.drawTile(tiles, TileSource.getXPos(_foreground), TileSource.getYPos(_foreground), window.blocksize*_x, window.blocksize*_y);}	// add foreground layer
					}
				}
			}
		}
		renderForeground(ForceUpdate);
		Player.lastXPos = Player.newLastXPos;
		Player.lastYPos = Player.newLastYPos;
	}
	public static void UpdateOldData() {
		for(int _x=0;_x<(int)window.width/24;_x++) {
			for(int _y=0;_y<(int)window.height/24;_y++) {
				ScreenMatrixOld[_x][_y] = ScreenMatrix[_x][_y];
			}
		}
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
