package com.toashel.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import com.toashel.pong.game.Game;

public class Ball {
	private int x;
	private int y;
	private int xVelocity;
	private int yVelocity;
	private int radius;
	
	private Game game;

	public Ball(int x, int y, int radius, Game game) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.game = game;
		this.xVelocity = -2;
		this.yVelocity = -2;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);		
	}
	
	public void update() {	
		
		if (yBoundaryCollision()) {
			reverseYVelocity();
		}
		
		if (player1Score()) {
			game.setPlayerScore(game.getPlayerScore() + 1);
			resetPosition();
			reverseXVelocity();
		}
		
		if (player2Score()) {
			game.setOpponentScore(game.getOpponentScore() + 1);
			resetPosition();
			reverseXVelocity();
		}
		
		if (game.getPlayerScore() == 10 || game.getOpponentScore() == 10) {
			victoryCondition();
		}
		
		if (paddleCollision() || aiPaddleCollision()) {
			reverseXVelocity();
		}
		
		updateLocation();
		
		
	}
	
	public void updateLocation() {
		y += yVelocity;
		x += xVelocity;
	}
	
	public void resetPosition() {
		x = game.getWidth()  / 2;
		y = game.getHeight() / 2;
	}
	
	public boolean yBoundaryCollision() {
		return y < 0 || y > game.getHeight();
	}
	
	public boolean paddleCollision() {
		return getBallBounds().intersects(game.paddle.getPaddleBounds());
	}
	
	public boolean aiPaddleCollision() {
		return getBallBounds().intersects(game.aiPaddle.getPaddleBounds());
	}

	public boolean player1Score() {
		return x > game.getWidth();
	}
	
	public boolean player2Score() {
		return x < 0;
	}
	
	public void victoryCondition() {
		if (game.getPlayerScore() >= 10) {
			JOptionPane.showMessageDialog(null, "Player 1 Wins", "Pong", JOptionPane.PLAIN_MESSAGE);
		}
		
		if (game.getOpponentScore() >= 10) {
			JOptionPane.showMessageDialog(null, "Player 2 Wins", "Pong", JOptionPane.PLAIN_MESSAGE);
		}
		
		game.resetGame();
		game.startGame(false);
	}
	
	public void reverseXVelocity() {
		xVelocity *= -1;
	}
	
	public void reverseYVelocity() {
		yVelocity *= -1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getXVelocity() {
		return xVelocity;
	}
	
	public int getY() {
		return y;
	}
	
	public void setBallVelocity(int velocity) {
		xVelocity = -1 * velocity;
		yVelocity = -1 * velocity;
	}
	
	public Rectangle getBallBounds() {
		return new Rectangle(x, y, radius * 2, radius * 2);
	}
	
	
	
}
