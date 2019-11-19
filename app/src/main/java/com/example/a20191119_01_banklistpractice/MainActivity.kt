package com.example.a20191119_01_banklistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a20191119_01_banklistpractice.adapters.BankAdapter
import com.example.a20191119_01_banklistpractice.datas.Bank
import com.example.a20191119_01_banklistpractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    var bankList = ArrayList<Bank>()
    var bankAdapter:BankAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        bankAdapter = BankAdapter(mContext,bankList)
        bankListView.adapter= bankAdapter

        getBanksFromServer()
    }
    fun getBanksFromServer(){
        
        ServerUtil.getRequestBankList(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("응답확인",json.toString())
                val code = json.getInt("code")

                if(code==200){
                    val data = json.getJSONObject("data")
                    val banks = data.getJSONArray("banks")

                    for(i in 0 .. (banks.length()-1)){
                        val bankJsonObject = banks.getJSONObject(i)

                        val bankData = Bank.getBankFromJsonObject(bankJsonObject)
                        bankList.add(bankData)
                    }
                    runOnUiThread{
                        bankAdapter?.notifyDataSetChanged()
                    }

                }else{
                    Toast.makeText(mContext,"서버 통신에 문제가 있습니다",Toast.LENGTH_SHORT).show()
                }
            }

        })

    }
}
