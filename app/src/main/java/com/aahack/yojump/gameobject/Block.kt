package com.aahack.yojump.gameobject

import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Block : SpriteObject() {

	override fun getBounds(outRect: RectF) {
		val l = pos.x
		val t = pos.y
		outRect.set(l, t, l + w, t + h)
	}

}
