package uricer.edu.br.lista04

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.example.alunos.viadagemdossom.R

class CanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RenderView(this))
    }

    inner class RenderView(context: Context) : View (context) {

        var circulos: Int = 0
        var xx = FloatArray(0)
        var yy = FloatArray(0)
        var raio = 90f
        var paint = Paint()

        override fun onDraw(canvas: Canvas) {
            paint.color = Color.GREEN
            for(i in xx.indices){
                desenharCirculo(canvas, xx[i], yy[i], raio, paint)
            }
            invalidate()
            super.onDraw(canvas)
        }

        fun desenharCirculo(canvas : Canvas,
                            x : Float,
                            y : Float,
                            raio : Float,
                            paint : Paint) {
            canvas.drawCircle(x, y, raio, paint)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {

            when(event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    novoCirculo(event)
                }
            }

            return super.onTouchEvent(event)
        }

        private fun novoCirculo(event: MotionEvent) {
            var mp = MediaPlayer.create(this@CanvasActivity, R.raw.bigodudo_ou_careca)
            mp.start()
            mp.setOnCompletionListener {
                mp.release()
            }
            circulos++
            xx = xx.copyOf(circulos)
            yy = yy.copyOf(circulos)
            xx.set(circulos - 1, event.x)
            yy.set(circulos - 1, event.y)
            invalidate()
        }
    }
}
