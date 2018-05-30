package `in`.yeng.user.Adaptors

import `in`.yeng.user.Activities.MainActivity
import `in`.yeng.user.Fragments.FragSyllabus
import `in`.yeng.user.R
import `in`.yeng.user.Utilities.FragmentHelper
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_syllabus.view.*


class SyllabusAdapter(val data: List<String>, val idStr: String, val activity: AppCompatActivity?) : RecyclerView.Adapter<SyllabusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_syllabus, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], idStr, activity)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: String, idStr: String, activity: AppCompatActivity?) = with(view) {
            content.text = data.replace(idStr.plus("_"), "")

            card.setOnClickListener {
                activity?.let {

                    val fragSyllabus = FragSyllabus()
                    fragSyllabus.arguments = Bundle().apply {
                        putString("id", data)
                        FragmentHelper.ReplaceFragment(fragSyllabus, it, MainActivity.CONTAINER_LAYOUT, 250)
                    }
                }

            }
        }

    }
}