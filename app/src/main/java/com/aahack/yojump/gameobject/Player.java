package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import com.aahack.yojump.util.AnimationFrame;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player extends GameObject {

	public MediaPlayer jumpSound;
	public MediaPlayer collectSound;
	public List<AnimationFrame> frames;
	private long frameStart = 0;
	private int frameIndex = -1;

	public Drawable drawable;
	public int w;
	public int h;
	private int jumpCount = 0;
	private int maxJumps = 2;
	public int jumpVelocity;

	private boolean colliding = false;

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

	public boolean isColliding() {
		return colliding;
	}

	public void resetJumpCount() {
		this.jumpCount = 0;
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
		float w = this.w * 0.6f;
		float h = this.h * 0.9f;
		float horOffset = (this.w - w) / 2;
		float vertOffset = (this.h - h) / 2;
		float l = getPos().x + horOffset;
		float t = getPos().y + vertOffset;
		outRect.set(l, t, l + w, t + h);
	}

	public void jump() {
		if (jumpCount < maxJumps) {
			jumpCount ++;
			getVelocity().y = -jumpVelocity;
			if (jumpSound != null) {
				jumpSound.start();
			}
		}
	}

	public void collect() {
		if (collectSound != null) {
			collectSound.start();
		}
	}

}
