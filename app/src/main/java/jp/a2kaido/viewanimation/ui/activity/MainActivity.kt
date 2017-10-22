package jp.a2kaido.viewanimation.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import jp.a2kaido.viewanimation.R
import kotterknife.bindView

class MainActivity : AppCompatActivity() {

    val recyclerView: RecyclerView by bindView(R.id.mainRecyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
