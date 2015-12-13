package com.toashel.pong.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.toashel.pong.game.Game;

public class Window {
	
	JPanel options = new JPanel();
	SpinnerModel optionsSpinner = new SpinnerNumberModel(1, 1, 10, 1);
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.setResizable(false); 
		frame.setTitle("Pong");
		frame.add(game); 
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
		
		game.start();
	}

}
