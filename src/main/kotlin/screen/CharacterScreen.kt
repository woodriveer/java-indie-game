package screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import game.BaseScreen

class CharacterScreen: BaseScreen() {
    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun update(p0: Float) {
        if (Gdx.input.isKeyPressed(Keys.S))
            println("Do nothing")
    }
}
