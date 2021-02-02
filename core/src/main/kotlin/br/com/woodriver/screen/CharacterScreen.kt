package br.com.woodriver.screen

import br.com.woodriver.RedGirlGame
import br.com.woodriver.api.RedGirlClient
import br.com.woodriver.api.impl.RedGirlClientImpl
import br.com.woodriver.api.response.CharactersResponse
import br.com.woodriver.extensions.getResourcePath
import br.com.woodriver.extensions.isMouseTouchDown
import br.com.woodriver.extensions.logger
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import br.com.woodriver.utils.Logger
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

open class CharacterScreen: BaseScreen() {
    private lateinit var logger: Logger

    override fun initialize() {
        val redGirlClient: RedGirlClient = RedGirlClientImpl()
        logger = logger<CharacterScreen>()

        logger.info("Character Screen initialized")
        uiTable.clear()
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture(getResourcePath("background-gray.png"))
        background.setSize(800F, 600F)

        redGirlClient.loadCharacters(RedGirlGame.userId).characters.map {
            val characterButton = TextButton(it.name, RedGirlGame.textButtonStyle)

            logger.info("Starting level screen with ${it.name}")
            characterButton.addListener { event ->
                if(event.isMouseTouchDown()) {
                    try {
                        logger.info("Starting level screen with ${it.name}")
                        RedGirlGame.setActiveScreen(LevelScreen(it))
                    } catch (ex: Exception) {
                        logger.error("Something happen could not load screen.")
                    }
                    true
                } else {
                    false
                }
            }

            uiTable.pad(20F)
            uiTable.add().expandX().expandY()
            uiTable.add(characterButton).top().right()
        }

        Gdx.input.inputProcessor = uiStage
    }

    override fun update(p0: Float) {
    }
}
