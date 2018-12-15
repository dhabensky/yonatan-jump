package com.aahack.yojump

import android.graphics.Canvas

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Scene {

	private val objects = arrayListOf<GameObject>()

	fun render(canvas: Canvas) {

		for (obj in objects) {
			obj.render(canvas)
		}

	}

}
