package com.cheng.user.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cheng.user.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerBtn.setOnClickListener {
            Toast.makeText(this,"注册",Toast.LENGTH_LONG).show()
        }

    }
}
