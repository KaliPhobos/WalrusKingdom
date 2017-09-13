package CodeW;

import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keys implements KeyListener
{
	public static boolean keyDown[];
	
	public void keyPressed(KeyEvent ke)
	{
		keyDown[ke.getKeyCode()] = true;
	}	
	public void keyReleased(KeyEvent ke)
	{
		keyDown[ke.getKeyCode()] = false;
	}	
	public void keyTyped(KeyEvent ke)
	{
	}
	
	public Keys(JFrame _window)
	{
		keyDown = new boolean[1024];
		_window.addKeyListener(this);
	}

	public static void checkInput()
	{
		if (window.window.getWidth()* window.window.getHeight()!=Screen.ScreenSizeIndicator) {		// triggered if you resize the window
			
			if (Screen.ScreenSizeIndicator==0) {
				Screen.tileArea = new TileArea(window.window.getWidth(), window.window.getHeight());	// thx p4nix for tellign me to do this xD
				Screen.ScreenSizeIndicator = window.window.getWidth()* window.window.getHeight();
			} else {
				Screen.tileArea = new TileArea(window.window.getWidth(), window.window.getHeight());	// thx p4nix for tellign me to do this xD
				Screen.ScreenSizeIndicator = window.window.getWidth()* window.window.getHeight();
				Screen.render(true);		//causing troubls if Map.MAP[] isn't already defined (first run)
			}
		}
		//General.DebugLog(window.GameStat);
		if (System.currentTimeMillis() > Menu.KeyPause) {
			if (window.GameStat.equals("Game")) {											// IN THE GAME
				Player.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
		    		Player.go("left");
				}
				if(keyDown[38]==true) {
		    		Player.go("up");
				}
				if(keyDown[39]==true) {
		    		Player.go("right");
				}
				if(keyDown[40]==true) {
		    		Player.go("down");
				}
				if(keyDown[27]==true) {
					Map.MapToResume = Map.currentMapName;
					Player.xPosToResume = Player.xPos;
					Player.yPosToResume = Player.yPos;
					Menu.RunMenu();
				}
				if(keyDown[73]==true) {
					General.DebugLog("x="+Player.getXPos()+" y="+Player.getYPos());
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Menu")) {									// IN THE MAIN MENU
				Player.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("left");
				}
				if(keyDown[38]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("up");
				}
				if(keyDown[39]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("right");
				}
				if(keyDown[40]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("down");
				}
				if(keyDown[73]==true) {
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Paused")) {									// GAME IS PAUSED
				Player.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("left");
				}
				if(keyDown[38]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("up");
				}
				if(keyDown[39]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("right");
				}
				if(keyDown[40]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
		    		Player.go("down");
				}
				if(keyDown[73]==true) {
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Intro")) {									// ASKING TO START THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					Menu.KeyPause = System.currentTimeMillis()+1000;
					General.DebugLog("intro -> load main");
					// goes to Intro.IntroEnd();	(walk out of forest)
				}
				if(keyDown[32]==true) {
					General.DebugLog("intro -> continue Intro");
					window.GameStat = "ContinueIntro";
					Intro.continueIntro();
				}
			} else if (window.GameStat.equals("ContinueIntro")) {									// INSIDE THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					General.DebugLog("continue intro -> load main");
					Menu.KeyPause = System.currentTimeMillis()+150;
					Intro.loadMainGame();
				}
			}
		}
	}
	public static boolean isPressed(int key) {
		return keyDown[key];
	}
}