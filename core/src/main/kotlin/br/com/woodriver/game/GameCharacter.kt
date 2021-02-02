package br.com.woodriver.game

import br.com.woodriver.api.response.CharactersResponse.Character
import br.com.woodriver.extensions.logger
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.Stage
import br.com.woodriver.game.BaseActor.Companion.Direction

class GameCharacter(private val character: Character,
                    private val xPosition: Float = 0F,
                    private val yPosition: Float,
                    private val characterStage: Stage
): BaseActor(xPosition, yPosition, characterStage) {

    private val logger = logger<GameCharacter>()

    val isAttacking = true

    constructor(character: Character,stage: Stage, width: Float, height: Float): this(character, 0F, 0F, stage) {
        acceleration = 2000F
        maxSpeed = 500F
        deceleration = 2000F

        val sprites = arrayOf("assets/orc.png")

        loadAnimationFromFiles(sprites, 0.1f, true)

        setBoundaryPolygon(8)
        setWorldBounds(width, height)
    }

    init {
        logger.info("GameCharacter ${character.name} created")
    }

    override fun act(dt: Float) {
        super.act(dt)

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
            Gdx.input.isKeyPressed(Input.Keys.A)) accelerateAtAngle(180F, Direction.LEFT)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)||
            Gdx.input.isKeyPressed(Input.Keys.D)) accelerateAtAngle(0F,  Direction.RIGHT)
        if (Gdx.input.isKeyPressed(Input.Keys.UP)||
            Gdx.input.isKeyPressed(Input.Keys.W)) accelerateAtAngle(90F,  Direction.UP)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)||
            Gdx.input.isKeyPressed(Input.Keys.S)) accelerateAtAngle(270F, Direction.DOWN)

        applyPhysics(dt)

        if (getSpeed() > 0) rotation = getMotionAngle()

        boundToWorld()

        alignCamera()
    }

    fun getAttackDamage(): Float {
        //TODO: Get Attack damage base on Attributes and Weapon
        return 100F
    }
}