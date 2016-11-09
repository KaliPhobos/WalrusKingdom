package CodeW;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TileArea extends Component {
	private static BufferedImage m_image;
	private int m_width, m_height;
	
	public TileArea(int width, int height) {
		m_width = width;
		m_height = height;
		m_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(m_width, m_height);
    }
 
	public void paint(Graphics g) {
		g.drawImage(m_image, 0, 0, null);
	}
	
	public static void drawTile(TileSource tileSource, int tilex, int tiley, int x, int y) {
		//Graphics graphics = _buffer.getDrawGraphics();
		//graphics.drawImage(tileSource.getTile(tilex, tiley), x, y, null);
		Graphics2D g = m_image.createGraphics();
	    g.drawImage(tileSource.getTile(tilex, tiley), x, y, null);
	}
}