package io.navendra.bingetime.views.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.navendra.bingetime.R
import io.navendra.bingetime.models.HomeFeedModel
import kotlin.properties.Delegates

class HomeFeedAdapter(context: Context, private val feedData : List<HomeFeedModel>) :
        RecyclerView.Adapter<HomeFeedAdapter.ViewHolder>(){

    private var localContext : Context by Delegates.notNull()

    init {
        this.localContext = context
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var itemImage: ImageView by Delegates.notNull()
        private var itemTitle: TextView by Delegates.notNull()
        private var itemDetail: TextView by Delegates.notNull()

        init {
            itemImage = itemView.findViewById(R.id.item_image) as ImageView
            itemTitle = itemView.findViewById(R.id.item_title) as TextView
            itemDetail = itemView.findViewById(R.id.item_detail) as TextView
        }

        fun setImage(imageId:Int){
            val drawable = localContext.getDrawable(imageId)
            itemImage.setImageDrawable(drawable)
        }

        fun setImage(drawable: Drawable){
            itemImage.setImageDrawable(drawable)
        }

        fun setTitle(title:String){
            itemTitle.text = title
        }

        fun setDetail(detail:String){
            itemDetail.text = detail
        }
    }

    override fun getItemCount(): Int {
        return feedData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_feed_card, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedModel : HomeFeedModel = feedData[position%feedData.size]
        holder.setImage(feedModel.imageId)
        holder.setTitle(feedModel.title)
        holder.setDetail(feedModel.content)
    }

}
