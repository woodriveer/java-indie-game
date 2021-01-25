package game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.*
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import java.util.*

class BaseActor(): Actor() {

    var animation: Animation<TextureRegion>? = null
    var elapsedTime: Float = 0F
    var animationPaused: Boolean = false
    var velocityVec: Vector2 = Vector2(0F, 0F)
    var accelerationVec:Vector2 = Vector2(0F, 0F)
    var acceleration: Float = 0F
    var maxSpeed: Float = 1000F
    var deceleration: Float = 0F

    var boundaryPolygon: Polygon? = null

    lateinit var worldBounds: Rectangle

    constructor(x: Float, y: Float, stage: Stage): this() {
        setPosition(x, y)
        stage.addActor(this)
    }

    fun centerAtPosition(x: Float, y: Float) {
        setPosition(x - width / 2, y - height / 2)
    }

    fun centerAtActor(other: BaseActor) {
        centerAtPosition(other.x + other.width / 2, other.y + other.height / 2)
    }

    fun setAnim(anim: Animation<TextureRegion>) {
        animation = anim
        val textureRegion = animation!!.getKeyFrame(0F)
        val width = textureRegion.regionWidth.toFloat()
        val height = textureRegion.regionHeight.toFloat()
        setSize(width, height)
        setOrigin(width / 2, height / 2)

        if (boundaryPolygon == null) {
            setBoundaryRectangle()
        }
    }

    fun loadTexture(fileName: String?): Animation<TextureRegion>? {
        val fileNames = arrayOfNulls<String>(1)
        fileNames[0] = fileName
        return loadAnimationFromFiles(fileNames, 1f, true)
    }

    fun loadAnimationFromFiles(fileNames: Array<String?>, frameDuration: Float, loop: Boolean): Animation<TextureRegion>? {
        val fileCount = fileNames.size
        val textureArray = com.badlogic.gdx.utils.Array<TextureRegion>()
        for (n in 0 until fileCount) {
            val fileName = fileNames[n]
            val texture = Texture(Gdx.files.internal(fileName))
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
            textureArray.add(TextureRegion(texture))
        }
        val anim = Animation(frameDuration, textureArray)
        if (loop) anim.setPlayMode(Animation.PlayMode.LOOP) else anim.setPlayMode(Animation.PlayMode.NORMAL)
        if (animation == null) setAnim(anim)
        return anim
    }

    fun setBoundaryRectangle() {
        val vertices = floatArrayOf(0F, 0F, width, 0f, width, height, 0F, height)
        boundaryPolygon = Polygon(vertices)
    }

    fun setBoundaryPolygon(numberSides: Int) {
        val vertices = FloatArray(2 * numberSides)
        for (i in 0 until numberSides) {
            val angle: Float = i * 6.28f / numberSides
            // x-coordinate
            vertices[2 * i] = width / 2 * MathUtils.cos(angle) + width / 2
            // y-coordinate
            vertices[2 * i + 1] = height / 2 * MathUtils.sin(angle) + height / 2
        }
        boundaryPolygon = Polygon(vertices)
    }

    fun getBoundary(): Polygon {
        boundaryPolygon!!.setPosition(x, y)
        boundaryPolygon!!.setOrigin(originX, originY)
        boundaryPolygon!!.rotation = rotation
        boundaryPolygon!!.setScale(scaleX, scaleY)
        return boundaryPolygon!!
    }

    fun overlaps(other: BaseActor): Boolean {
        val poly1: Polygon = this.getBoundary()
        val poly2: Polygon = other.getBoundary()

        // initial test to improve performance
        return if (!poly1.boundingRectangle.overlaps(poly2.boundingRectangle)) false else Intersector.overlapConvexPolygons(poly1, poly2)
    }

    fun preventOverlap(other: BaseActor): Vector2? {
        val poly1: Polygon = this.getBoundary()
        val poly2: Polygon = other.getBoundary()

        // initial test to improve performance
        if (!poly1.boundingRectangle.overlaps(poly2.boundingRectangle)) return null
        val mtv = MinimumTranslationVector()
        val polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv)
        if (!polygonOverlap) return null
        moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth)
        return mtv.normal
    }

    fun setWorldBounds(width: Float, height: Float) {
        worldBounds = Rectangle(0F, 0F, width, height)
    }

    fun setWorldBounds(ba: BaseActor) {
        setWorldBounds(ba.width, ba.height)
    }

    fun boundToWorld() {
        if (x < 0) x = 0f
        if (x + width > worldBounds.width) x = worldBounds.width - width
        if (y < 0) y = 0f
        if (y + height > worldBounds.height) y = worldBounds.height - height
    }

    fun alignCamera() {
        val cam = stage.camera
        val v = stage.viewport

        // center camera on actor
        cam.position[x + originX, y + originY] = 0f

        // bound camera to layout
        cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth / 2, worldBounds.width - cam.viewportWidth / 2)
        cam.position.y = MathUtils.clamp(cam.position.y, cam.viewportHeight / 2, worldBounds.height - cam.viewportHeight / 2)
        cam.update()
    }

    // ----------------------------------------------
    // Instance list methods
    // ----------------------------------------------
    fun getList(stage: Stage, className: String): ArrayList<BaseActor> {
        val list = ArrayList<BaseActor>()
        var theClass: Class<*>? = null
        try {
            theClass = Class.forName(className)
        } catch (error: Exception) {
            error.printStackTrace()
        }
        for (a in stage.actors) {
            if (theClass!!.isInstance(a)) list.add(a as BaseActor)
        }
        return list
    }

    fun count(stage: Stage, className: String): Int {
        return getList(stage, className).size
    }

    // ----------------------------------------------
    // Actor methods: act and draw
    // ----------------------------------------------
    override fun act(dt: Float) {
        super.act(dt)
        if (!animationPaused) elapsedTime += dt
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        // apply color tint effect
        val c = color
        batch.setColor(c.r, c.g, c.b, c.a)
        if (animation != null && isVisible) batch.draw(animation!!.getKeyFrame(elapsedTime),
                x, y, originX, originY,
                width, height, scaleX, scaleY, rotation)
    }
}