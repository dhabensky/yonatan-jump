package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class ScoreLabel(
		private val player: Player
) : GameObject() {

	private val paint: Paint

	private var text: String? = null
	private var lastScore: Int = -1

	init {
		paint = Paint(Paint.ANTI_ALIAS_FLAG)
		paint.color = Color.RED
		paint.textSize = 24 * 2f
	}

	override fun render(canvas: Canvas) {

		canvas.matrix.reset()
		canvas.matrix = canvas.matrix

		val t = when (player.score == lastScore) {
			true -> text!!
			false -> "score: ${player.score}"
		}
		text = t

		canvas.drawText(text, pos.x, pos.y, paint)
	}

	override fun getBounds(outRect: RectF) {

	}

}
