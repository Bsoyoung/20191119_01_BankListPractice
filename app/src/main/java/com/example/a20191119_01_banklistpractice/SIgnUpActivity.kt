package com.example.a20191119_01_banklistpractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*

class SIgnUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        pwEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("텍스트 변경",s.toString())

                //0글자 : #EFEFEF
                //6글자 미만 : #FF0000
                //그 이상 : #00FF00

                if(s.toString().length==0)
                {
                    passwordStatusTxt.setTextColor(Color.parseColor("#EFEFEF"))
                    passwordStatusTxt.text = " 비밀번호를 입력해주세요."
                }else if(s.toString().length<6){
                    passwordStatusTxt.setTextColor(Color.parseColor("#FF0000"))
                    passwordStatusTxt.text = "길이가 너무 짧습니다."
                }else{
                    passwordStatusTxt.setTextColor(Color.parseColor("#00FF00"))
                    passwordStatusTxt.text = "사용해도 좋을 비밀번호입니다."
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    override fun setValues() {
    }

}
