package br.com.woodriver.screen

import br.com.woodriver.RedGirlGame
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import br.com.woodriver.extensions.getResourcePath
import br.com.woodriver.extensions.isMouseTouchDown
import br.com.woodriver.extensions.logger
import br.com.woodriver.game.BaseActor
import br.com.woodriver.game.BaseScreen
import br.com.woodriver.utils.Logger
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField

class SignUpScreen: BaseScreen() {

    private lateinit var logger: Logger

    override fun initialize() {
        logger = logger<SignUpScreen>()
        logger.error("Sign Up Screen initialized")
        val background = BaseActor(0F, 0F, mainStage)
        background.loadTexture(getResourcePath("background.png"))
        background.setSize(800F, 600F)


        val userNameTextField = TextField("Username", RedGirlGame.textFieldSkin)
        val passwordTextField = TextField("Password", RedGirlGame.textFieldSkin)

        val confirmButton = TextButton("Confirm", RedGirlGame.textButtonStyle)
        val cancelButton = TextButton("Cancel", RedGirlGame.textButtonStyle)

        confirmButton.color = Color.GREEN
        cancelButton.color = Color.RED

        confirmButton.addListener {
            if(it.isMouseTouchDown()){
                //TODO: implement create user
                    logger.info("Confirmed")
                RedGirlGame.setActiveScreen(LoginScreen())
                true
            } else {
                false
            }
        }

        cancelButton.addListener {
            if(it.isMouseTouchDown()) {
                logger.info("Cancelled")
                RedGirlGame.setActiveScreen(LoginScreen())
                true
            } else {
                false
            }
        }

        uiTable.pad(20F)
        uiTable.add().expandX().expandY()
        uiTable.add(userNameTextField).bottom().left()
        uiTable.add(passwordTextField).bottom().left()
        uiTable.add(confirmButton).bottom()
        uiTable.add(cancelButton).bottom()
        Gdx.input.inputProcessor = uiStage
    }

    override fun update(p0: Float) {
        if (Gdx.input.isKeyJustPressed(Keys.A))
            logger.info("Do nothing in Sign Up")
    }
}
