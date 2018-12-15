package com.aahack.yojump

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.aahack.yojump.gameobject.Scene

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class GameView : View {

	lateinit var scene: Scene

	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)

		scene.render(canvas)

		invalidate()
	}

}
