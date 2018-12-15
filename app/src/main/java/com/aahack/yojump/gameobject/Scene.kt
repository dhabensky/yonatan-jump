package com.aahack.yojump.gameobject

import android.graphics.Canvas
import android.graphics.RectF

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Scene {

	private val objects = arrayListOf<GameObject>()
	private lateinit var camera: Camera
	private lateinit var player: Player

	private var lastFrameMillis: Long = 0L

	private val playerBounds = RectF()
	private val playerBoundsCopy = RectF()
	private val bounds = RectF()

	fun render(canvas: Canvas) {

		val curMillis = System.currentTimeMillis()
		if (lastFrameMillis == 0L) {
			lastFrameMillis = curMillis
		}
		val delta = (curMillis - lastFrameMillis) / 1000f
		lastFrameMillis = curMillis


		for (obj in objects) {
			obj.update(delta)
		}

		processBlockCollisions()

		canvas.matrix = camera.getMatrix()

		for (obj in objects) {
			obj.render(canvas)
		}

	}

	private fun processBlockCollisions() {

		val threshold = 16f
		player.getBounds(playerBounds)

		for (obj in objects) {
			if (obj.tag != "block") continue

			playerBoundsCopy.set(playerBounds)
			obj.getBounds(bounds)

			if (playerBoundsCopy.intersect(bounds)) {
				val inside = playerBounds.bottom - bounds.top
				if (player.velocity.y > 0 && inside < threshold) {
					player.pos.y = bounds.top - player.h / 2
					player.velocity.y = 0f
				}
			}
		}
	}

	fun addObject(gameObject: GameObject) {
		objects.add(gameObject)
	}

	fun setCamera(camera: Camera) {
		addObject(camera)
		this.camera = camera
	}

	fun setPlayer(player: Player) {
		addObject(player)
		this.player = player
	}

}
