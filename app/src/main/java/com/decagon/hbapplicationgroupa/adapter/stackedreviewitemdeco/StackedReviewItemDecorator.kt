package com.decagon.hbapplicationgroupa.adapter.stackedreviewitemdeco

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This class is responsible for the overlapping of the items in StackedReviewAdapter
 */
class StackedReviewItemDecorator : RecyclerView.ItemDecoration() {

    private val horizontalOverlap = -30

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0,0,horizontalOverlap, 0)
    }
}