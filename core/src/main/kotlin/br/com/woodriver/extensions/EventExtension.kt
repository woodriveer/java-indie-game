package br.com.woodriver.extensions

import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.InputEvent

fun Event.isMouseTouchDown(): Boolean =
    if(this is InputEvent)
        this.type == InputEvent.Type.touchDown
    else
        false