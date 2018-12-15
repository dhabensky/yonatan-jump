package com.aahack.yojump

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Display
import android.view.Window
import android.view.WindowManager
import com.aahack.yojump.gameobject.*
import com.aahack.yojump.gameobject.Block
import com.aahack.yojump.gameobject.Camera
import com.aahack.yojump.gameobject.Player
import com.aahack.yojump.gameobject.Scene
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

		gameView.scene = scene

		val player = createPlayer()
		val camera = createCamera()
		val block = createBlock()
		val background = createBackground()

		scene.addObject(background)
		scene.setPlayer(player)
		scene.setCamera(camera)
		scene.addObject(block)
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
		player.frames = createPlayerFrames()
		player.pos.set(100f, 220f)
		player.acceleration.y = 100f
		player.w = 100
		player.h = 100
		player.velocity.set(200f, -30f)
		return player
	}

	private fun createCamera(): Camera {
		val camera = Camera()
//		camera.velocity.x = 8f
		return camera
	}

	private fun createBlock(): Block {
		val block = Block()
		block.drawable = ColorDrawable(Color.BLACK)
		block.w = 300
		block.h = 20
		block.pos.set(0f, 150f)
		block.tag = "block"
		return block
	}

	private fun  createBackground(): Background{
		val background = Background()

		val display:Display = windowManager.defaultDisplay
		val point  = Point()
		display.getSize(point)

		background.drawable = resources.getDrawable(R.drawable.ic_back_1)
		background.w = point.x
		background.h = point.y / 2
		background.pos.set(0f, (point.y - background.h).toFloat())
		return background
	}

}
