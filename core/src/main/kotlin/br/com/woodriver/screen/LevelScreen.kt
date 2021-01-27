package br.com.woodriver.screen

import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen

class LevelScreen(): BaseScreen() {
    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("background.png")
        background.setSize(800F, 600F)
    }

    constructor(userId: Int): this() {

    }

    override fun update(p0: Float) {
        TODO("Not yet implemented")
    }
}