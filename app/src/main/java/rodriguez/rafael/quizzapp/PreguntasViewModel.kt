package rodriguez.rafael.quizzapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG ="DesdeViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class PreguntasViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val bancoPreguntas = listOf(
        Preguntas(R.string.pregunta1, false),
        Preguntas(R.string.pregunta2, false),
        Preguntas(R.string.pregunta3, true),
        Preguntas(R.string.pregunta4, true),
    )
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val respuestaPreguntaActual: Boolean
        get() = bancoPreguntas[currentIndex].answer

    val textoPreguntaActual: Int
        get() = bancoPreguntas[currentIndex].textResId

    fun siguentePregunta() {
        currentIndex = (currentIndex + 1) % bancoPreguntas.size
    }
    fun anteriorPregunta() {
        if (currentIndex == 0)
            currentIndex = bancoPreguntas.size - 1
        else
            currentIndex = (currentIndex - 1) % bancoPreguntas.size
    }
}