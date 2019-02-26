package CodeW;

import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keys implements KeyListener {
	public static boolean keyDown[];
	static Player Walrii = window.getPlayerObject();

	
	public void keyPressed(KeyEvent ke)	{
		keyDown[ke.getKeyCode()] = true;
	}	
	public void keyReleased(KeyEvent ke) {
		keyDown[ke.getKeyCode()] = false;
	}	
	public void keyTyped(KeyEvent ke) { }
	
	public Keys(JFrame _window)	{
		keyDown = new boolean[1024];
		_window.addKeyListener(this);
	}

	public static void checkInput() {
		/*for (int _temp = 0;_temp<1024;_temp++) {
			if (keyDown[_temp]) {
				System.out.println(_temp);
			}
		}*/
		if (window.window.getWidth()* window.window.getHeight()!=Screen.ScreenSizeIndicator) {		// triggered if you resize the window
			
			if (Screen.ScreenSizeIndicator==0) {
				Screen.tileArea = new TileArea(window.window.getWidth(), window.window.getHeight());	// thx p4nix for telling me to do this xD
				Screen.ScreenSizeIndicator = window.window.getWidth()* window.window.getHeight();
			} else {
				Screen.tileArea = new TileArea(window.window.getWidth(), window.window.getHeight());	// thx p4nix for telling me to do this xD
				Screen.ScreenSizeIndicator = window.window.getWidth()* window.window.getHeight();
				Screen.render(true);		//causing troubls if Map.MAP[] isn't already defined (first run)
			}
		}
		//General.DebugLog(window.GameStat);
		if (System.currentTimeMillis() > Menu.KeyPause) {
			if (window.GameStat.equals("Game")) {											// IN THE GAME
				Walrii.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
					Walrii.go("left");
				}
				if(keyDown[38]==true) {
					Walrii.go("up");
				}
				if(keyDown[39]==true) {
					Walrii.go("right");
				}
				if(keyDown[40]==true) {
					Walrii.go("down");
				}
				if(keyDown[27]==true) {
					Map.MapToResume = Walrii.getCurrentMapName();
					Walrii.setXPosToResume(Walrii.xPos);
					Walrii.setYPosToResume(Walrii.yPos);
					Menu.RunMenu();
				}
				if(keyDown[65]==true) {
		    		Walrii.Interact();			// Cut tree, open chest, talk, ...
				}
				if(keyDown[73]==true) {
					if(General.showDebug) {
						General.DebugLog("x="+Walrii.getXPos()+" y="+Walrii.getYPos());
					}
					TileArea.drawInfo();
				}
				if(keyDown[79]==true) {
					Screen.forceUpdateNextTime = true;		// Force full rendering
					Screen.clearScreenMatrixOverlay();		// Remove overlay artefacts
					if(General.showDebug) {	General.DebugLog("Forced screen update"); }
				}

			} else if (window.GameStat.equals("Menu")) {									// IN THE MAIN MENU
				Walrii.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("left");
				}
				if(keyDown[38]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("up");
				}
				if(keyDown[39]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("right");
				}
				if(keyDown[40]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("down");
				}
				if(keyDown[73]==true) {
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Paused")) {									// GAME IS PAUSED
				Walrii.TileChangeWhileWalking = 0;
				if(keyDown[37]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("left");
				}
				if(keyDown[38]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("up");
				}
				if(keyDown[39]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("right");
				}
				if(keyDown[40]==true) {
					Menu.KeyPause = System.currentTimeMillis()+Player.StepDuration;
					Walrii.go("down");
				}
				if(keyDown[73]==true) {
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Intro")) {									// ASKING TO START THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					Menu.KeyPause = System.currentTimeMillis()+1000;
					if(General.showTeleport || General.showClasses) {
						General.DebugLog("keys.intro -> load main");
					}
					// goes to Intro.IntroEnd();	(walk out of forest)
				}
				if(keyDown[32]==true) {
					if(General.showTeleport || General.showClasses) {
						General.DebugLog("keys.intro -> continue Intro");
					}
					window.GameStat = "ContinueIntro";
					Intro.continueIntro();
				}
			} else if (window.GameStat.equals("ContinueIntro")) {									// INSIDE THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					if(General.showTeleport || General.showClasses) {
						General.DebugLog("keys.continue intro -> load main");
					}
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