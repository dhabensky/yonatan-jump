package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.drawable.Drawable

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
open class SpriteObject : GameObject() {

	var drawable: Drawable? = null
	var w: Int = 0
	var h: Int = 0

	override fun render(canvas: Canvas) {
		super.render(canvas)
		drawable?.let {
			val l = (pos.x).toInt()
			val t = (pos.y).toInt()
			it.setBounds(l, t, l + w, t + h)
			it.draw(canvas)
		}
	}

	override fun getBounds(outRect: RectF) {
		val l = pos.x
		val t = pos.y
		outRect.set(l, t, l + w, t + h)
	}

}
