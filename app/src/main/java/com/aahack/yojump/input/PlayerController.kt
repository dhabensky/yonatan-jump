package com.aahack.yojump.input

import android.view.View
import com.aahack.yojump.gameobject.Player

/**
 * Created on 16.12.2018.
 * @author dhabensky <dhabensky@yandex.ru>
 */
class PlayerController : View.OnClickListener {

	var player: Player? = null

	override fun onClick(v: View?) {
		player?.onJump()
	}

}
