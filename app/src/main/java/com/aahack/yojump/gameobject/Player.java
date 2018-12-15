package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.aahack.yojump.JumpListener;

import org.jetbrains.annotations.NotNull;

public class Player extends GameObject implements JumpListener {

	public Drawable drawable;
	public RectF bounds = new RectF();
	public float gravity = 5f;
	public float runSpeed = 3f;

	private boolean animating = false;

	public void setBounds(RectF rectF) {
		bounds.set(rectF);
	}

	@Override
	public void render(@NotNull Canvas canvas) {
		super.render(canvas);

		drawable.setBounds(
				(int) bounds.left,
				(int) bounds.top,
				(int) bounds.right,
				(int) bounds.bottom
		);
		drawable.draw(canvas);

		if (!animating) {
			((Animatable) drawable).start();
			animating = false;
		}

		// apply velocity
		getPos().y += getVelocity().y;
		getPos().x += getVelocity().x;

		// apply acceleration
		getVelocity().y += gravity;
	}


	@Override
	public void onJump() {
		getVelocity().y = -40;
	}

}
