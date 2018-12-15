package com.aahack.yojump

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
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

		val player = Player()
		player.drawable = resources.getDrawable(R.drawable.ic_yonatan)
		player.pos.set(100f, 100f)
		player.w = 100
		player.h = 100
		player.velocity.set(10f, 0f)

		val camera = Camera()
		camera.velocity.x = 8f

		scene.addObject(player)
		scene.setCamera(camera)
	}

}
