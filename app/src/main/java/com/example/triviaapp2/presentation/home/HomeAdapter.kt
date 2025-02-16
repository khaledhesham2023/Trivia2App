package com.example.triviaapp2.presentation.home

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.triviaapp2.R
import com.example.triviaapp2.data.model.AnswerItem
import com.example.triviaapp2.databinding.RecyclerItemAnswerBinding
import java.util.ArrayList

class HomeAdapter(val context: Context, private val oldData: ArrayList<AnswerItem>, val onRecycleNotified: (Int) -> Unit) : Adapter<HomeAdapter.HomeViewHolder>() {

    private var selectedItemIndex: Int? = null
    private var selectedItem: AnswerItem? = null
    var isDisabled: Boolean = true

    inner class HomeViewHolder(val binding: RecyclerItemAnswerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item_answer,parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.answer.text = Html.fromHtml(oldData[position].answer)
        holder.binding.selector.isVisible = oldData[position].isSelected == true
        holder.binding.answer.background = if (oldData[position].isSelected) ContextCompat.getDrawable(context,R.drawable.bg_sheet_selected) else ContextCompat.getDrawable(context,R.drawable.bg_sheet)
        holder.binding.root.setOnClickListener {
            if (oldData[position].isSelected){
                unSelectItem(oldData[position])
            } else {
                if (selectedItem != null && selectedItemIndex != position){
                    selectedItem?.let { unSelectItem(it) }
                    selectItem(oldData[position],position)
                } else {
                    selectItem(oldData[position],position)
                }
            }
        }
    }

    private fun selectItem(item: AnswerItem, position: Int) {
        isDisabled = false
        selectedItem = item
        item.isSelected = true
        selectedItemIndex = position
        selectedItemIndex?.let { onRecycleNotified(it) }
    }

    private fun unSelectItem(item: AnswerItem) {
        isDisabled = true
        item.isSelected = false
        selectedItemIndex?.let { onRecycleNotified(it) }
        selectedItem = null
        selectedItemIndex = null
    }

    override fun getItemCount(): Int = oldData.size

    fun updateAnswers(newData: ArrayList<AnswerItem>) {
        val answersUtils = AnswersUtils(oldData,newData)
        val diffResult = DiffUtil.calculateDiff(answersUtils)
        oldData.clear()
        oldData.addAll(newData)
        diffResult.dispatchUpdatesTo(this)

    }
}
class AnswersUtils(private val oldData: ArrayList<AnswerItem>, private val newData: ArrayList<AnswerItem>) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData == newData
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size
}