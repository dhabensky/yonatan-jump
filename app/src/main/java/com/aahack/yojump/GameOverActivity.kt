package com.aahack.yojump

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gameover.*

/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class GameOverActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_gameover)
		root.setOnClickListener {
			startActivity(
					Intent(this, DhabenskyActivity::class.java)
							.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
			)
		}
	}

}
