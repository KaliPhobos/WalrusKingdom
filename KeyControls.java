package CodeW;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class KeyControls {
	public static boolean keyDown[];
	public static void prepare(Canvas _canvas) {
        _canvas.setSize(window.width, window.height);
		_canvas.setIgnoreRepaint(true);
		_canvas.createBufferStrategy(2);
        keyDown = new boolean[65536]; //This list will hold the position of keys (up/down)
		_canvas.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                keyDown[ke.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                keyDown[ke.getKeyCode()] = false;
            }
        });
	}
	public static void checkInput() {
		for(int _x=0;_x<65536;_x++) {
        	if(keyDown[_x]==true) {
        		System.out.println(_x+" pressed");
        	}
        }
		if(keyDown[37]==true) {
    		Player.move("up");
    		System.out.println("LEFT#########################################################");
    	}
	}
}