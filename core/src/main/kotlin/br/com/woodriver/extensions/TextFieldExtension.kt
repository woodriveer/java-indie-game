package br.com.woodriver.extensions

import com.badlogic.gdx.scenes.scene2d.ui.TextField


fun TextField.addCleanTextListener() =
    this.addListener {
        if (it.isMouseTouchDown()) {
            this.text = ""
            true
        } else {
            false
        }
    }