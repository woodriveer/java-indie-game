package br.com.woodriver.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import br.com.woodriver.game.RedGirlGame
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.InputEvent

class LoginScreen: BaseScreen() {
    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("core/assets/background.png")
        background.setSize(800F, 600F)

        RedGirlGame.loadTextButton()
        val loginButton = TextButton("Login", RedGirlGame.textButtonStyle)

        loginButton.addListener { e: Event? ->
            if (e is InputEvent) false
            if ((e as InputEvent).type != InputEvent.Type.touchDown) false
            RedGirlGame.setActiveScreen(CharacterScreen())
            true
        }

        uiTable.pad(10F)
        uiTable.add().expandX().expandY()
        uiTable.add(loginButton).top()
    }

    override fun update(p0: Float) {
        if (Gdx.input.isKeyPressed(Keys.S))
            RedGirlGame.setActiveScreen(CharacterScreen())
    }
}
