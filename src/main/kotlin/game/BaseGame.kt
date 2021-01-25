package game

import com.badlogic.gdx.Game

abstract class BaseGame: Game() {

    init {
        println("BaseGame initialized")
    }

    fun setActiveScreen(screen: BaseScreen){
        this.setScreen(screen)
    }
}