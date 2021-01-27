package br.com.woodriver.screen

import br.com.woodriver.RedGirlGame
import br.com.woodriver.api.RedGirlClient
import br.com.woodriver.api.impl.RedGirlClientImpl
import br.com.woodriver.extensions.getResourcePath
import br.com.woodriver.extensions.isMouseTouchDown
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class CharacterScreen(): BaseScreen() {
    private val redGirlClient: RedGirlClient = RedGirlClientImpl()

    override fun initialize() {
        println("Character Screen initialized")
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture(getResourcePath("background.png"))
        background.setSize(800F, 600F)
    }

    constructor(userId: Int): this() {
        redGirlClient.loadCharacters(userId).characters.map {
            val characterButton = TextButton(it.name, RedGirlGame.textButtonStyle)

            characterButton.addListener { event ->
                if(event.isMouseTouchDown()) {
                    RedGirlGame.setActiveScreen(LevelScreen())
                    true
                } else false
            }

            uiTable.pad(20F)
            uiTable.add().expandX().expandY()
            uiTable.add(characterButton).bottom().right()
        }
    }

    override fun update(p0: Float) {

    }
}
