package rodriguez.rafael.quizzapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import virgen.juan.quizzapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: PreguntasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {view: View ->
            checkAnswer(true)
        }
        binding.button2.setOnClickListener{
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.siguentePregunta()
            updateQuestion()
        }
        binding.prevButton.setOnClickListener {
            quizViewModel.anteriorPregunta()
            updateQuestion()
        }

        updateQuestion()


        val questionTextResId = quizViewModel.textoPreguntaActual
        binding.questionTextView.setText(questionTextResId)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "pase por el onCreate")
        Log.d(TAG, "tengo un  QuizViewModel: $quizViewModel")
    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.textoPreguntaActual
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.respuestaPreguntaActual

        val messageResId = if(userAnswer == correctAnswer){
            R.string.toast_ok
        }else{
            R.string.toast_fail
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
    }

}