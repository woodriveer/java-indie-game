package game

import screen.LoginScreen

class RedGirlGame: BaseGame() {

    override fun create(){
        setActiveScreen(LoginScreen())
    }
}