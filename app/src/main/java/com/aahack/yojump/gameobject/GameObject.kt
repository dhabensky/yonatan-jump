package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.PointF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
open class GameObject {

	var pos = PointF()
	var velocity = PointF()
	var acceleration = PointF()

	fun update(delta: Float) {
		pos.x += velocity.x * delta
		pos.y += velocity.y * delta

		velocity.x += acceleration.x * delta
		velocity.y += acceleration.y * delta
	}

	open fun render(canvas: Canvas) {

	}

}
