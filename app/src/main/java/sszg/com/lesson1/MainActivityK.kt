package sszg.com.lesson1

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.muddzdev.styleabletoastlibrary.StyleableToast
import sszg.com.lesson1.R.drawable.ic_cloud_upload_black_24dp
import java.util.*

/**
 * Created by saran on 12/25/17.
 */

class MainActivityK : AppCompatActivity() {

    private var diceRollButton: Button? = null
    private var diceImageView: ImageView? = null
    private var diceRollAnimation: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceRollButton = findViewById(R.id.rollDiceButton)
        diceImageView = findViewById(R.id.diceImageView)
        diceImageView!!.setBackgroundResource(R.drawable.dice_roll_animation)
        diceRollAnimation = diceImageView!!.background as AnimationDrawable

        val random = Random()

        diceRollButton!!.setOnClickListener {
            diceRollAnimation!!.start()
            diceRollButton!!.isEnabled = false
            Toast.makeText(applicationContext, "Rolling Dice!", Toast.LENGTH_SHORT).show()
            val waitTime = (random.nextInt(3000) + 1000).toLong()

            object : CountDownTimer(waitTime, waitTime) {

                override fun onTick(l: Long) {

                }

                @SuppressLint("ResourceType")
                override fun onFinish() {
                    diceRollButton!!.isEnabled = true
                    diceRollAnimation!!.stop()
                    // The variable that will guard the frame number
                    var frameNumber = -1

                    // Get the frame of the animation
                    val currentFrame: Drawable
                    var checkFrame: Drawable
                    currentFrame = diceRollAnimation!!.current

                    // Checks the position of the frame
                    for (i in 0 until diceRollAnimation!!.numberOfFrames) {
                        checkFrame = diceRollAnimation!!.getFrame(i)
                        if (checkFrame === currentFrame) {
                            frameNumber = i
                            break
                        }
                    }
                    StyleableToast.makeText(applicationContext, "You rolled a " + (frameNumber + 1) + "!", ic_cloud_upload_black_24dp).show()
                }
            }.start()
        }
    }
}
