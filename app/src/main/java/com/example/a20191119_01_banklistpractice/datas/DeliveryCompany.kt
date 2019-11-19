package com.example.a20191119_01_banklistpractice.datas

import org.json.JSONObject

class DeliveryCompany {

    var id = 0
    var name = ""
    var logo = ""

    companion object{

        fun getDeliveryCompanyInfoJson(json: JSONObject) : DeliveryCompany{

            var dc =  DeliveryCompany()
            dc.id = json.getInt("id")
            dc.name = json.getString("name")
            dc.logo = json.getString("logo")

            return dc
        }

    }

}