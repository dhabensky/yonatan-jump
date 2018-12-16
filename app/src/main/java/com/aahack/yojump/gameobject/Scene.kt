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
	private lateinit var score: ScoreLabel

	private var lastFrameMillis: Long = 0L

	private val playerBounds = RectF()
	private val playerBoundsCopy = RectF()
	private val bounds = RectF()

	public var gameOver = false

	private val objToRemove = arrayListOf<GameObject>()

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

		processCollisions(delta)

		canvas.matrix = camera.getMatrix()

		for (obj in objects) {
			obj.render(canvas)
		}

		for (obj in objToRemove) {
			objects.remove(obj)
		}
		objToRemove.clear()

		score.render(canvas)
	}

	private fun processCollisions(delta: Float) {

		player.getBounds(playerBounds)
		if (player.velocity.y > 0) {
			bounds.top -= player.velocity.y * delta
		}

		var colliding = false

		for (obj in objects) {
			playerBoundsCopy.set(playerBounds)
			obj.getBounds(bounds)

			if (playerBoundsCopy.intersect(bounds)) {
				if (obj.tag == "block") {
					processBlockCollision(obj, playerBoundsCopy)
					colliding = true
				}
				else if (obj.tag == "collectable") {
					processCollectableCollision(obj)
				}
				else if (obj.tag == "death") {
					processDeathCollision(obj)
				}
			}
		}

		player.isColliding = colliding
	}

	private fun processBlockCollision(obj: GameObject, collision: RectF) {

		if (player.velocity.y < 0) {
			return
		}

		if (collision.bottom != playerBounds.bottom) {
			return
		}

		val threshold = 16f
		val nearTop = collision.height() < threshold

		val newCollision = nearTop || !player.isColliding

		if (newCollision) {
			player.pos.y = bounds.top - player.h
			player.velocity.y = 0f
			player.resetJumpCount()
		}
	}

	private fun processCollectableCollision(obj: GameObject) {
		player.collect()
		objToRemove.add(obj)
	}

	private fun processDeathCollision(obj: GameObject) {
		gameOver = true
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

	fun setScore(score: ScoreLabel) {
		addObject(score)
		this.score = score
	}

}
