package com.example.myhoho

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    //module-level variable
    private var like: Int = 0
    private var dislike: Int = 0


    //declare an instance of the SharedPref
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the Shared Pref
        // Now, we use getPreferences because this is only use in this activity !!! (will come out in exam)
        // if want to share and use in another activity, we use getSharedPreferences.
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)


        imageViewLike.setOnClickListener{
            like++
            textViewLike.text = like.toString()
        }

        imageViewDislike.setOnClickListener{
            dislike++
            textViewDislike.text = dislike.toString()
        }

        Log.d("MainActivity", "onCreate")
    }

    override fun onStart(){
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume(){
        Log.d("MainActivity", "onResume")

        //Retrieve the shared Pref
        like = sharedPreferences.getInt(getString(R.string.like),0)
        dislike = sharedPreferences.getInt(getString(R.string.dislike),0)

        textViewLike.text = like.toString()
        textViewDislike.text = dislike.toString()
        super.onResume()
    }

    override fun onStop(){
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    //onPause means the app is blocked by another UI, u can see your app but u cannot execute any output
    override fun onPause(){
        Log.d("MainActivity", "onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like),like)
            putInt(getString(R.string.dislike),dislike)
            apply()
        }
        super.onPause()
    }

    override fun onDestroy(){
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }

    //After finish, run the app and see. No matter how u kill the app, the like and dislike
    //still the same, this is because when u come out from the app, u sure will go through onPause() state, and
    //we already write the code to share the like and dislike data to onPause() state. Therefore,
    //no matter how u kill the app or even rotate the screen, the data is still the same.













}
