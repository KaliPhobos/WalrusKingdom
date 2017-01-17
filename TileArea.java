package CodeW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TileArea extends Component {
	public static BufferedImage m_image;
	private int m_width, m_height;
	
	public TileArea(int width, int height) {
		m_width = width;
		m_height = height;
		m_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public Dimension getPreferredSize() {
		// I have no idea why this is here
        return new Dimension(m_width, m_height);
    }
 
	public void paint(Graphics g) {
		// same again
		g.drawImage(m_image, 0, 0, null);
	}
	
	public static void drawTile(TileSource tileSource, int tilex, int tiley, int x, int y) {
		//This draws a regular tile used inside the game (map)
		// Zoom factor should be the same as tileSize (24) when 1:1 zoom
		Graphics2D g = m_image.createGraphics();
		x = General.adaptZoom(x);
	    y = General.adaptZoom(y);
	    g.drawImage(tileSource.getTile(tilex, tiley), x, y, Screen.getZoom(), Screen.getZoom(), null);
	}
	public static void drawTile(TileSource tileSource, int screenx, int screeny, int tilex, int tiley, int tilexdim, int tileydim) {
		//this draws tiles of different sizes (Menu, Inv)
		Graphics2D g = m_image.createGraphics();
		//float zoom = General.getMin((screenx+tilexdim)/window.getWidth(), (screeny+tileydim)/window.getHeight());
		//float x = (screenx+tilexdim)/window.getWidth();
		//float y =(screeny+tileydim)/window.getHeight();
		//System.out.println("x-zoom="+x+"		y-zoom="+y);
		screenx = General.adaptZoom(screenx);
	    screeny = General.adaptZoom(screeny);
	    int tilexdimZoomed = General.adaptZoom(tilexdim);
	    int tileydimZoomed = General.adaptZoom(tileydim);
		g.drawImage(tileSource.getTile(tilex, tiley, tilexdim, tileydim), screenx, screeny, tilexdimZoomed, tileydimZoomed, null);
	}
	public static void drawTile(TileSource tileSource, int screenx, int screeny, int tilex, int tiley, int tilexdim, int tileydim, String comment) {
		// to handle the special menu background
		// for whatever reason it gets stretched in X direction the more it shifts...
		// dunno why, dont ask me... ??	^	
		Graphics2D g = m_image.createGraphics();
		screenx = General.adaptZoom(screenx);
	    screeny = General.adaptZoom(screeny);
	    int tilexdimZoomed = General.adaptZoom(tilexdim);
	    int tileydimZoomed = General.adaptZoom(tileydim);
	    
	    //System.out.println("Tilesource X="+tilex+" Y="+tiley+" Breite="+tilexdim+" Höhe="+tileydim+" Wird gezeichnet auf X="+screenx+" Y="+screeny+" Breite="+tilexdimZoomed+" Höhe="+tileydimZoomed);
		g.drawImage(tileSource.getTile(tilex, tiley, tilexdim+tilex, tileydim), screenx, screeny, tilexdimZoomed, tileydimZoomed, null);
	}
	public static void drawInfo() {
		// Just some debug panel, called when pressing "I"
		Graphics2D g = m_image.createGraphics();
		g.setFont(new Font("Arial", Font.BOLD, 12)); 
		g.setColor(Color.white);
		g.drawString("X="+Player.xPos+"     Y="+Player.yPos, 10, 10);
	    g.drawString("Facing: "+Player.direction, 10, 20);
	    g.drawString("Block foreground:", 10, 35);
	    g.drawString("name="+Block.getNameFromID(Map.getForegroundID(Map.Map[Player.xPos][Player.yPos])), 15, 45);
	    g.drawString("solid="+Block.getSolidFromID((Map.getForegroundID(Map.Map[Player.xPos][Player.yPos]))), 15, 55);
	    g.drawString("id="+Map.getForegroundID(Map.Map[Player.xPos][Player.yPos]), 15, 65);
	    g.drawString("Block background:", 10, 80);
	    g.drawString("name="+Block.getNameFromID(Map.getBackgroundID(Map.Map[Player.xPos][Player.yPos])), 15, 90);
	    g.drawString("solid="+Block.getSolidFromID((Map.getBackgroundID(Map.Map[Player.xPos][Player.yPos]))), 15, 100);
	    g.drawString("id="+Map.getBackgroundID(Map.Map[Player.xPos][Player.yPos]), 15, 110);
	    g.drawString("Block-ID: "+Map.Map[Player.xPos][Player.yPos], 10, 125);
	    g.drawString("Map="+Map.currentMapName, 10, 140);
	}
	
}