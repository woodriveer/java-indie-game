package br.com.woodriver.screen

import br.com.woodriver.RedGirlGame
import br.com.woodriver.api.RedGirlClient
import br.com.woodriver.api.impl.RedGirlClientImpl
import br.com.woodriver.extensions.addCleanTextListener
import br.com.woodriver.extensions.isMouseTouchDown
import br.com.woodriver.extensions.logger
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField

class LoginScreen: BaseScreen() {
    private val redGirlClient: RedGirlClient = RedGirlClientImpl()
    val logger = logger<LoginScreen>()

    override fun initialize() {
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture("background.png")
        background.setSize(800F, 600F)

        RedGirlGame.loadTextButton()
        val loginButton = TextButton("Login", RedGirlGame.textButtonStyle)
        val signUpButton = TextButton("Sign Up", RedGirlGame.textButtonStyle)
        val userNameTextField = TextField("Username", RedGirlGame.textFieldSkin)
        val passwordTextField = TextField("Password", RedGirlGame.textFieldSkin)

        loginButton.addListener {
            if(it.isMouseTouchDown()) {
                try {
                    RedGirlGame.userId = redGirlClient.login(userNameTextField.text, passwordTextField.text)
                    RedGirlGame.setActiveScreen(CharacterScreen())
                } catch (e: Exception){
                    e.printStackTrace()
                    logger.warning("Username/Password invalid")
                }
                true
            } else false
        }

        signUpButton.addListener {
            if(it.isMouseTouchDown()) {
                RedGirlGame.setActiveScreen(SignUpScreen())
                true
            } else  false
        }

        userNameTextField.addCleanTextListener()
        passwordTextField.addCleanTextListener()

        uiTable.pad(20F)
        uiTable.add().expandX().expandY()
        uiTable.add(loginButton).bottom().right()
        uiTable.add(signUpButton).bottom().right()
        uiTable.add(userNameTextField).bottom().left()
        uiTable.add(passwordTextField).bottom().left()
        Gdx.input.inputProcessor = uiStage
    }

    override fun update(p0: Float) {
    }
}
