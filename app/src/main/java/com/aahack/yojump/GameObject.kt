package com.aahack.yojump

import android.graphics.Canvas
import android.graphics.PointF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
open class GameObject {

	var speed = PointF()
	var pos = PointF()

	open fun render(canvas: Canvas) {

	}

}
