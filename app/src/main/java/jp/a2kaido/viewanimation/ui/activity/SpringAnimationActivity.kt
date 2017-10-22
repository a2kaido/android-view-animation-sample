package jp.a2kaido.viewanimation.ui.activity

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import jp.a2kaido.viewanimation.R
import kotterknife.bindView

class SpringAnimationActivity : AppCompatActivity(), View.OnTouchListener {

    private var preDx: Int = 0
    private var newDx: Int = 0
    private var initX: Int = 0

    private val arrow: TextView by bindView(R.id.springAnimationArrow)
    private val seekBar: SeekBar by bindView(R.id.seekBar)

    private val animX: SpringAnimation by lazy {
        SpringAnimation(arrow, DynamicAnimation.X, 0f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_animation)

        title = "SpringAnimation"

        animX.spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY

        arrow.setOnTouchListener(this)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                animX.spring.dampingRatio = progress.toFloat() / 100
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        newDx = event.rawX.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initX = view.left
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = view.left + newDx - preDx

                view.layout(dx, 0, dx + view.width, view.height)
            }
            MotionEvent.ACTION_UP -> {
                animX.start()
            }
        }

        preDx = newDx
        return true
    }
}
