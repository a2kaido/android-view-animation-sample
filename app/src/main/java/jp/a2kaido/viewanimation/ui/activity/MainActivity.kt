package jp.a2kaido.viewanimation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import jp.a2kaido.viewanimation.R
import jp.a2kaido.viewanimation.ui.adapter.MainRecyclerViewAdapter
import kotterknife.bindView

class MainActivity : AppCompatActivity() {

    sealed class Transition {
        object ViewAnimation: Transition()
        object ObjectAnimator: Transition()
        object ValueAnimator: Transition()
        object SpringAnimation: Transition()
    }

    private val recyclerView: RecyclerView by bindView(R.id.mainRecyclerView)

    private val clickListner: (Transition) -> Unit = {
        when (it) {
            Transition.ViewAnimation -> {
                val intent = Intent(MainActivity@ this, ViewAnimationActivity::class.java)

                startActivity(intent)
            }
            Transition.ObjectAnimator -> {
                val intent = Intent(MainActivity@ this, ObjectAnimatorActivity::class.java)
                startActivity(intent)
            }
            Transition.ValueAnimator -> {
                val intent = Intent(MainActivity@ this, ValueAnimatorActivity::class.java)
                startActivity(intent)
            }
            Transition.SpringAnimation -> {
                val intent = Intent(MainActivity@ this, SpringAnimationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "アニメーション実装"

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainRecyclerViewAdapter(
                context = this,
                data = arrayOf(
                        Transition.ViewAnimation,
                        Transition.ObjectAnimator,
                        Transition.ValueAnimator,
                        Transition.SpringAnimation),
                clickListner = clickListner
        )
    }
}
