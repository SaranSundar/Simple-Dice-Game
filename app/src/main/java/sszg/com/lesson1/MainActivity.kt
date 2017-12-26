package sszg.com.lesson1

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
/**
 * In order to do this, you needed to apply the following plugin to your gradle:
 *      apply plugin: 'kotlin-android-extensions'
 */
import kotlinx.android.synthetic.main.activity_main.*
import java.security.SecureRandom

/**
 * Created by Arham on 12/26/2017.
 */
class MainActivity : AppCompatActivity() {
    /**
     * So I heard from somewhere random (no pun intended) that SecureRandom is always preferred over
     * Random. Why? I don't know, but hey, it sounds secure.
     */
    private val random = SecureRandom()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * I don't know which way is better, but I prefer the way of putting in the XML the onClick
     * function and making all the buttons have separate functions. Just me? Maybe.
     */
    fun rollDice(view: View) {
        /**
         * Don't need all the other ids since they're all in the XML. (:
         */
        val animation = diceImageView.background as AnimationDrawable
        val waitTime = (random.nextInt(3000) + 1000).toLong()

        animation.start()
        rollDiceButton.isEnabled = false
        /**
         * Having an extension function for toast is useful for future. Not crucial though, I didn't
         * have to do this.
         */
        "Rolling Dice!".toToast().show()
        /**
         * I have a little nitpick of making a class in a function, especially if you aren't using
         * one of the functions in the class. Idk, I didn't have to do this either really.
         */
        sendCountdown(waitTime, waitTime) {
            rollDiceButton.isEnabled = true
            animation.stop()
            /**
             * I noticed all the code after frameNumber was primarily just to find the frame number.
             * The let keyword is helpful to localize all that.
             */
            val frameNumber = let {
                val currentFrame = animation.current
                /**
                 * This is like Java 8 streams. Since there is only one frame that has the same
                 * frame as the current one, I return the first index of the list that is generated
                 * by {.filter {}}
                 */
                (0 until animation.numberOfFrames)
                        .filter { animation.getFrame(it) == currentFrame }[0]
            }
            /** Here's an alternate solution (unsure if this will work):
            val frameNumber2: Int = let {
                val currentFrame = animation.current
                (0 until animation.numberOfFrames)
                        .filter { currentFrame == animation.getFrame(it) }
                        .forEach { return@let it }
                -1
            }*/
            /**
             * ^ essentially, the return@let allows us to return the appropriate frame index, and if
             * nothing is found, it would return -1.
             */
            MaterialDialog.Builder(this)
                    .title("You rolled a...")
                    .content("${(frameNumber.plus(1))}")
                    .positiveText("Ok")
                    .show()
        }
    }

    /**
     * Extension function for toast
     * Modified because .toToast().show() seems more logical than .show()
     * Modified because of wanting to perhaps have manipulation of length. Demonstrated default
     * parameters in Kotlin.
     */
    private fun String.toToast(length: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(this@MainActivity, this, length)
    }

    /**
     * With extensios, you can make variables out of them too. For example, the above code could be
     * this:
     *     private val String.toast: Toast
     *          get() = Toast.makeText(this@MainActivity, this, Toast.LENGTH_LONG)
     * Which would allow you to do:
     *     "Rolling Dice!".toast.show()
     */

    /**
     * Uses inline functions. The crossinline modifier is just restriction so that we can can't
     * return anything. I forget to add it most of the time really. Essentially, the weird "() -> Unit"
     * is just me declaring it's a lambda.
     *
     * Don't worry though, they're just keywords for optimization. You can read more in the
     * KotlinDocs: http://kotlinlang.org/docs/reference/inline-functions.html
     */
    private inline fun sendCountdown(millisInFuture: Long, countDownInterval: Long, crossinline onFinish: () -> Unit) {
        object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                onFinish()
            }

            override fun onTick(p0: Long) {}
        }.start()
    }
}