package com.toashel.pong.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	
	private Game game;

	public KeyboardInput(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			game.paddle.setUp(true);
			break;
			
		case KeyEvent.VK_DOWN:
			game.paddle.setDown(true);
			break;
			
		case KeyEvent.VK_SPACE:
			game.startGame(true);
			break;
			
		case KeyEvent.VK_P:
			game.setOptions(true);
			break;
		
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			game.paddle.setUp(false);
			break;
			
		case KeyEvent.VK_DOWN:
			game.paddle.setDown(false);
			break;
			
		default:
			break; 
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
