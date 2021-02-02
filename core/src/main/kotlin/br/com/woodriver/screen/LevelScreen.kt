package br.com.woodriver.screen

import br.com.woodriver.api.response.CharactersResponse.Character
import br.com.woodriver.extensions.logger
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import br.com.woodriver.game.GameCharacter

class LevelScreen(): BaseScreen() {
    private val logger = logger<LevelScreen>()
    private lateinit var levelCharacter: Character
    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("background-red.png")
        background.setSize(WIDTH_MAP, HEIGHT_MAP)

        BaseActor().setWorldBounds(background)

    }

    constructor(character: Character): this() {
        logger.info("Starting game with ${character.name} at level ${character.level}")
        levelCharacter = character

        GameCharacter(character, mainStage, WIDTH_MAP, HEIGHT_MAP)
    }

    override fun update(p0: Float) {
    }

    companion object {
        const val WIDTH_MAP = 2048F
        const val HEIGHT_MAP = 1028F
    }
}