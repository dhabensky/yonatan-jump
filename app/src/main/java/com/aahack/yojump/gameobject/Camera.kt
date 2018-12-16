package com.aahack.yojump.gameobject

import android.graphics.Matrix
import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Camera : GameObject() {

	private val matrix = Matrix()
	var scale = 1f

	fun getMatrix(): Matrix {
		matrix.reset()
		matrix.preScale(scale, scale)
		matrix.preTranslate(-pos.x, -pos.y)
		return matrix
	}

	override fun getBounds(outRect: RectF) {

	}

}
