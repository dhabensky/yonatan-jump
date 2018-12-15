package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.aahack.yojump.JumpListener;
import com.aahack.yojump.util.AnimationFrame;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player extends GameObject implements JumpListener {

	public List<AnimationFrame> frames;
	private long frameStart = 0;
	private int frameIndex = -1;

	public Drawable drawable;
	public int w;
	public int h;
	public float gravity = 5f;
	public float runSpeed = 3f;

	@Override
	public void render(@NotNull Canvas canvas) {

		switchFrames();

		int l = (int) (getPos().x - w / 2);
		int t = (int) (getPos().y - h / 2);

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
	public void onJump() {
		getVelocity().y = -40;
	}

}