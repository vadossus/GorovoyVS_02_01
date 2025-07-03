package com.example.gorovoyvs_02_01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var button: AppCompatButton
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        button = findViewById(R.id.sumbit)

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)

        loadData()

        button.setOnClickListener{
            var log = login.text.toString()
            var password = password.text.toString()

            if (password.isNotEmpty() && log.isNotEmpty())
            {
                if (log == "ects" && password == "ects2024")
                {
                    sharedPreferences.edit().apply()
                    {
                        putString("login", log.toString())
                        putString("password", password.toString())
                        apply()
                    }
                    Snackbar.make( it, "Успешный вход", Snackbar.LENGTH_SHORT).show()
                    go_to_next_view()
                }
                else
                {
                    Snackbar.make( it, "Неверный пароль", Snackbar.LENGTH_SHORT).show()
                }
            }
            else
            {
                Snackbar.make(it, "Введите данные", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    public fun go_to_next_view()
    {
        var intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun loadData()
    {
        var log = sharedPreferences.getString("login","")
        var pass = sharedPreferences.getString("password", "")

        login.setText(log.toString())
        password.setText(pass.toString())
    }


}