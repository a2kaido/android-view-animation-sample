package jp.a2kaido.viewanimation.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.a2kaido.viewanimation.R
import jp.a2kaido.viewanimation.ui.activity.MainActivity

/**
 * Created by anikaido on 2017/10/22.
 */
class MainRecyclerViewAdapter(
        val context: Context,
        val layoutInflater: LayoutInflater = LayoutInflater.from(context),
        val data: Array<MainActivity.Transition>,
        val clickListner: (transition: MainActivity.Transition) -> Unit
): RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = when (data[position]) {
            MainActivity.Transition.ViewAnimation -> "ViewAnimation"
            MainActivity.Transition.ValueAnimator -> "ValueAnimator"
            MainActivity.Transition.ObjectAnimator -> "ObjectAnimator"
            MainActivity.Transition.SpringAnimation -> "SpringAnimation"
        }

        holder?.itemView?.setOnClickListener {
            clickListner.invoke(data[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.item_main_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView by lazy {
            view.findViewById<TextView>(R.id.itemTextView)
        }
    }
}