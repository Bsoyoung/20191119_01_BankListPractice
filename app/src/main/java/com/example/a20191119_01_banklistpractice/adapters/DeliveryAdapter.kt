package com.example.a20191119_01_banklistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.a20191119_01_banklistpractice.R
import com.example.a20191119_01_banklistpractice.datas.DeliveryCompany

class DeliveryAdapter(context:Context,res:Int,list:ArrayList<DeliveryCompany>):ArrayAdapter<DeliveryCompany>(context,res,list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context,list:ArrayList<DeliveryCompany>):this(context,R.layout.delivery_list_item,list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow==null)
        {
            tempRow = inf.inflate(R.layout.delivery_list_item,null)
        }

        var row = tempRow!!

        var deliverylogoImg = row.findViewById<ImageView>(R.id.delivieryLogoImg)
        var deliveryName = row.findViewById<TextView>(R.id.deliveryName)

        var data = mList.get(position)

        Glide.with(mContext).load(data.logo).into(deliverylogoImg)
        deliveryName.text = data.name

        return row
    }




}