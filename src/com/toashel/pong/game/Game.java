package com.toashel.pong.game; 

import com.toashel.pong.objects.Ball;
import com.toashel.pong.objects.Paddle;
import com.toashel.pong.objects.AIPaddle;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
		
	private Thread gameThread;
	private boolean running = false;
	private boolean started = false;
	private boolean options = false;

	private int playerScore = 0;
	private int opponentScore = 0;
		
	private Ball ball;
	public Paddle paddle;
	public Paddle aiPaddle;
	
	JTextField message = new JTextField();
		
	public Game() {	
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);	

		ball = new Ball((width * scale) / 2, (height * scale) / 2, 5, this);
		paddle = new Paddle(10, 2, height * scale);
		aiPaddle = new AIPaddle((width * scale) - 20, 1, height * scale, ball);
		addKeyListener(new KeyboardInput(this));
	}
	
	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Pong");
		gameThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (running) {
			update(); // handles logic
			render(); // renders logic to canvas
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (started) {
			ball.update();
			paddle.update();
			aiPaddle.update();
		}
	}
	
	public void render() {
		// buffer - temporary storage 
		BufferStrategy buffer = getBufferStrategy(); 
		if (buffer == null) {
			createBufferStrategy(3); // triple buffering -- point of diminishing returns
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		
		if (!options && !started) {
			
			// Background
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			// Text
			
			g.setColor(Color.WHITE);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 165, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 'Space' to play.", 175, 400);
            g.drawString("Press 'P' for Options.", 175, 450);

		}
		
		else if (options) {
			JOptionPane.showConfirmDialog(null, message, "Message", JOptionPane.OK_CANCEL_OPTION);
			setOptions(false);
		}
		
		else if (started) {
			// Background
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			// Line
			
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g.create();
	        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	        g2d.setStroke(dashed);
	        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g2d.dispose();
			
			// Scores 
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Munro", Font.TRUETYPE_FONT, 36));
			g.drawString("" + playerScore, (getWidth() / 2) - 50, 40);
			g.drawString("" + opponentScore, (getWidth() / 2) + 33, 40);
			
			// Draw Objects
			
			ball.draw(g);
			paddle.draw(g);
			aiPaddle.draw(g);
			

		}
		
		g.dispose(); // remove graphics from each frame
		buffer.show(); // show next available buffer
		
	}

	public int getPlayerScore() {
		return playerScore;
	}
	
	public void setPlayerScore(int score) {
		playerScore = score;
	}
	
	public int getOpponentScore() {
		return opponentScore;
	}
	
	public void setOpponentScore(int score) {
		opponentScore = score;
	}
	
	public void startGame(boolean x) {
		started = x;
	}
	
	public void setOptions(boolean x) {
		options = x;
	}
	
	public void resetGame() {
		setPlayerScore(0);
		setOpponentScore(0);
		ball.resetPosition();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
