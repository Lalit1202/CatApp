package com.example.catapp.cat.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.R
import com.example.catapp.cat.data.model.CatData
import com.example.catapp.cat.item.CatListGridAdaptor
import com.example.catapp.cat.item.OnItemClickListener
import com.example.catapp.cat.vm.CatVM
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.util.PaginationScrollListener
import com.example.catapp.util.bindImage
import kotlinx.android.synthetic.main.fragment_cat.*
import kotlinx.android.synthetic.main.grid_view_item.*

class CatFragment : Fragment(), OnItemClickListener {


    private var currentAnimator: Animator? = null
    private var shortAnimationDuration: Int = 0
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val viewModel by viewModels<CatVM>()

    companion object {
        @JvmStatic
        fun newInstance() = CatFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCatBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = CatListGridAdaptor(this)
        val layoutManger = binding.photosGrid.layoutManager as GridLayoutManager

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        binding.photosGrid.addOnScrollListener(object : PaginationScrollListener(layoutManger) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = false
                viewModel.fetchCatData()
            }

        })

        return binding.root

    }


    override fun onItemClicked(catdata: CatData, itemView: View) {

        photos_grid.visibility = View.INVISIBLE
        // imageview.visibility = View.VISIBLE
        val imgUrl = catdata.url
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imageview.context)
                .load(imgUri)
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageview)


            ZoomImage(itemView)




        }





    }

    fun ZoomImage(view: View) {
        currentAnimator?.cancel()

        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()
        view.getGlobalVisibleRect(startBoundsInt)
        photos_grid.getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)


        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        view.alpha = 0f
        imageview.visibility = View.VISIBLE
        imageview.pivotX = 0f
        imageview.pivotY = 0f


        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    imageview,
                    View.X,
                    startBounds.left,
                    finalBounds.left
                )
            ).apply {
                with(ObjectAnimator.ofFloat(imageview, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(imageview, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(imageview, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }
        imageview.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(imageview, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(imageview, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(imageview, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(imageview, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        view.alpha = 1f
                        imageview.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        view.alpha = 1f
                        imageview.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }

            photos_grid.visibility = View.VISIBLE


        }


    }
}

