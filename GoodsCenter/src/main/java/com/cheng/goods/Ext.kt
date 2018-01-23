package com.cheng.goods

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton

/**
 * User: Cheng
 * Date: 2018-01-23
 * Time: 23:38
 * Describe:
 */

/**
 * 获取NumberButton里的EditText
 */
fun NumberButton.getEditText():EditText{
    return find(R.id.text_count)
}