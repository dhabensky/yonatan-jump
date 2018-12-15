package com.aahack.yojump

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.aahack.yojump.gameobject.Block
import com.aahack.yojump.gameobject.Camera
import com.aahack.yojump.gameobject.Player
import com.aahack.yojump.gameobject.Scene
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

		scene.addObject(player)
		scene.addObject(block)
		scene.setCamera(camera)
	}

	private fun createPlayer(): Player {
		val player = Player()
		player.drawable = resources.getDrawable(R.drawable.ic_yonatan)
		player.pos.set(100f, 100f)
		player.w = 100
		player.h = 100
		player.velocity.set(10f, 0f)
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
		block.pos.set(block.w / 2f, 150f)
		return block
	}

}
