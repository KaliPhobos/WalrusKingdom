package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Trigger {
	static Player Walrii = window.getPlayerObject();
	public static int _modeOld = 7;
	public static Graphics2D g;
	public static String[] NotificationTexts = {"[A]: read the sign", "[A]: enter house", "[A]: talk to statue", "[A]: look inside"};
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
		int BackgroundID = getBackgroundID(_x, _y);		// Accessing TRIGGER map, not regular map!
		if(BackgroundID!=0) {	// Trigger in place
			Screen.forceUpdateNextTime = true;			// Hide "press A to ..." pop-up with next interaction
			currentTrigger = BackgroundID;
			if (General.showTrigger) {
				General.DebugLog("trigger: "+BackgroundID);
			}
			int x = Walrii.getXPos();
			int y = Walrii.getYPos();
			switch(Walrii.getCurrentMapName()) {
				case "City1":
					if(BackgroundID>0) {		// Positive trigger IDs are regular items/signs
						Graphics2D g = prepareGraphics(1);		// Show trigger text
						g.drawString(NotificationTexts[BackgroundID-1], General.adaptZoom(Screen.getWidth()*(window.blocksize/2)-60), General.adaptZoom(Screen.getHeight()*(window.blocksize/2)+(window.blocksize*2)));
						// General.DebugLog("... drew text");
					} else {					// negative trigger IDs are teleportations points
						if((x==44)&&(y>45)&&(y<49)) {
							Graphics2D g = prepareGraphics(2);			// Show trigger text
							g.drawString("Enter the forest?", General.adaptZoom(Screen.getWidth()*(window.blocksize/2)-60), General.adaptZoom(Screen.getHeight()*(window.blocksize/2)+(window.blocksize*2)));
						}										// 0 = none, -1 = forest/town teleporter
						if((x==45)&&(y>45)&&(y<49)) {		// automated teleport (enter forest)
							Map.loadForestHouse();
							Walrii.setXPos(Walrii.getXPos()-33+6);		// not similar to number below for whatever reason (changed forest map size)
							Walrii.setYPos(Walrii.getYPos()-38);
							if (General.showTrigger) {
								General.DebugLog("Teleport INTO FOREST");
							}
						}
					}
					break;
				case "ForestHouse":
					if(BackgroundID>0) {
						Graphics2D g = prepareGraphics(3);			// Show trigger text
						g.drawString(NotificationTexts[BackgroundID-1], General.adaptZoom(Screen.getWidth()*(window.blocksize/2)-60), General.adaptZoom(Screen.getHeight()*(window.blocksize/2)+(window.blocksize*2)));
					} else {
						if((x==17)&&(y>7)&&(y<11)) {
							Graphics2D g = prepareGraphics(4);		// Show trigger text
							g.drawString("Leave the forest?", General.adaptZoom(Screen.getWidth()*(window.blocksize/2)-60), General.adaptZoom(Screen.getHeight()*(window.blocksize/2)+(window.blocksize*2)));
						}
						if((x==16)&&(y>7)&&(y<11)) {
							Map.loadCity1();
							Walrii.setXPos(Walrii.getXPos()+27);		// not similar to number above for whatever reason (changed forest map size)
							Walrii.setYPos(Walrii.getYPos()+38);
							if (General.showTrigger) {
								General.DebugLog("teleport BACK");
							}
						}
					}
					break;
					
			}
		} else {
			currentTrigger = 0;
		}
		
	}
	public static Graphics2D prepareGraphics(int _mode) {
			//General.DebugLog("-> Trigger.prepareGraphics");
			TileArea.drawTile(infobox, Screen.getWidth()*12-85, Screen.getHeight()*(window.blocksize/2)+(window.blocksize*3/2)-3, 0, 0, 170, 21);		// PRESS SPACE TO INTERACT
			g = TileArea.m_image.createGraphics();
			//General.DebugLog("... created Graphics");
			g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(16)));
			//General.DebugLog("... prepared Font");
			g.setColor(Color.decode("#161618"));
			//General.DebugLog("... prepared Colorcode");
			_modeOld = _mode;		// no longer in use
			if (General.showTrigger) {
				General.DebugLog("Trigger mode "+ _mode);
			}
		return g;
	}
}
