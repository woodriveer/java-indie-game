package screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import game.BaseActor
import game.BaseScreen
import game.RedGirlGame

class LoginScreen: BaseScreen() {
    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("resources/assets/background.png")
        background.setSize(800F, 600F)
    }

    override fun update(p0: Float) {
        if (Gdx.input.isKeyPressed(Keys.S))
            RedGirlGame().setActiveScreen(CharacterScreen())
    }
}
