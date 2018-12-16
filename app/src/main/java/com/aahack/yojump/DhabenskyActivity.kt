package com.aahack.yojump

import android.graphics.Color
import android.graphics.Point
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Display
import android.view.Window
import android.view.WindowManager
import com.aahack.yojump.gameobject.*
import com.aahack.yojump.input.PlayerController
import com.aahack.yojump.util.AnimationFrame
import kotlinx.android.synthetic.main.dhabensky_activity.*
import java.util.*

/**
 * Created on 15.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class DhabenskyActivity : AppCompatActivity() {

	private var scene = Scene()

	companion object {
		const val WIDTH = 1920f
		const val HEIGHT = 1080f
	}

	private lateinit var backgroundMusic: MediaPlayer

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		requestWindowFeature(Window.FEATURE_NO_TITLE)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.dhabensky_activity)

		val display: Display = windowManager.defaultDisplay
		val screenSize = Point()
		display.getSize(screenSize)

		gameView.scene = scene

		val player = createPlayer()
		val camera = createCamera(screenSize)
		val background = createBackground(screenSize)


		scene.addObject(background)
		scene.setPlayer(player)
		scene.setCamera(camera)

		val blocks = createBlocks()
		val rand = Random()
		for (b in blocks) {
			scene.addObject(b)
			if (rand.nextInt(10) < 1) {
				scene.addObject(createCollectable(
						rand.nextInt(drawables.ids.size),
						b.pos.x + b.w / 2,
						b.pos.y - b.h
				))
			}
		}

		val controller = PlayerController()
		controller.player = player
		gameView.setOnClickListener(controller)

		scene.addObject(createDeathCollider())
		scene.addObject(DeathListener())


		val tf = Typeface.createFromAsset(assets, "font/komika-title.otf")
		val score = ScoreLabel(player, tf).apply {
			velocity.x = 600f;
			pos.set(1200f, 100f)
		}
		scene.setScore(score)

		backgroundMusic = MediaPlayer.create(this, R.raw.background)
		backgroundMusic.setVolume(0.5f, 0.5f)
		backgroundMusic.isLooping = true
		backgroundMusic.start()
	}

	override fun onPause() {
		super.onPause()
		backgroundMusic.pause()
	}

	override fun onResume() {
		super.onResume()
		scene.lastFrameMillis = System.currentTimeMillis()
		backgroundMusic.start()
	}

	override fun onDestroy() {
		super.onDestroy()
		backgroundMusic.release()
	}


	private fun createBlocks(): List<SpriteObject> {
		val blocks = arrayListOf<SpriteObject>()
		val gen = GenerateBlock()
		for (i in 0..30) {
			val b = gen.createBlock()
			blocks.add(b)
		}
		return blocks
	}

	private fun createCollectable(index: Int, x: Float, y: Float): GameObject {
		val obj = CollectableObject()
		obj.drawable = resources.getDrawable(drawables.ids[index])
		obj.w = 150
		obj.h = 180
		obj.pos.set(x - obj.w / 2, y - obj.h / 2)
		return obj
	}

	object drawables {
		val ids = arrayOf(
				R.drawable.z_1,
				R.drawable.z_2,
				R.drawable.z_3,
				R.drawable.z_4,
				R.drawable.z_5,
				R.drawable.z_6,
				R.drawable.z_7,
				R.drawable.z_8,
				R.drawable.z_9,
				R.drawable.z_10,
				R.drawable.z_11,
				R.drawable.z_12,
				R.drawable.z_13,
				R.drawable.z_14,
				R.drawable.z_15,
				R.drawable.z_16,
				R.drawable.z_17,
				R.drawable.z_18
		)
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
		player.pos.set(100f, 300f)
		player.acceleration.y = 2000f
		player.w = 200
		player.h = 200
		player.velocity.set(600f, 0f)
//		player.jumpSound = MediaPlayer.create(this, R.raw.jump)
//		player.jumpSound.setVolume(1f, 1f)
		player.collectSound = MediaPlayer.create(this, R.raw.jump_new_cut)
		player.collectSound.setVolume(1f, 1f)
		return player
	}

	private fun createCamera(screenSize: Point): Camera {
		val camera = Camera()
		camera.velocity.x = 600f
		camera.scale = screenSize.y / HEIGHT
		return camera
	}

	private fun createBackground(point: Point): Background {
		val background = Background()

		background.drawable = resources.getDrawable(R.drawable.ic_back_1)
		val ratio = background.drawable!!.intrinsicWidth / background.drawable!!.intrinsicHeight
		background.w = (HEIGHT * ratio).toInt()
		background.h = (HEIGHT).toInt()
		background.pos.set(0f, 200f)
		background.velocity.set(600f, 0f)
		return background
	}

	private fun createDeathCollider(): GameObject {
		val obj = Death()
		obj.drawable = ColorDrawable(Color.RED)
		obj.pos.set(0f, 1200f)
		obj.w = 3000
		obj.h = 200
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
					val resultScore = scene.getScore()
					Handler().post {
						intent = GameOverActivity.newIntent(resultScore,this@DhabenskyActivity)
						startActivity(intent)
					}
				}
			}
		}

		override fun getBounds(outRect: RectF) {

		}
	}

}
