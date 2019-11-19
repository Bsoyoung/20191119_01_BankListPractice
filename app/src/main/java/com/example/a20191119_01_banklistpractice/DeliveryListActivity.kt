package com.example.a20191119_01_banklistpractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.a20191119_01_banklistpractice.adapters.BankAdapter
import com.example.a20191119_01_banklistpractice.adapters.DeliveryAdapter
import com.example.a20191119_01_banklistpractice.datas.Bank
import com.example.a20191119_01_banklistpractice.datas.DeliveryCompany
import com.example.a20191119_01_banklistpractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_delivery_list.*
import org.json.JSONObject

class DeliveryListActivity : BaseActivity() {

    var deliveryList = ArrayList<DeliveryCompany>()
    var deliveryAdapter:DeliveryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        deliveryAdapter = DeliveryAdapter(mContext,deliveryList)
        deliveryCompanyListView.adapter = deliveryAdapter

        getDeliveryListItem()
    }

    fun getDeliveryListItem(){

        ServerUtil.getRequestDeliveryList(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("응답확인",json.toString())
                val code = json.getInt("code")

                if(code==200){
                    val data = json.getJSONObject("data")
                    val delivery = data.getJSONArray("company")

                    for(i in 0 .. (delivery.length()-1)){
                        val deliveryJsonObject = delivery.getJSONObject(i)

                        val deliveryData = DeliveryCompany.getDeliveryCompanyInfoJson(deliveryJsonObject)
                        deliveryList.add(deliveryData)
                    }
                    runOnUiThread{
                        deliveryAdapter?.notifyDataSetChanged()
                    }

                }else{
                    Toast.makeText(mContext,"서버 통신에 문제가 있습니다", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }
}
