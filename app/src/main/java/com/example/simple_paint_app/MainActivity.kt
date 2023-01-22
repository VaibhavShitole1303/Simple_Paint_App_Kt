package com.example.simple_paint_app

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simple_paint_app.Paint_View.Companion.colorList
import com.example.simple_paint_app.Paint_View.Companion.currentBrush
import com.example.simple_paint_app.Paint_View.Companion.pathList
import com.example.simple_paint_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //to have to draw we need the path that the pointer follows
    // and the paintbrush to apply the color
    // we to access this path and paintbrush in paintview to to declear them globally in java we do
    //that by public  static key but in kotlin we use the companion object and which is in it can access all over the application
    companion object{
        var paths=Path()
        var paintBrush=Paint()
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        supportActionBar?.hide()
    }

    private fun initListener() {
       binding.btnredColor.setOnClickListener{
           paintBrush.color = Color.RED
           currentColor(paintBrush.color)
      Toast.makeText(this,"clicked red", Toast.LENGTH_LONG).show()
       }
        binding.btnblueColor.setOnClickListener{
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
            Toast.makeText(this,"clicked blue", Toast.LENGTH_LONG).show()
        }
        binding.btnblackColor.setOnClickListener{
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
            Toast.makeText(this,"clicked black", Toast.LENGTH_LONG).show()
        }
        binding.btnEraser.setOnClickListener{
            pathList.clear()
            colorList.clear()
            paths.reset()
            Toast.makeText(this,"clicked Eraser", Toast.LENGTH_LONG).show()
        }

    }
    private fun currentColor(color: Int){
        currentBrush=color
        paths=Path()
    }
}