package br.com.woodriver.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import br.com.woodriver.extensions.getResourcePath
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen

class CharacterScreen: BaseScreen() {
    override fun initialize() {
        println("Character Screen initialized")
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture(getResourcePath("core/assets/background.png"))
        background.setSize(800F, 600F)
    }

    override fun update(p0: Float) {
        if (Gdx.input.isKeyPressed(Keys.A))
            println("Do nothing")
    }
}
