package com.toashel.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	
	protected static final int WIDTH = 10, HEIGHT = 60; 
	
	protected int x;
	protected int y;
	protected int yVelocity;
	protected int maxHeight;
	
	private boolean up = false;
	private boolean down = false;

	public Paddle(int x, int yVelocity, int maxHeight) {
		this.x = x;
		y = maxHeight / 2;
		this.yVelocity = yVelocity;
		this.maxHeight = maxHeight;
	}
	
	public void update(){
		if (up) {
			moveUp();
		}
		if (down) {
			moveDown();
		}
		
		yBoundaryCollision();
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y, WIDTH, HEIGHT);
	}
	
	public void setVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	public void moveUp() {
		y -= yVelocity;
	}
	
	public void moveDown() {
		y += yVelocity;
	}
	
	public void yBoundaryCollision() {
		if (y <= 0) {
			y = 0;
		}
		else if (y + HEIGHT >= maxHeight) {
			y = maxHeight - HEIGHT;
		}
	}
	
	public void setUp(boolean x) {
		up = x;
	}
	
	public void setDown(boolean x) {
		down = x;
	}
	
	public Rectangle getPaddleBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

}
