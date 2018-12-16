package com.aahack.yojump

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_gameover.*

/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class GameOverActivity : AppCompatActivity() {

	companion object {

		open fun newIntent(score: String?, context: Context): Intent? {
			val intent = Intent(context, GameOverActivity::class.java)
			intent.putExtra("score", score)
			return intent
		}

	}

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

		val text = "Your score - " + intent.extras.get("score")
		findViewById<TextView>(R.id.score_text_view).setText(text)
	}

	override fun onBackPressed() {

	}

}
