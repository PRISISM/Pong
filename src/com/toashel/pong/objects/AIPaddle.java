package com.toashel.pong.objects;

public class AIPaddle extends Paddle {
	
	private Ball ball;

	public AIPaddle(int x, int yVelocity, int maxHeight, Ball ball) {
		super(x, yVelocity, maxHeight);
		this.ball = ball;
	}
	
	@Override 
	public void update(){
		
		if (ball.getXVelocity() > 0) {
			if (ball.getY() <= y + (HEIGHT/ 2)) {
				y-= yVelocity;
			}
			
			if (ball.getY() >= y + (HEIGHT/ 2)) {
				y+= yVelocity;
			}
			
		}
		
		yBoundaryCollision();
		
				
	}

}
