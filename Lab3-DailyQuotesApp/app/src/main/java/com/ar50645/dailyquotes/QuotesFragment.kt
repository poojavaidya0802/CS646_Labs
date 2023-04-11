package com.ar50645.dailyquotes

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.abs

const val QUOTE_STATE = "quoteState"

class QuotesFragment: Fragment() {
    private var initTouchY = 0
    private var quoteIndex = 0

    private lateinit var quoteTextView: TextView
    var quotesArray: Array<String> = emptyArray()

    companion object {
        fun newInstance() = QuotesFragment()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.context_menu, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val parentView = inflater.inflate(R.layout.fragment_quote, container, false)
        quoteTextView = parentView.findViewById(R.id.jokeTextView)
        quotesArray = getResources().getStringArray((R.array.quotes))

        registerForContextMenu(quoteTextView)

        // Moving finger up or down changes jokes
        parentView.setOnTouchListener { v, event ->
            var returnVal = true
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initTouchY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    val y = event.y.toInt()

                    // See if movement is at least 500 pixels
                    if (abs(y - initTouchY) >= 300) {
                        if (y > initTouchY) {
                            quoteIndex--
                            updateQuotes()
                        } else {
                            quoteIndex++
                            updateQuotes()
                        }
                        initTouchY = y
                    }
                }
                else -> returnVal = false
            }
            returnVal
        }

        return parentView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(QUOTE_STATE, quoteIndex)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            updateQuotes()
        } else {
            quoteIndex = savedInstanceState.getInt(QUOTE_STATE)
            updateQuotes()
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.next -> {
                quoteIndex++
                updateQuotes()
                true
            }
            R.id.prev -> {
                quoteIndex--
                updateQuotes()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun updateQuotes() {
        if(quoteIndex < 0 || quoteIndex >= quotesArray.size) {
            Toast.makeText(activity, "no more jokes to display", Toast.LENGTH_SHORT).show()
            return
        }
        quoteTextView.text = quotesArray.get(quoteIndex)
    }
}