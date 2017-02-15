package CodeW;

import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TileSource extends Component {
	private BufferedImage m_tiles;
	public static int m_tileSize;

	public TileSource(String filename, int tileSize) {
		m_tileSize = tileSize;
		try {
			m_tiles = ImageIO.read(TileSource.class.getResourceAsStream(filename));
		}
		catch (Exception e) { // no need to do anything here
			e.printStackTrace();
		}						// m_tiles will be null anyway
		
		if (m_tiles == null) {
			JOptionPane.showMessageDialog(null, "Harambe died for our sins.");
			return;
		}
	}
	public static int getXPos(int _id) {
		int _pos = (_id%10);		// *m_tilesize
		//System.out.println("XPos in tile:"+_pos);
		return _pos;
	}
	public static int getYPos(int _id) {
		//int _pos = (int) _id/10;
		//_pos = _pos*10;
		int _pos = (_id-(_id%10))/10;
		//System.out.println("YPos in tile:"+_pos);
		return _pos;
	}
	public int getTileSize() {
		return m_tileSize;
	}
	
	public BufferedImage getTile(int tilex, int tiley) {
		if (m_tiles == null) {
			return null;
		}
		try {
			return m_tiles.getSubimage(tilex*(m_tileSize+1)+1, tiley*(m_tileSize+1)+1, m_tileSize, m_tileSize);
		}
		catch(Exception e){
			System.out.println("Couldnt load tile subimage. Better check the BLOCKSIZE parameter and the sourcefile.");
			return new BufferedImage(0, 0, 0);
		}
	}
	public BufferedImage getTile(int tileXmin, int tileYmin, int tileXdim, int tileYdim) {
		if (m_tiles == null) {
			return null;
		}
		try {
			return m_tiles.getSubimage(tileXmin, tileYmin, tileXdim-tileXmin, tileYdim-tileYmin);
		}
		catch(Exception e){
			System.out.println("Couldnt load tile subimage. Better check the BLOCKSIZE parameter and the sourcefile.");
			return new BufferedImage(0, 0, 0);
		}
	}
}
