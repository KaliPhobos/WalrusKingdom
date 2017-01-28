package CodeW;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;

public class Intro {
	public static void Intro() {
		window.GameStat = "Intro";
		Menu.KeyPause = System.currentTimeMillis()+900001;	// To prevent skiping the intro before the first dialogue appears
	   	Keys.checkInput();
	   	WalriiWalkX(0, 15);
		Menu.KeyPause = System.currentTimeMillis();
		TileSource textbox = new TileSource("/CodeW/assets/textbox.png", window.blocksize);
		TileArea.drawTile(textbox, 9, 219, 0, 0, 558, 150);	// add foreground layer (screenPos, SourcePos, SourceDim)
		Graphics2D g = TileArea.m_image.createGraphics();
		g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(26))); 
		g.setColor(Color.decode("#161618"));
		g.drawString("This is a recorded personal message sent to you by", General.adaptZoom(30), General.adaptZoom(260));
		g.drawString("Prof. Dr. Elric, head of the royal science center.", General.adaptZoom(30), General.adaptZoom(290));
		g.setColor(Color.decode("#861618"));
		g.drawString("Press [SPACE] to continue or [ESC] to skip the intro", General.adaptZoom(30), General.adaptZoom(350));
		window.window.repaint();
		Menu.KeyPause = System.currentTimeMillis()+1000;
		while(Keys.isPressed(32)==false && Keys.isPressed(27)==false) {
		   	Keys.checkInput();
			TileArea.drawTile(textbox, 9, 219, 0, 0, 558, 150);	// add foreground layer (screenPos, SourcePos, SourceDim)
			g = TileArea.m_image.createGraphics();
			g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(26))); 
			g.setColor(Color.decode("#161618"));
			g.drawString("This is a recorded personal message sent to you by", General.adaptZoom(30), General.adaptZoom(260));
			g.drawString("Prof. Dr. Elric, head of the royal science center.", General.adaptZoom(30), General.adaptZoom(290));
			g.setColor(Color.decode("#861618"));
			g.drawString("Press [SPACE] to continue or [ESC] to skip the intro", General.adaptZoom(30), General.adaptZoom(350));
			window.window.repaint();
			General.sleep(100);
		}
		if(window.GameStat.equals("ContinueIntro")) {
			continueIntro();
		} else {
			IntroEnd();
		}
	}
	
	public static void IntroEnd() {
		WalriiWalkX(15, 29);
		loadMainGame();
	}
	public static void loadMainGame(){
		window.GameStat = "Game";
		System.out.println("intro.loadMainGame");
		Map.loadCity1();
		Player.setXPos(0);
		Player.setYPos(37);
		Player.setXPosToResume(0);		// WIll be restored instead of xPos and yPos (overwritten in Menu)
		Player.setYPosToResume(37);
		Screen.update();
		Screen.render(true);
	   	window.window.repaint();
	   	Menu.KeyPause = System.currentTimeMillis()+1000;
	   	window.Resume();
	}
	
	
	public static void continueIntro() {		
		window.GameStat = "ContinueIntro";
		Menu.KeyPause = System.currentTimeMillis()+150;
		DisplayMessage("Hey there, Walrii.", "As you maybe already know, I'm Dr. Elric and also", "a close friend of our King since many many years.");
		System.exit(0);
		DisplayMessage("I do not know if he has ever told you this, but your", "grandfather used to be a good friend of mine, too,", "when we were younger.");
		DisplayMessage("I am sending you this message as I have no-one else", "I can trust any longer.", "Someone has poisoned me and I'm probably already");
		DisplayMessage("dead when you receive this message. But I need you", "to do something for me,  I need your help, Walrii!", "");
		DisplayMessage("We do not have much time so listen closely to what", "I am about to tell you:", "");
		DisplayMessage("As we all know the King loves walruses and it was 2", "years ago when he decided to turn every human", "being into a walrus. A bold decicion!");
		DisplayMessage("I was the one helping him achive this goal. I built", "a giant machine that would rearrange out genetics,", "and my machine worked: We are all walruses now.");
		DisplayMessage("At first this plan seemed totally crazy.", "Later it did turn out to be a great idea.", "People love being Walruses. Me, too!");
		DisplayMessage("But still we should have realized it back then...", "", "");
		DisplayMessage("It hurts to admit it, but our king IS crazy. ", "And now he has really turned mad.", "You MUST stop him!");
		DisplayMessage("This time he wants to ban everyone from eating fish,", "can you believe that, making it illegal to eat any sort", "of fish without exceptions?");
		DisplayMessage("This is not just stupidity, it's madness!", "We all turned into walruses after all!", "It's only natural for us to eat fish!");
		DisplayMessage("I admit it, we all could still survive eating", "other stuff instead of fish. But still...", "Where's the fun in that?");
		DisplayMessage("Where is the fun in being a walrus if you can not", "eat fish every day? There simply is none!!", "I would rather die than never eat fish again!");
		DisplayMessage("I admit this might sound stupid coming from a man", "who is already dead, but you get the point, right?", "We have to stop him for the sake of all walruses.");
		General.sleep(1000);
		window.GameStat = "ContinueIntro";
		while(Keys.isPressed(32)==false) { Keys.checkInput(); }
		Screen.render(true);
		IntroEnd();

	}
	
	public static Graphics2D prepareGraphics() {
		Graphics2D g = TileArea.m_image.createGraphics();
		g.setFont(new Font("DPComic", Font.PLAIN, General.adaptZoom(26))); 
		g.setColor(Color.decode("#161618"));
		return g;
	}
	
	public static void DisplayMessage(String Str1, String Str2, String Str3) {
		TileSource textbox = new TileSource("/CodeW/assets/textbox.png", window.blocksize);
		Screen.render(true);
		TileArea.drawTile(textbox, 9, 9, 0, 0, 558, 150);	// add foreground layer (screenPos, SourcePos, SourceDim)
		Graphics2D g = prepareGraphics();
		g.drawString(Str1, General.adaptZoom(30), General.adaptZoom(50));
		g.drawString(Str2, General.adaptZoom(30), General.adaptZoom(80));
		g.drawString(Str3, General.adaptZoom(30), General.adaptZoom(110));
		g.setColor(Color.decode("#861618"));
		g.drawString("Press [SPACE] to continue or [ESC] to skip the intro", General.adaptZoom(30), General.adaptZoom(140));
		window.window.repaint();
		General.sleep(1000);
		while(Keys.isPressed(32)==false) {
			Keys.checkInput();
			TileArea.drawTile(textbox, 9, 9, 0, 0, 558, 150);	// add foreground layer (screenPos, SourcePos, SourceDim)
			g = prepareGraphics();
			g.drawString(Str1, General.adaptZoom(30), General.adaptZoom(50));
			g.drawString(Str2, General.adaptZoom(30), General.adaptZoom(80));
			g.drawString(Str3, General.adaptZoom(30), General.adaptZoom(110));
			g.setColor(Color.decode("#861618"));
			g.drawString("Press [SPACE] to continue or [ESC] to skip the intro", General.adaptZoom(30), General.adaptZoom(140));
			window.window.repaint();
			General.sleep(100);
		}
	}
	
	public static void WalriiWalkX(int xStart, int xEnd) {
		for(int _i=xStart;_i<xEnd;_i++) {
			Player.TileChangeWhileWalking = 1;
			Player.nextStep = System.currentTimeMillis()+Player.StepDuration;
			Screen.update();
			Screen.render(true);
		   	window.window.repaint();
		   	Keys.checkInput();
			General.sleep(Player.StepDuration/2);
			Player.TileChangeWhileWalking = 0;
			Player.setXPos(_i);
			Screen.update();
			Screen.render(true);
		   	window.window.repaint();
		   	Keys.checkInput();
		   	General.sleep(Player.StepDuration);
		}
	}
}
