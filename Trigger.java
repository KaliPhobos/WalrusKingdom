package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Trigger {
	public static String[] NotificationTexts = {"Press A to read the sign", "Press A to open the door", "Press A to talk to statue"};
	public static int getForegroundID(int _x, int _y) {
		double _data = Map.EventMap[General.getBetween(0, _x, Map.getWidth())][General.getBetween(0, _y, Map.getHeight())];;
		return (int) ((_data-(_data%901))/901)%901;
	}
	public static int getForegroundID(double _data) {
		return Map.getForegroundID(_data);
	}
	public static int getBackgroundID(int _x, int _y) {
		double _data = Map.EventMap[General.getBetween(0, _x, Map.getWidth())][General.getBetween(0, _y, Map.getHeight())];;
		return (int) _data%901;
	}
	public static int getBackgroundID(double _data) {
		return Map.getBackgroundID(_data);
	}
	public static long get(int _x, int _y) {
		return Map.EventMap[General.getBetween(0, _x, Map.getWidth())][General.getBetween(0, _y, Map.getHeight())];
	}
	public static void trigger(int _x, int _y) {
		int BackgroundID = getBackgroundID(_x, _y);
		if(BackgroundID!=0) {
			Screen.forceUpdateNextTime = true;
			System.out.println("trigger: "+BackgroundID);
			switch(Map.currentMapName) {
			case "City1":
				Graphics2D g = prepareGraphics();
				g.drawString(NotificationTexts[BackgroundID-1], General.adaptZoom(215), General.adaptZoom(254));
			}
		}
	}
	public static Graphics2D prepareGraphics() {
		TileSource infobox = new TileSource("/CodeW/assets/infobox.png", window.blocksize);
		TileArea.drawTile(infobox, 203, 240, 0, 0, 170, 21);		// PRESS SPAE TO INTERACT
		Graphics2D g = TileArea.m_image.createGraphics();
		g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(16))); 
		g.setColor(Color.decode("#161618"));
		return g;
	}
}
