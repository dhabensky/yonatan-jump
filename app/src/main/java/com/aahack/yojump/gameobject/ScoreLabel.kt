package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface



/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class ScoreLabel(
		private val player: Player,
		private val typeface: Typeface
) : GameObject() {

	private val paint: Paint

	private var text: String? = null
	private var lastScore: Int = -1

	init {
		paint = Paint(Paint.ANTI_ALIAS_FLAG)
		paint.color = Color.RED
		paint.textSize = 76f
		paint.typeface = typeface
	}

	override fun render(canvas: Canvas) {

		canvas.matrix.reset()
		canvas.matrix = canvas.matrix

		val t = when (player.score == lastScore) {
			true -> text!!
			false -> "${player.score}"
		}
		text = t

		canvas.drawText(text, pos.x, pos.y, paint)
	}

	override fun getBounds(outRect: RectF) {

	}

	fun getScore(): String? {
		return text
	}

}
