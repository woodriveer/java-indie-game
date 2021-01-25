import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import game.RedGirlGame

fun main(args: Array<String>){
    val game = RedGirlGame()
    val launcher = LwjglApplication(game, "RedGirlGame", 800, 600)
}