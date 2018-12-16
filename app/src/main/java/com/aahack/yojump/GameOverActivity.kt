package com.aahack.yojump

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_gameover.*

/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class GameOverActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		requestWindowFeature(Window.FEATURE_NO_TITLE)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.activity_gameover)

		root.setOnClickListener {
			startActivity(
					Intent(this, DhabenskyActivity::class.java)
							.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
			)
		}
	}

}
