package br.com.woodriver.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import java.io.File

abstract class BaseGame: Game() {


    val textButtonStyle: TextButtonStyle

    init {
        println("BaseGame initialized")
        textButtonStyle = TextButtonStyle()
    }

    fun loadTextButton() {



        // parameters for generating a custom bitmap font
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("core/assets/OpenSans.ttf"))
        val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        fontParameters.size = 16
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2F
        fontParameters.borderStraight = true
        fontParameters.minFilter = Texture.TextureFilter.Linear
        fontParameters.magFilter = Texture.TextureFilter.Linear

        val customFont = fontGenerator.generateFont(fontParameters)

        val buttonTexture = Texture("core/assets/blackButton.png")
        val buttonPatch = NinePatch(buttonTexture, 12, 12, 12, 12)
        buttonPatch.scale(0.3F, 0.2F)
        textButtonStyle.up = NinePatchDrawable(buttonPatch)
        textButtonStyle.font = customFont
        textButtonStyle.fontColor = Color.WHITE
    }

    fun setActiveScreen(screen: BaseScreen){
        this.setScreen(screen)
    }
}