package br.com.woodriver.game

import com.badlogic.gdx.scenes.scene2d.Stage

class Monster(x: Float, y: Float, stage: Stage) : BaseActor(x, y, stage) {


    var health: Float = Float.MIN_VALUE

    constructor(x: Float, y: Float, stage: Stage, monsterHealth: Float): this(x, y, stage) {
        acceleration = 2000F
        maxSpeed = 500F
        deceleration = 2000F
        health = monsterHealth

        val sprites = arrayOf("assets/skeleton.png")

        loadAnimationFromFiles(sprites, 0.1f, true)

        setBoundaryPolygon(8)
        setWorldBounds(width, height)
    }

    fun getDefense(): Float {
        //TODO: Return defense from level and DEF
        return 10F
    }
}