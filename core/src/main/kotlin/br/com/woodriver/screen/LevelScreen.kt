package br.com.woodriver.screen

import br.com.woodriver.api.response.CharactersResponse.Character
import br.com.woodriver.extensions.logger
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import br.com.woodriver.game.GameCharacter
import br.com.woodriver.game.Monster
import com.badlogic.gdx.scenes.scene2d.actions.Actions

class LevelScreen(): BaseScreen() {
    private val logger = logger<LevelScreen>()
    private lateinit var levelCharacter: Character
    private lateinit var gameCharacter: GameCharacter
    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("background-red.png")
        background.setSize(WIDTH_MAP, HEIGHT_MAP)

        BaseActor().setWorldBounds(background)
        Monster(20F, 200F, mainStage, 30000F)
    }

    constructor(character: Character): this() {
        logger.info("Starting game with ${character.name} at level ${character.level}")
        levelCharacter = character

        gameCharacter = GameCharacter(character, mainStage, WIDTH_MAP, HEIGHT_MAP)
    }

    override fun update(p0: Float) {
        BaseActor().getList(mainStage, "Monster").map {
            val monster = it as Monster
            if(gameCharacter.overlaps(monster) && !gameCharacter.isAttacking) {
                //TODO: Game Character lose life
            } else {
                //TODO: Monster lose life
                logger.info("Monster losing life ${monster.health}")
                monster.health -= gameCharacter.getAttackDamage() - monster.getDefense()
                if(monster.health < 0)
                    monster.addAction(Actions.fadeOut(0.5F))

            }
        }
    }

    companion object {
        const val WIDTH_MAP = 2048F
        const val HEIGHT_MAP = 1028F
    }
}