package com.aahack.yojump.gameobject

import android.graphics.Matrix
import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Camera : GameObject() {

	private val matrix = Matrix()

	fun getMatrix(): Matrix {
		matrix.setTranslate(-pos.x, -pos.y)
		return matrix
	}

	override fun getBounds(outRect: RectF) {

	}

}
