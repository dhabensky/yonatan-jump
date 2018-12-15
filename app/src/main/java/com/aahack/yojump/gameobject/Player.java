package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.aahack.yojump.JumpListener;

import org.jetbrains.annotations.NotNull;

public class Player extends GameObject implements JumpListener {

	public Drawable drawable;
	public int w;
	public int h;
	public float gravity = 5f;
	public float runSpeed = 3f;
	private boolean animating = false;

	@Override
	public void render(@NotNull Canvas canvas) {

		int l = (int) (getPos().x - w / 2);
		int t = (int) (getPos().y - h / 2);

		drawable.setBounds(l, t, l + w, t + h);
		drawable.draw(canvas);

//		getVelocity().y += gravity;
	}


	@Override
	public void onJump() {
		getVelocity().y = -40;
	}

}
