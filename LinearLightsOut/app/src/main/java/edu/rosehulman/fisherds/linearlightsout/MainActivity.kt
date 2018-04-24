package edu.rosehulman.fisherds.linearlightsout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val numButtons = 7;
    private lateinit var mLightButtons: Array<Button?>
    private lateinit var mGame: LinearLightsOutGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGame = LinearLightsOutGame(numButtons)
        mLightButtons = Array<Button?>(mGame.lights.size, { null });
        mLightButtons[0] = findViewById(R.id.button0)
        mLightButtons[1] = findViewById(R.id.button1)
        mLightButtons[2] = findViewById(R.id.button2)
        mLightButtons[3] = findViewById(R.id.button3)
        mLightButtons[4] = findViewById(R.id.button4)
        mLightButtons[5] = findViewById(R.id.button5)
        mLightButtons[6] = findViewById(R.id.button6)

        for (i in mLightButtons.indices) {
            mLightButtons[i]?.setOnClickListener({
                mGame.pressButtonAt(i)
                updateView()
            })
        }

        newGameButton.setOnClickListener({
            mGame = LinearLightsOutGame(numButtons)
            for (button in mLightButtons) {
                button?.isEnabled = true
            }
            updateView()
        })
        updateView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        // TODO: Save the state.
    }

    private fun updateView() {
        for (k in mLightButtons.indices) {
            mLightButtons[k]?.text = if (mGame.isLightOnAt(k)) "1" else "0"
        }
        if (mGame.numPresses == 0) {
            gameStateTextView.text = getString(R.string.label_start)
        } else {
            if (mGame.isGameWon()) {
                gameStateTextView.text = getString(R.string.win, mGame.numPresses)
                for (button in mLightButtons) {
                    button?.isEnabled = false
                }
            } else {
                gameStateTextView.text = resources.getQuantityString(R.plurals.game_state_turns, mGame.numPresses, mGame.numPresses)


            }
        }

    }
}
