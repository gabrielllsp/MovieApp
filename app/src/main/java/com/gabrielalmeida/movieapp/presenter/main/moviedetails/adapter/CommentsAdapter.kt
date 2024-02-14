package com.gabrielalmeida.movieapp.presenter.main.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielalmeida.movieapp.databinding.ItemCommentReviewBinding
import com.gabrielalmeida.movieapp.domain.model.MovieReview
import com.gabrielalmeida.movieapp.util.formatCommentDate


class CommentsAdapter : ListAdapter<MovieReview, CommentsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieReview>() {
            override fun areItemsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCommentReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)

        Glide
            .with(holder.binding.root.context)
            .load(review.authorDetails?.avatarPath)
            .into(holder.binding.imageUser)

        holder.binding.textUsername.text = review.authorDetails?.username
        holder.binding.textComment.text = review.content
        holder.binding.textRating.text = review?.authorDetails?.rating?.toString() ?: "0"
        holder.binding.textDate.text = formatCommentDate(review.createdAt)

    }

    inner class MyViewHolder(val binding: ItemCommentReviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}