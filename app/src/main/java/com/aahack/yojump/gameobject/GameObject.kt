package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
open class GameObject {

	val pos = PointF()
	val velocity = PointF()
	val acceleration = PointF()
	var tag: String? = null

	open fun update(delta: Float) {
		pos.x += velocity.x * delta
		pos.y += velocity.y * delta

		velocity.x += acceleration.x * delta
		velocity.y += acceleration.y * delta
	}

	open fun render(canvas: Canvas) {

	}

	open fun getBounds(outRect: RectF) {
		throw NotImplementedError("not implemented in class ${javaClass.simpleName}")
	}

}
