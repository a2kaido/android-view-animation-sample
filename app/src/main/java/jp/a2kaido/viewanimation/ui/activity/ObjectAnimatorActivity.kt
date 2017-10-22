package jp.a2kaido.viewanimation.ui.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import jp.a2kaido.viewanimation.R
import kotterknife.bindView

class ObjectAnimatorActivity : AppCompatActivity(), View.OnTouchListener {

    private var preDx: Int = 0
    private var newDx: Int = 0
    private var initX: Int = 0

    private val arrow: TextView by bindView(R.id.objectAnimatorArrow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        title = "ObjectAnimator"

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
                val anim = ObjectAnimator.ofFloat(view, "translationX", 0f, view.x * (-1)).apply {
                    duration = 500
                    addListener(object: Animator.AnimatorListener {
                        override fun onAnimationRepeat(p0: Animator?) {
                            //
                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            view.translationX = 0f
                            view.layout(0, view.top, view.width, view.top + view.height)
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                            //
                        }

                        override fun onAnimationStart(p0: Animator?) {
                            //
                        }
                    })
                }

                anim.start()
            }
        }

        preDx = newDx
        return true
    }
}
