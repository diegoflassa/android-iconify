package io.github.diegoflassa.iconivoid.internal

import android.widget.TextView
import androidx.core.view.ViewCompat

/**
 * Any TextView subclass that wishes to call [io.github.diegoflassa.iconivoid.IconiVoid.addIcons] on it
 * needs to implement this interface if it ever want to use spinning icons.
 * <br></br>
 * IconTextView, IconButton and IconToggleButton already implement it, but if you need to implement it
 * yourself, please use [io.github.diegoflassa.iconivoid.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate]
 * to help you.
 */
interface HasOnViewAttachListener {
    fun setOnViewAttachListener(listener: OnViewAttachListener?)
    interface OnViewAttachListener {
        fun onAttach()
        fun onDetach()
    }

    /**
     * Helper class to implement [HasOnViewAttachListener].
     * Usual implementation should look like this:
     * <pre>
     * `class MyClass extends TextView implements HasOnViewAttachListener {
     *
     * private HasOnViewAttachListenerDelegate delegate
     *
     *
     * public void setOnViewAttachListener(OnViewAttachListener listener) {
     * if (delegate == null) delegate = new HasOnViewAttachListenerDelegate(this);
     * delegate.setOnViewAttachListener(listener);
     * }
     *
     *
     * protected void onAttachedToWindow() {
     * super.onAttachedToWindow();
     * delegate.onAttachedToWindow();
     * }
     *
     *
     * protected void onDetachedFromWindow() {
     * super.onDetachedFromWindow();
     * delegate.onDetachedFromWindow();
     * }
     *
     * }
    ` *
    </pre> *
     */
    class HasOnViewAttachListenerDelegate(private val view: TextView) {
        private var listener: OnViewAttachListener? = null
        fun setOnViewAttachListener(listener: OnViewAttachListener?) {
            if (this.listener != null) this.listener!!.onDetach()
            this.listener = listener
            if (ViewCompat.isAttachedToWindow(view) && listener != null) {
                listener.onAttach()
            }
        }

        fun onAttachedToWindow() {
            if (listener != null) listener!!.onAttach()
        }

        fun onDetachedFromWindow() {
            if (listener != null) listener!!.onDetach()
        }
    }
}