package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.aahack.yojump.util.AnimationFrame;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player extends GameObject {

	public List<AnimationFrame> frames;
	private long frameStart = 0;
	private int frameIndex = -1;

	public Drawable drawable;
	public int w;
	public int h;
	private boolean jumping = false;
	public int jumpVelocity;

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isJumping() {
		return jumping;
	}

	@Override
	public void render(@NotNull Canvas canvas) {

		switchFrames();

		int l = (int) (getPos().x);
		int t = (int) (getPos().y);

		drawable.setBounds(l, t, l + w, t + h);
		drawable.draw(canvas);
	}

	private void switchFrames() {
		if (frameIndex == -1) {
			frameIndex = 0;
			drawable = frames.get(0).getDrawable();
			return;
		}

		long now = System.currentTimeMillis();
		if (frameStart == 0) {
			frameStart = now;
		}
		if (now - frameStart > frames.get(frameIndex).getDuration()) {
			frameIndex = (frameIndex + 1) % frames.size();
			drawable = frames.get(frameIndex).getDrawable();
			frameStart = now;
		}
	}

	@Override
	public void getBounds(@NotNull RectF outRect) {
		float l = getPos().x;
		float t = getPos().y;
		outRect.set(l, t, l + w, t + h);
	}

	public void jump() {
		if (!jumping) {
			jumping = true;
			getVelocity().y = -jumpVelocity;
		}
	}

}
