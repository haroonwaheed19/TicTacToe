//package com.hwm.tictactoe
//
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.RadioButton
//import android.widget.RadioGroup
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.cardview.widget.CardView
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var cvUser: CardView
//    private lateinit var cvGrid: CardView
//    private lateinit var choice1: RadioButton
//    private lateinit var choice2: RadioButton
//    private lateinit var rdChoice: RadioGroup
//    private lateinit var start: Button
//    private lateinit var clear: Button
//    private lateinit var btn1: Button
//    private lateinit var btn2: Button
//    private lateinit var btn3: Button
//    private lateinit var btn4: Button
//    private lateinit var btn5: Button
//    private lateinit var btn6: Button
//    private lateinit var btn7: Button
//    private lateinit var btn8: Button
//    private lateinit var btn9: Button
//    private var cnt = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        btn1 = findViewById(R.id.btn1)
//        btn2 = findViewById(R.id.btn2)
//        btn3 = findViewById(R.id.btn3)
//        btn4 = findViewById(R.id.btn4)
//        btn5 = findViewById(R.id.btn5)
//        btn6 = findViewById(R.id.btn6)
//        btn7 = findViewById(R.id.btn7)
//        btn8 = findViewById(R.id.btn8)
//        btn9 = findViewById(R.id.btn9)
//        choice1 = findViewById(R.id.rbtnX)
//        choice2 = findViewById(R.id.rdbO)
//        rdChoice = findViewById(R.id.rdgChoice)
//        start = findViewById(R.id.btnStart)
//        clear = findViewById(R.id.btnClear)
//        cvUser = findViewById(R.id.cvUserChoice)
//        cvGrid = findViewById(R.id.cvGrid)
//
//        start.setOnClickListener {
//            if (choice1.isChecked || choice2.isChecked) {
//                cvUser.visibility = View.INVISIBLE
//                cvGrid.visibility = View.VISIBLE
//                clear.visibility = View.VISIBLE
//            } else {
//                // Show a message or alert to prompt the user to make a choice
//                Toast.makeText(this, "Please select X or O", Toast.LENGTH_LONG).show()
//            }
//        }
//
//    }
//
//    private fun Check(view:V){
//        val btn = view as Button
//        if(cnt==0){
//            if(choice1.isChecked)
//            {
//                btn.text="X"
//                cnt++;
//            }
//        }
//
//    }
//
//}





package com.hwm.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var  b1: String
    private lateinit var  b2: String
    private lateinit var  b3: String
    private lateinit var  b4: String
    private lateinit var  b5: String
    private lateinit var  b6: String
    private lateinit var  b7: String
    private lateinit var  b8: String
    private lateinit var  b9: String
    private lateinit var cvUser: CardView
    private lateinit var cvGrid: CardView
    private lateinit var choice1: RadioButton
    private lateinit var choice2: RadioButton
    private lateinit var rdChoice: RadioGroup
    private lateinit var start: Button
    private lateinit var clear: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private var cnt = 0
    private var currentPlayer = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize buttons and views
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        choice1 = findViewById(R.id.rbtnX)
        choice2 = findViewById(R.id.rdbO)
        rdChoice = findViewById(R.id.rdgChoice)
        start = findViewById(R.id.btnStart)
        clear = findViewById(R.id.btnClear)
        cvUser = findViewById(R.id.cvUserChoice)
        cvGrid = findViewById(R.id.cvGrid)

        // Start the game when the "Start" button is clicked
        start.setOnClickListener {
            if (choice1.isChecked || choice2.isChecked) {
                cvUser.visibility = View.INVISIBLE
                cvGrid.visibility = View.VISIBLE
                clear.visibility = View.VISIBLE
                currentPlayer = if (choice1.isChecked) "X" else "O"
            } else {
                // Show a message if the user hasn't selected X or O
                Toast.makeText(this, "Please select X or O", Toast.LENGTH_LONG).show()
            }
        }

        // Clear the game grid when the "Clear" button is clicked
        clear.setOnClickListener {
            resetGame()
        }
    }

    // Initialize game by setting up button click listeners
    public fun initializeGame(view:View) {
        val btn = view as Button
        if(btn.text=="")
        {
            cnt++
            btn.text = currentPlayer
            if(currentPlayer=="X") {
                btn.setBackgroundColor(getColor(R.color.red))
            }
            else{
                btn.setBackgroundColor(getColor(R.color.text_Hint))
            }

            if(checkWinner())
            {
                Toast.makeText(this, "$currentPlayer wins!", Toast.LENGTH_LONG).show()
                disableGrid()
//                Toast.makeText(this, "Press Clear Button to Restart Game", Toast.LENGTH_LONG).show()
                lifecycleScope.launch {
                    delay(4000)
                    resetGame()
                }
            }
            //Game is Drawn
            else if(cnt==9)
            {
                disableGrid()
                Toast.makeText(this, "Game is Drawn", Toast.LENGTH_LONG).show()
                lifecycleScope.launch {
                    delay(4000)
                    resetGame()
                }
            }
            else
            {
                if(currentPlayer=="X") {
                    currentPlayer = "O"
                }
                else
                {
                    currentPlayer = "X"
                }
            }

        }

    }

    // Disable the grid after the game ends
    private fun disableGrid() {
        val buttons = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        buttons.forEach { it.isClickable = false }
    }

    // Reset the game to the initial state
    private fun resetGame() {
        val buttons = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        buttons.forEach { button ->
            button.text = ""
            button.isClickable = true
            button.setBackgroundColor(getColor(R.color.button_color))
        }
        cvUser.visibility = View.VISIBLE
        cvGrid.visibility = View.INVISIBLE
        clear.visibility = View.INVISIBLE
        rdChoice.clearCheck()
        cnt = 0
    }

    // Check if there is a winner
    private fun checkWinner(): Boolean {
        b1 = btn1.text.toString()
        b2 = btn2.text.toString()
        b3 = btn3.text.toString()
        b4 = btn4.text.toString()
        b5 = btn5.text.toString()
        b6 = btn6.text.toString()
        b7 = btn7.text.toString()
        b8 = btn8.text.toString()
        b9 = btn9.text.toString()

        //checking for Rows
        if(b1==b2 && b2==b3 && b1.isNotEmpty())
        {
            return true
        }
        else if(b4==b5 && b5==b6 && b4.isNotEmpty())
        {
            return true
        }
        else if(b7==b8 && b8==b9 && b7.isNotEmpty())
        {
            return true
        }

        //checking for columns
        if(b1==b4 && b4==b7 && b1.isNotEmpty()){
            return true
        }
        else if(b2==b5 && b5==b8 && b2.isNotEmpty()){
            return true
        }
        else if(b3==b6 && b6==b9 && b3.isNotEmpty()){
            return true
        }

        //checking for Diagonals
        if(b1==b5 && b5==b9 && b1.isNotEmpty()){
            return true
        }

        else if(b3==b5 && b5==b7 && b3.isNotEmpty()){
            return true
        }

        return false
    }

}
