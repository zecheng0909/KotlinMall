package com.cheng.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cheng.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerBtn.setOnClickListener {
            //            Toast.makeText(this,"注册",Toast.LENGTH_LONG).show()
//            toast("注册")
            startActivity(intentFor<TestActivity>("id" to 5))
        }
    }
}
