package com.distraction.cm.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.CM;
import com.distraction.cm.util.Res;

public class CheckeredTransitionState extends TransitionState {
	
	private TextureRegion pixel;
	private int size;
	private int numRows;
	private int numCols;
	private float expandSpeed = 150;
	private float delay = 3;
	private int finishCount;
	
	private static final int EXPANDING = 0;
	private static final int DONE_EXPANDING = 1;
	private static final int COLLAPSING = 2;
	private static final int DONE_COLLAPSING = 3;
	private int status = EXPANDING;
	
	private float[][] sizes;
	
	public CheckeredTransitionState(GSM gsm, State prev, State next) {
		
		super(gsm, prev, next);
		
		pixel = Res.getAtlas().findRegion("pixel");
		
		size = 50;
		numRows = CM.HEIGHT / size + 1;
		numCols = CM.WIDTH / size + 1;
		sizes = new float[numRows][numCols];
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				sizes[row][col] = ((numRows - row) + col) * -delay;
			}
		}
		
	}
	
	public void setDelay(float f) {
		delay = f;
	}
	
	public void setExpandSpeed(float f) {
		expandSpeed = f;
	}
	
	@Override
	public void update(float dt) {
		
		finishCount = 0;
		
		if(status == EXPANDING) {
			for(int row = 0; row < numRows; row++) {
				for(int col = 0; col < numCols; col++) {
					sizes[row][col] += expandSpeed * dt;
					if(sizes[row][col] >= size) {
						finishCount++;
						sizes[row][col] = size;
					}
				}
			}
			if(finishCount == numRows * numCols) {
				status = DONE_EXPANDING;
				for(int row = 0; row < numRows; row++) {
					for(int col = 0; col < numCols; col++) {
						sizes[row][col] = ((numRows - row) + col) * delay + size;
					}
				}
				status = COLLAPSING;
			}
		}
		
		finishCount = 0;
		
		if(status == COLLAPSING) {
			for(int row = 0; row < numRows; row++) {
				for(int col = 0; col < numCols; col++) {
					sizes[row][col] -= expandSpeed * dt;
					if(sizes[row][col] <= 0) {
						finishCount++;
						sizes[row][col] = 0;
					}
				}
			}
			
			if(finishCount == numRows * numCols) {
				status = DONE_COLLAPSING;
				finish();
			}
		}
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		if(status == EXPANDING) {
			prevState.render(sb);
		}
		else if(status == COLLAPSING) {
			nextState.render(sb);
		}
		
		sb.setProjectionMatrix(cam.combined);
		sb.setColor(0, 0, 0, 1);
		sb.begin();
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(sizes[row][col] < 0) {
					continue;
				}
				sb.draw(pixel,
						col * size + size / 2 - sizes[row][col] / 2,
						row * size + size / 2 - sizes[row][col] / 2,
						sizes[row][col],
						sizes[row][col]);
			}
		}
		sb.end();
	}
	
}
