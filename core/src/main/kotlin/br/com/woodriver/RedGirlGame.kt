package br.com.woodriver

import br.com.woodriver.game.BaseGame
import br.com.woodriver.screen.LoginScreen

object RedGirlGame: BaseGame() {

    var userId: Int = 0

    override fun create(){
        setActiveScreen(LoginScreen())
    }
}