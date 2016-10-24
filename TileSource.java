package CodeW;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TileSource extends Component {
	private BufferedImage m_tiles;
	public static int m_tileSize;

	public TileSource(String filename, int tileSize) {
		m_tileSize = tileSize;
		try {
			m_tiles = ImageIO.read(new File(filename));
		}
		catch (IOException e) { // no need to do anything here
		}						// m_tiles will be null anyway
		
		if (m_tiles == null) {
			JOptionPane.showMessageDialog(null, "Harambe died for our sins.");
			return;
		}
	}
	public static int getXPos(int _id) {
		int _pos = (_id%10);		// *m_tilesize
		System.out.println("XPos in tile:"+_pos);
		return _pos;
	}
	public static int getYPos(int _id) {
		//int _pos = (int) _id/10;
		//_pos = _pos*10;
		int _pos = (_id-(_id%10))/10;
		System.out.println("YPos in tile:"+_pos);
		return _pos;
	}
	public int getTileSize() {
		return m_tileSize;
	}
	
	public BufferedImage getTile(int tilex, int tiley) {
		if (m_tiles == null) {
			return null;
		}
		return m_tiles.getSubimage(tilex*(m_tileSize+1)+1, tiley*(m_tileSize+1)+1, m_tileSize, m_tileSize);
	}
}
