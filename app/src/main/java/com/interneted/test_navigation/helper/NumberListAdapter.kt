package com.interneted.test_navigation.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.newFixedThreadPoolContext
import org.w3c.dom.Text

/**
 * Creator: ED
 * Date: 2/4/21 9:38 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class NumberListAdapter : RecyclerView.Adapter<NumberListAdapter.NumberViewHolder>() {


    private val tvNumber = View.generateViewId()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {

        val frameLayout = FrameLayout(parent.context)
        frameLayout.id = View.generateViewId()

        frameLayout.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        val textView = TextView(parent.context)

        textView.id = tvNumber

        frameLayout.addView(
            textView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return NumberViewHolder(frameLayout)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(tvNumber)
            .text = "第${position}個"
    }

    override fun getItemCount(): Int {
        return 30
    }


    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}