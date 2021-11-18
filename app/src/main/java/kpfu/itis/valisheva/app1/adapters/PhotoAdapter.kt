package kpfu.itis.valisheva.app1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.RequestManager
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.databinding.CardviewImgBinding

class PhotoAdapter(
    private val glide: RequestManager,
    private val photosList:List<String>)
    : PagerAdapter() {

    private var binding: CardviewImgBinding? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.cardview_img,container,false)
        binding = CardviewImgBinding.bind(view)
        with(binding){
            glide.load(photosList[position]).into(this?.cardImg!!)
            cardImg
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        binding = null
    }

    override fun getCount(): Int {
        return photosList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}

