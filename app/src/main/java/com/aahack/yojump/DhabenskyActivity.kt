package com.aahack.yojump

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Display
import android.view.Window
import android.view.WindowManager
import com.aahack.yojump.gameobject.*
import com.aahack.yojump.gameobject.Block
import com.aahack.yojump.gameobject.Camera
import com.aahack.yojump.gameobject.Player
import com.aahack.yojump.gameobject.Scene
import com.aahack.yojump.input.PlayerController
import com.aahack.yojump.util.AnimationFrame
import kotlinx.android.synthetic.main.dhabensky_activity.*

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class DhabenskyActivity : AppCompatActivity() {

	private var scene = Scene()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		requestWindowFeature(Window.FEATURE_NO_TITLE)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.dhabensky_activity)

		val display:Display = windowManager.defaultDisplay
		val point  = Point()
		display.getSize(point)

		gameView.scene = scene

		val player = createPlayer()
		val camera = createCamera()
		val block = createBlock()
		val background = createBackground(point)


		scene.addObject(background)
		scene.setPlayer(player)
		scene.setCamera(camera)
		scene.addObject(block)

		val gen = GenerateBlock()
		for (i in 0..30) {
			val b = gen.createBlock(1800)
			scene.addObject(b)
		}

		val controller = PlayerController()
		controller.player = player
		gameView.setOnClickListener(controller)

		scene.addObject(createDeathCollider())
		scene.addObject(DeathListener())
	}

	private fun createPlayerFrames(): List<AnimationFrame> {
		return arrayListOf(
				AnimationFrame(getDrawable(R.drawable.ic_yonatan_left), 200),
				AnimationFrame(getDrawable(R.drawable.ic_yonatan_mid), 100),
				AnimationFrame(getDrawable(R.drawable.ic_yonatan_right), 200),
				AnimationFrame(getDrawable(R.drawable.ic_yonatan_mid), 100)
		)
	}

	private fun createPlayer(): Player {
		val player = Player()
		player.jumpVelocity = 1000
		player.frames = createPlayerFrames()
		player.pos.set(100f, 400f)
		player.acceleration.y = 2000f
		player.w = 200
		player.h = 200
		player.velocity.set(600f, 0f)
		player.jumpSound = MediaPlayer.create(this, R.raw.jump)
		player.jumpSound.setVolume(1f, 1f)
		return player
	}

	private fun createCamera(): Camera {
		val camera = Camera()
		camera.velocity.x = 600f
		return camera
	}

	private fun createBlock(): Block {
		val block = Block()
		block.drawable = ColorDrawable(Color.BLACK)
		block.w = 1800
		block.h = 20
		block.pos.set(0f, 600f)
		block.tag = "block"
		return block
	}

	private fun  createBackground(point: Point): Background{
		val background = Background(point.x, point.y)

		background.drawable = resources.getDrawable(R.drawable.ic_back_1)
		background.w = point.x
		background.h = point.y /2
		background.pos.set(0f, (point.y- background.h).toFloat())
		background.velocity.set(600f,0f)
		return background
	}

	private fun createDeathCollider(): GameObject {
		val obj = Death()
		obj.pos.set(0f, 900f)
		obj.w = 10000
		obj.h = 100
		obj.velocity.x = 600f
		return obj
	}

	private inner class DeathListener : GameObject() {
		private var fired = false
		override fun update(delta: Float) {
			super.update(delta)
			if (scene.gameOver) {
				if (!fired) {
					fired = true
					Handler().post {
						startActivity(Intent(this@DhabenskyActivity, GameOverActivity::class.java))
					}
				}
			}
		}

		override fun getBounds(outRect: RectF) {

		}
	}

}
