package sszg.com.lesson1;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Random;

public class MainActivitys extends AppCompatActivity {

    private Button diceRollButton;
    private ImageView diceImageView;
    private AnimationDrawable diceRollAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceRollButton = findViewById(R.id.rollDiceButton);
        diceImageView = findViewById(R.id.diceImageView);
        diceImageView.setBackgroundResource(R.drawable.dice_roll_animation);
        diceRollAnimation = (AnimationDrawable) diceImageView.getBackground();

        final Random random = new Random();

        diceRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceRollAnimation.start();
                diceRollButton.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Rolling Dice!", Toast.LENGTH_SHORT).show();
                long waitTime = random.nextInt(3000) + 1000;

                new CountDownTimer(waitTime, waitTime) {

                    @Override
                    public void onTick(long l) {

                    }

                    @SuppressLint("ResourceType")
                    @Override
                    public void onFinish() {
                        diceRollButton.setEnabled(true);
                        diceRollAnimation.stop();
                        // The variable that will guard the frame number
                        int frameNumber = -1;

                        // Get the frame of the animation
                        Drawable currentFrame, checkFrame;
                        currentFrame = diceRollAnimation.getCurrent();

                        // Checks the position of the frame
                        for (int i = 0; i < diceRollAnimation.getNumberOfFrames(); i++) {
                            checkFrame = diceRollAnimation.getFrame(i);
                            if (checkFrame == currentFrame) {
                                frameNumber = i;
                                break;
                            }
                        }
                        new MaterialDialog.Builder(MainActivitys.this)
                                .title("You rolled a...")
                                .content("" + (frameNumber + 1))
                                .positiveText("Ok")
                                .show();
                        //StyleableToast.makeText(getApplicationContext(), "You rolled a " + (frameNumber + 1) + "!", ic_cloud_upload_black_24dp).show();
                    }
                }.start();
            }
        });
    }
}
