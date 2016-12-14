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
		System.out.println(window.GameStat);
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
					Menu.Menu();
				}
				if(keyDown[73]==true) {
					TileArea.drawInfo();
				}
			} else if (window.GameStat.equals("Menu")) {									// IN THE MAIN MENU
				if(keyDown[10]==true) {
		    		if (Menu.SelectedID==0) {		// New Game
		    			window.Start();
		    		}
		    		if (Menu.SelectedID==1) {		// Continue
		    			// NOt possible here
		    		}
		    		if (Menu.SelectedID==2) {		// Save
		    			
		    		}
		    		if (Menu.SelectedID==3) {		// Settings
		    			
		    		}
		    		if (Menu.SelectedID==4) {		// Credits
		    			
		    		}
		    		if (Menu.SelectedID==5) {		// Exit
		    			System.exit(0);
		    		}
				}
				if(keyDown[38]==true) {
		    		Menu.SelectedID = General.getBetween(0,  Menu.SelectedID-1, 5);
		    		Menu.KeyPause = System.currentTimeMillis()+150;
				}
				if(keyDown[40]==true) {
		    		Menu.SelectedID = General.getBetween(0,  Menu.SelectedID+1, 5);
		    		Menu.KeyPause = System.currentTimeMillis()+150;
				}
			} else if (window.GameStat.equals("Paused")) {									// GAME IS PAUSED
				if(keyDown[27]==true) {
					Menu.KeyPause = System.currentTimeMillis()+150;
					Screen.render(true);
					window.Resume();
				}
				if(keyDown[10]==true) {
		    		if (Menu.SelectedID==0) {		// New Game
		    			window.Start();
		    		}
		    		if (Menu.SelectedID==1) {		// Continue
		    			Screen.render(true);
		    			window.Resume();
		    		}
		    		if (Menu.SelectedID==2) {		// Save
		    			
		    		}
		    		if (Menu.SelectedID==3) {		// Settings
		    			
		    		}
		    		if (Menu.SelectedID==4) {		// Credits
		    			
		    		}
		    		if (Menu.SelectedID==5) {		// Exit
		    			System.exit(0);
		    		}
				}
				if(keyDown[38]==true) {
		    		Menu.SelectedID = General.getBetween(0,  Menu.SelectedID-1, 5);
		    		Menu.KeyPause = System.currentTimeMillis()+150;
				}
				if(keyDown[40]==true) {
		    		Menu.SelectedID = General.getBetween(0,  Menu.SelectedID+1, 5);
		    		Menu.KeyPause = System.currentTimeMillis()+150;
				}
			} else if (window.GameStat.equals("Intro")) {									// ASKING TO START THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					Menu.KeyPause = System.currentTimeMillis()+1000;
					System.out.println("intro -> load main");
					Intro.loadMainGame();
				}
				if(keyDown[32]==true) {
					System.out.println("intro -> continue Intro");
					Intro.continueIntro();
				}
			} else if (window.GameStat.equals("SilentIntro")) {									// INSIDE THE GAME INTRO (DIALOGUES)
				if(keyDown[27]==true) {
					System.out.println("silent intro -> load main");
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