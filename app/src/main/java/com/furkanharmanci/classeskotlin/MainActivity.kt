package com.furkanharmanci.classeskotlin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.furkanharmanci.classeskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var data : SharedPreferences
    private lateinit var resultMessage : String
    private var errorMessage : String = "Empty error: Please enter a value!"
    private var sharedPackageName : String = "com.furkanharmanci.classeskotlin"
    private var dataPref : Int? = null
    private var prefCodeName : String = "input"
    private var prefDefaultCode : Int = -1
    private var myAge : Int? = null
    private var emptyAge = "Your age: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data = this.getSharedPreferences(sharedPackageName, MODE_PRIVATE)
        dataPref = data.getInt(prefCodeName, prefDefaultCode)
        if (dataPref == prefDefaultCode) {
            binding.resultText.text = errorMessage
        } else {
            resultMessage = "Your age: $dataPref"
            binding.resultText.text = resultMessage
        }

    }

    fun saveClicked(view : View) {
        myAge = binding.inputText.text.toString().toIntOrNull()
        if (myAge != null) {
            resultMessage = "Your age: $myAge"
            binding.resultText.text = resultMessage
            data.edit().putInt(prefCodeName, myAge!!).apply()
        } else {
            binding.resultText.text = errorMessage
        }
    }

    fun deleteClicked(view : View) {
        dataPref = data.getInt(prefCodeName, prefDefaultCode)
        if (dataPref != prefDefaultCode) {
            data.edit().remove(prefCodeName).apply()
            binding.resultText.text = emptyAge
        }
    }
}
