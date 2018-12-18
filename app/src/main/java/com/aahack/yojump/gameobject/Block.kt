package com.aahack.yojump.gameobject

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class Block(
		private val scene: Scene
) : SpriteObject() {

	init {
		tag = "block"
	}

	private var destructTime = 0L
	var destructible = false

	fun scheduleDestruct() {
		if (destructTime == 0L) {
			destructTime = System.currentTimeMillis() + 400
		}
	}

	override fun update(delta: Float) {
		super.update(delta)
		if ((destructTime > 0) && (System.currentTimeMillis() > destructTime)) {
			scene.delayedRemoveObject(this)
		}
	}

}
