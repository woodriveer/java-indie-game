package br.com.woodriver.game

import br.com.woodriver.screen.LoginScreen

object RedGirlGame: BaseGame() {

    override fun create(){
        setActiveScreen(LoginScreen())
    }
}