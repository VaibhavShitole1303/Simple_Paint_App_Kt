package com.example.simple_paint_app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.simple_paint_app.MainActivity.Companion.paintBrush
import com.example.simple_paint_app.MainActivity.Companion.paths
import com.example.simple_paint_app.Paint_View.Companion.colorList
import com.example.simple_paint_app.Paint_View.Companion.pathList
import java.nio.file.Path

class Paint_View: View{
    var params:ViewGroup.LayoutParams?=null// this param is basically responsible for our convas with respect to parent layout
    companion object{
        var pathList=ArrayList<android.graphics.Path>()
       // var pathList=ArrayList<Path>()// pathlist will store all the path we have Draw in the view
        var colorList=ArrayList<Int>()// this will store the colors we used
        // we use Int because colors having type integer
        var currentBrush=Color.BLACK //this store the default color i.e black
    }
    // this are constructor of view in android kotlin which we can take from net
   constructor(context: Context) : this(context, null){
     init()
   }
   constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
     init()
   }

constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    init()

   }
    // method to initialize the path i.e to initialize the brush
    private fun init(){
        paintBrush.isAntiAlias=true//  isAntiAlias is to make the texture of strokes smooth
        paintBrush.color= currentBrush// setting the color to paintbrush what is to the currentBrush
        paintBrush.style=Paint.Style.STROKE// this is style of our paint brush
        paintBrush.strokeJoin=Paint.Join.ROUND//strokeJoin is basically end of any path we set it to round so at path end with round finish
        paintBrush.strokeWidth=8f// setting width of brush
       params=ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)// this param is basically responsible for our convas with respect to parent layout

    }
    // registering the moment of user on screen to get the correct info we override
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x =event.x
        var y=event.y
        when(event.action){
            MotionEvent.ACTION_DOWN-> {
                paths.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE->{
                paths.lineTo(x,y)
                pathList.add(paths)
                colorList.add(currentBrush)
            }
            else-> return  false
        }
        postInvalidate()
        return false
    }
    //to draw by this touch event we have to override
    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush) // by this we draw
            invalidate()
        }
    }

}