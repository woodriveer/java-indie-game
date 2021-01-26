package br.com.woodriver.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table

abstract class BaseScreen(
    val mainStage: Stage,
    val uiStage: Stage,
    val uiTable: Table
): Screen {
    constructor(): this(Stage(), Stage(), Table()){
        uiTable.setFillParent(true)
        uiStage.addActor(uiTable)
        initialize()
    }

    override fun show() { }

    abstract fun initialize()

    abstract fun update(p0: Float)

    override fun render(p0: Float) {
        uiStage.act(p0)
        mainStage.act(p0)

        update(p0)

        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT)

        mainStage.draw()
        uiStage.draw()
    }

    override fun resize(p0: Int, p1: Int) { }

    override fun pause() { }

    override fun resume() { }

    override fun hide() { }

    override fun dispose() { }

}