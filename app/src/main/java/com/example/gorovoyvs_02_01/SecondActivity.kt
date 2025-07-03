package com.example.gorovoyvs_02_01

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.PI

class SecondActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: AppCompatButton
    private lateinit var info_edit: EditText
    private lateinit var info_edit2: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spinner: Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.result_button)
        info_edit = findViewById(R.id.add_info)
        info_edit2 = findViewById(R.id.add_info2)
        imageView = findViewById(R.id.imageView2)

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (spinner.selectedItem.toString())
                {
                    "Круг" -> {
                        info_edit2.visibility = View.INVISIBLE
                        var image = R.drawable.circle
                        imageView.setImageResource(image)
                    }
                    "Треугольник" -> {
                        info_edit2.visibility = View.VISIBLE
                        var image = R.drawable.perim_treug2
                        imageView.setImageResource(image)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        button.setOnClickListener{
            if (info_edit.text.toString().isNotEmpty())
            {
                when (spinner.selectedItem.toString()) {
                    "Круг" -> {
                        var a = info_edit.text.toString().toInt()

                        var result = a / 2 * PI;

                        sharedPreferences.edit().apply()
                        {
                            putString("result", result.toString())
                            putString("figure", "Круг")
                            apply()
                        }
                        nextIntent()
                    }
                }
            }
            else if (info_edit.text.toString().isNotEmpty() && info_edit2.visibility == View.VISIBLE && info_edit2.text.toString().isNotEmpty())
            {
                when (spinner.selectedItem.toString())
                {
                    "Треугольник" -> {
                        var a = info_edit.text.toString().toInt()
                        var b = info_edit2.text.toString().toInt()

                        var result = (2 * a) + b;

                        sharedPreferences.edit().apply()
                        {
                            putString("result", result.toString())
                            putString("figure", "Треугольник")
                            apply()
                        }
                        nextIntent()
                    }
                }
            }
            else
            {
                Snackbar.make(it, "Ошибка: Не введены данные", Snackbar.LENGTH_SHORT).show()
            }

        }

    }

    public fun nextIntent()
    {
        var intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }
}