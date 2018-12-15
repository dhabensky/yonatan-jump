package com.aahack.yojump.gameobject

import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Block : SpriteObject() {

	override fun getBounds(outRect: RectF) {
		val l = (pos.x - w / 2)
		val t = (pos.y - h / 2)
		outRect.set(l, t, l + w, t + h)
	}

}
