package com.hand.player.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.hand.player.R
import com.hand.player.model.LrcBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/13.
 */
class LyricView : View {

    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }


    val list by lazy { ArrayList<LrcBean>() }

    var centerLine = 10

    var viewW: Int = 0
    var viewH: Int = 0

    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var green = 0
    var lineHeight = 0


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bigSize = resources.getDimension(R.dimen.bigSize)
        smallSize = resources.getDimension(R.dimen.smallSize)
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.green)

        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)
        //
        paint.textAlign = Paint.Align.CENTER

        //循环添加歌词
        for (i in 0 until 29) {
            list.add(LrcBean(2000 * i, "在在播放第$i 行歌词"))
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        drawSingleLine(canvas)
        drawMultiLine(canvas)
    }

    /***
     * 绘制多行居中
     */
    private fun drawMultiLine(canvas: Canvas?) {
        val centerText = list[centerLine].content
        val bounds = Rect()

        paint.getTextBounds(centerText, 0, centerText.length, bounds)

        val textH = bounds.height()

        // 居中行y
        val centerY = viewH / 2 + textH / 2

        for ((index, value) in list.withIndex()) {
            if (index == centerLine) {

                paint.color = green
                paint.textSize = bigSize

            } else {
                paint.color = white
                paint.textSize = smallSize
            }

            val curX = viewW / 2
            val curY = centerY + (index - centerLine) * lineHeight
            val curText = list[index].content
            canvas?.drawText(curText, curX.toFloat(), curY.toFloat(), paint)

        }


    }

    /**
     * 绘制单行居中
     */
    private fun drawSingleLine(canvas: Canvas?) {
        paint.textSize = bigSize
        paint.color = green


        val text = "loadingLLLLLLLLLL"

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)


        val textW = bounds.width()
        val textH = bounds.height()

        //        val x = viewW / 2 - textW / 2
        val y = viewH / 2 + textH / 2

        canvas?.drawText(text, viewW / 2f, y.toFloat(), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewW = w
        viewH = h

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }
}