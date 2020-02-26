package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.MyViewHolder
import java.util.*

//Пример адаптера для ReciclerView
class Adapter(val mData: MutableList<MyDataModel>) : RecyclerView.Adapter<MyViewHolder>() {

    //устанавливаем внешний вид из layout xml файла
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_row, viewGroup, false))
    }

    //указываем, что происходит при загрузке данных
    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val data: MyDataModel
        data = mData[i]
        myViewHolder.R_ID.text = data.id
        myViewHolder.R_Firstname.text = data.firstname
        myViewHolder.R_Lastname.text = data.lastname
        myViewHolder.R_Group.text = data.gr
    }

    //указываем размер массива отображаемых данных
    override fun getItemCount(): Int {
        return mData.size
    }

    //назначаем ViewHolder
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var R_ID: TextView
        var R_Firstname: TextView
        var R_Lastname: TextView
        var R_Group: TextView

        init {
            R_ID = itemView.findViewById<View>(R.id.t_c1) as TextView
            R_Firstname = itemView.findViewById<View>(R.id.t_c2) as TextView
            R_Lastname = itemView.findViewById<View>(R.id.t_c3) as TextView
            R_Group = itemView.findViewById<View>(R.id.t_c4) as TextView
        }
    }

}