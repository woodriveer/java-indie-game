package br.com.woodriver.game

import br.com.woodriver.extensions.FONT_RAIDER_CRUSADER_PUNCH
import br.com.woodriver.extensions.logger
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle

abstract class BaseGame: Game() {


    val textButtonStyle: TextButtonStyle
    val textFieldStyle: TextFieldStyle
    lateinit var labelCharacterStyle: Label.LabelStyle
    lateinit var textFieldSkin: Skin
    private val logger = logger<BaseGame>()

    init {
        logger.info("BaseGame initialized")
        textButtonStyle = TextButtonStyle()
        textFieldStyle = TextFieldStyle()
    }

    fun loadTextButton() {

        textFieldSkin = Skin(Gdx.files.internal("ui/uiskin.json"))

        // parameters for generating a custom bitmap font
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal(FONT_RAIDER_CRUSADER_PUNCH))
        val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        fontParameters.size = 16
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2F
        fontParameters.borderStraight = true
        fontParameters.minFilter = Texture.TextureFilter.Linear
        fontParameters.magFilter = Texture.TextureFilter.Linear

        val customFont = fontGenerator.generateFont(fontParameters)

//        val buttonTexture = Texture("blackButton.png")
//        val buttonPatch = NinePatch(buttonTexture, 12, 12, 12, 12)
//        buttonPatch.scale(0.3F, 0.2F)
//        textButtonStyle.up = NinePatchDrawable(buttonPatch)
        textFieldStyle.font = customFont
        textButtonStyle.font = customFont
        textButtonStyle.fontColor = Color.WHITE

        labelCharacterStyle = Label.LabelStyle(customFont, Color.WHITE)
    }

    fun setActiveScreen(screen: BaseScreen){
        this.setScreen(screen)
    }
}