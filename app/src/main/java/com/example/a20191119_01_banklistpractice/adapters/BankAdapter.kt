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
import com.example.a20191119_01_banklistpractice.datas.Bank

class BankAdapter(context: Context,res:Int,list:ArrayList<Bank>) : ArrayAdapter<Bank>(context,res,list) {

    var mContext = context
    var mList = list

    var inf = LayoutInflater.from(mContext)

    constructor(context:Context,list:ArrayList<Bank>):this(context,R.layout.bank_list_item,list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow==null){
            tempRow = inf.inflate(R.layout.bank_list_item,null)
        }

        var row = tempRow!!

        var logoImg = row.findViewById<ImageView>(R.id.logoImg)
        var bankNameTxt = row.findViewById<TextView>(R.id.bankNameTxt)

        var data =mList.get(position)


        Glide.with(mContext).load(data.logo).into(logoImg)
        bankNameTxt.text = data.name





        return row
    }


}