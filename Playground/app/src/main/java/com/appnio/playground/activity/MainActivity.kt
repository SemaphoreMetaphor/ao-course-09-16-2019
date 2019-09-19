package com.appnio.playground.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appnio.playground.R
import com.appnio.playground.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        val loginButton: Button = findViewById(R.id.button)
        loginButton.setOnClickListener {
            val usernameString = username.text.toString()
            val passwordString = password.text.toString()

            if (!isUsernameValid(usernameString)) {
                username.error = "Username not valid"
            } else if (!isPasswordValid(passwordString)) {
                password.error = "Invalid password"
            } else {
//                val secondActivityIntent = Intent(this, SecondActivity::class.java)
//                secondActivityIntent.putExtra(SecondActivity.USERNAME_KEY, usernameString)
//                secondActivityIntent.putExtra(SecondActivity.PASSWORD_KEY, passwordString)
//                startActivity(secondActivityIntent)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ListFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    fun isUsernameValid(username: String) = username.isNotEmpty()

    fun isPasswordValid(password: String) = password.length > 4
}
