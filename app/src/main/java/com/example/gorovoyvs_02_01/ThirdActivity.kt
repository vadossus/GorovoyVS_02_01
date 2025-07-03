package com.example.gorovoyvs_02_01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class ThirdActivity : AppCompatActivity() {

    private lateinit var res: EditText
    private lateinit var res2: EditText
    private lateinit var button: AppCompatButton
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        button = findViewById(R.id.return_back)

        res = findViewById(R.id.result1)
        res2 = findViewById(R.id.result2)

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)

        loadData()

        button.setOnClickListener{
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove("password")
            editor.remove("login")
            editor.remove("result")
            editor.remove("figure")
            editor.apply()

            go_back()
        }



    }


    private fun loadData()
    {
        var result1 = sharedPreferences.getString("result", "")
        var result2 = sharedPreferences.getString("figure", "")

        res.setText(result1.toString())
        res2.setText(result2.toString())
    }

    public fun go_back()
    {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}