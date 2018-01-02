package com.cheng.user.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cheng.user.R
import org.jetbrains.anko.toast

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        toast("${intent.getIntExtra("id",-1)}")
    }
}
