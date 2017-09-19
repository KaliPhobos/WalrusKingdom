package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Trigger {
	public static int _modeOld = 7;
	public static Graphics2D g;
	public static String[] NotificationTexts = {"Press A to read the sign", "Press A to open the door", "Press A to talk to statue", "Press A to look inside"};
	public static TileSource infobox; 		// for preloading ->preloadImages()
	public static int currentTrigger = 0;	// Global variable to hand over the current trigger's id
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
			if (General.ShowTriggers) {
				General.DebugLog("trigger: "+BackgroundID);
				currentTrigger = BackgroundID;
			}
			int x = Player.getXPos();
			int y = Player.getYPos();
			switch(Map.currentMapName) {
				case "City1":
					if(BackgroundID>0) {
						Graphics2D g = prepareGraphics(1);		// Show trigger text
						g.drawString(NotificationTexts[BackgroundID-1], General.adaptZoom(215), General.adaptZoom(254));
						// General.DebugLog("... drew text");
					} else {
						if((x==43)&&(y>45)&&(y<49)) {
							Graphics2D g = prepareGraphics(2);		// Show trigger text
							g.drawString("Enter the forest?", General.adaptZoom(215), General.adaptZoom(254));
						}
						if((x==44)&&(y>45)&&(y<49)) {
							Map.loadForestHouse();
							Player.setXPos(Player.getXPos()-30);
							Player.setYPos(Player.getYPos()-38);
							General.DebugLog("Teleport INTO FOREST");
						}
					}
				
				case "ForestHouse":
					if(BackgroundID>0) {
						Graphics2D g = prepareGraphics(3);		// Show trigger text
						g.drawString(NotificationTexts[BackgroundID-1], General.adaptZoom(215), General.adaptZoom(254));
					} else {
						if((x==13)&&(y>7)&&(y<11)) {
							Graphics2D g = prepareGraphics(4);		// Show trigger text
							g.drawString("Leave the forest?", General.adaptZoom(215), General.adaptZoom(254));
						}
						if((x==12)&&(y>7)&&(y<11)) {
							Map.loadCity1();
							Player.setXPos(Player.getXPos()+30);
							Player.setYPos(Player.getYPos()+38);
							General.DebugLog("teleport BACK");
						}
					}
					
			}
		}
	}
	public static Graphics2D prepareGraphics(int _mode) {
		if (_mode != _modeOld) {
			//General.DebugLog("-> Trigger.prepareGraphics");
			TileArea.drawTile(infobox, 203, 240, 0, 0, 170, 21);		// PRESS SPACE TO INTERACT
			g = TileArea.m_image.createGraphics();
			//General.DebugLog("... created Graphics");
			g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(16)));
			//General.DebugLog("... prepared Font");
			g.setColor(Color.decode("#161618"));
			//General.DebugLog("... prepared Colorcode");
			_modeOld = _mode;
		} else {
			
		}
		return g;
	}
}
