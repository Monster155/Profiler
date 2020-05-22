package ru.itlab.profilerk

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfilerActivity : Activity() {
    private var preferences: SharedPreferences? = null
    private var editor: Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        preferences = getSharedPreferences("Prefs", MODE_PRIVATE)
        editor = preferences!!.edit()

        val intent = Intent(this, MainActivity::class.java)
        button.setOnClickListener {
            editor!!.clear()
            editor!!.apply()
            startActivity(intent)
        }
        imageView.setImageDrawable(getDrawable(preferences!!.getInt("image", 0)))
        textView.text = preferences!!.getString("email", "")
        textView2.text = preferences!!.getString("user", "") +
                " " + preferences!!.getString("surname", "")
    }
}