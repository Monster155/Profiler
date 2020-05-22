package ru.itlab.profilerk

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity() {

    private var people: ArrayList<Person>? = null
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val regexEmail: Regex = Regex(
        "([0-9a-zA-Z]+[\\.\\-_]?)*[0-9a-zA-Z]+" +
                "@([0-9a-zA-Z]+[\\.\\-_]?)+\\.[0-9a-zA-Z]+"
    )
    private val regexPassword: Regex = Regex("[a-zA-Z0-9]+")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        people = ArrayList<Person>()
        createPersons()

        val intent = Intent(this, ProfilerActivity::class.java)

        preferences = getSharedPreferences("Prefs", MODE_PRIVATE)
        editor = preferences!!.edit()
        if (preferences!!.contains("user")) {
            startActivity(intent)
        }

        signInButton!!.setOnClickListener { v ->
            //check for correct
            //check in array
            val email = emailText!!.text.toString()
            val password = passwordText!!.getText().toString()
            for (p in people!!) {
                if (regexEmail.matches(email) && password.length >= 6 && regexPassword.matches(password)) {
                    if (p.getLogpass().containsKey(email)) {
                        if (p.getLogpass().get(email).equals(password)) {
                            editor!!.putString("user", p.getName())
                            editor!!.putString("surname", p.getSurname())
                            editor!!.putString("email", email)
                            editor!!.putInt("image", p.getDrawable())
                            editor!!.apply()
                            //enter
                            startActivity(intent)
                            break
                        } else {
                            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(getContext(), "Incorrect e-mail", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getContext(): Context {
        return this
    }

    private fun createPersons() {
        people!!.add(Person("Da", "Da", "t@m.r", "iT2", R.drawable.img1))
        people!!.add(
            Person(
                "Damir",
                "Davletshin",
                "damir180501@mail.ru",
                "helloItsMe228",
                R.drawable.img1
            )
        )
        people!!.add(
            Person(
                "Daniyar",
                "Zakiev",
                "daniyar228@gmail.com",
                "bulyaPetuh1337",
                R.drawable.img2
            )
        )
        people!!.add(
            Person(
                "Rinat",
                "Ahmethanov",
                "rinatTojePetuh13@kto.tam",
                "Anikogo9",
                R.drawable.img3
            )
        )
        people!!.add(
            Person(
                "Bulya",
                "Pes",
                "YanePetuh@eto.rinat",
                "chestnoRinat28",
                R.drawable.img4
            )
        )
    }
}
