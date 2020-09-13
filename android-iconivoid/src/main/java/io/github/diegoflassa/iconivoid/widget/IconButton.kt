package io.github.diegoflassa.iconivoid.widget

import android.content.Context
import android.util.AttributeSet
import io.github.diegoflassa.iconivoid.IconiVoid
import androidx.appcompat.widget.AppCompatButton
import io.github.diegoflassa.iconivoid.internal.HasOnViewAttachListener
import io.github.diegoflassa.iconivoid.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate
import io.github.diegoflassa.iconivoid.internal.HasOnViewAttachListener.OnViewAttachListener

class IconButton : AppCompatButton, HasOnViewAttachListener {
    private var delegate: HasOnViewAttachListenerDelegate? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        transformationMethod = null
    }

    override fun setText(text: CharSequence, type: BufferType) {
        super.setText(IconiVoid.compute(context, text, this), type)
    }

    override fun setOnViewAttachListener(listener: OnViewAttachListener?) {
        if (delegate == null) delegate = HasOnViewAttachListenerDelegate(this)
        delegate!!.setOnViewAttachListener(listener)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        delegate!!.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        delegate!!.onDetachedFromWindow()
    }
}