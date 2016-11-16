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
	}
}