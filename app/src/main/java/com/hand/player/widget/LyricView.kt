package com.hand.player.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.hand.player.R

/**
 * @author  diaokaibin@gmail.com on 2019/4/13.
 */
class LyricView : View {

    val panint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }


    var viewW: Int = 0
    var viewH: Int = 0

    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var green = 0


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bigSize = resources.getDimension(R.dimen.bigSize)
        smallSize = resources.getDimension(R.dimen.smallSize)
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.green)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        panint.textSize = bigSize
        panint.color = green


        val text = "loadingLLLLLLLLLL"

        val bounds = Rect()
        panint.getTextBounds(text, 0, text.length, bounds)


        val textW = bounds.width()
        val textH = bounds.height()

        val x = viewW / 2 - textW / 2
        val y = viewH / 2 + textH / 2

        canvas?.drawText(text, x.toFloat(), y.toFloat(), panint)


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