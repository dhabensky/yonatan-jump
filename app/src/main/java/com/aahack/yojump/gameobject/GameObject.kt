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

	open fun render(canvas: Canvas) {

	}

}
