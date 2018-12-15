package com.aahack.yojump.gameobject

import android.graphics.Canvas

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Scene {

	private val objects = arrayListOf<GameObject>()
	private lateinit var camera: Camera

	private var lastFrameMillis: Long = 0L

	fun render(canvas: Canvas) {

		val curMillis = System.currentTimeMillis()
		if (lastFrameMillis == 0L) {
			lastFrameMillis = curMillis
		}
		val delta = (curMillis - lastFrameMillis) / 1000f


		for (obj in objects) {
			obj.update(delta)
		}

		canvas.matrix = camera.getMatrix()

		for (obj in objects) {
			obj.render(canvas)
		}

	}

	fun addObject(gameObject: GameObject) {
		objects.add(gameObject)
	}

	fun setCamera(camera: Camera) {
		addObject(camera)
		this.camera = camera
	}

}
