package jp.a2kaido.viewanimation.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import jp.a2kaido.viewanimation.R
import kotterknife.bindView

class ViewAnimationActivity : AppCompatActivity(), View.OnTouchListener {

    private var preDx: Int = 0
    private var newDx: Int = 0
    private var initX: Int = 0

    val arrow: TextView by bindView(R.id.viewAnimationArrow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)

        title = "ViewAnimation"

        arrow.setOnTouchListener(this)
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
                val anim = TranslateAnimation(0f , view.x.toFloat() * (-1), 0f, 0f).apply {
                    duration = 500
                    fillAfter = true
                }

                view.startAnimation(anim)
            }
        }

        preDx = newDx
        return true
    }
}
