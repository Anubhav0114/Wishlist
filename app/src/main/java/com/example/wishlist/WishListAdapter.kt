package com.example.wishlist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter(private val context : Context  ) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {



    private var itemClickListener:((position:Int , url : String )->Unit)? = null
    private val itemData = ArrayList<Item>()


    fun setOnClickListener(callback:(position:Int , url :String)->Unit){
        itemClickListener = callback
    }


    fun addItem(item: Item , position: Int){
        itemData.add(position , item)
        notifyItemInserted(position)
    }


    private var longListener: ((position : Int) -> Unit) ? = null

    fun longClickListener(callback: (position: Int) -> Unit){
        longListener = callback
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productName : TextView   = itemView.findViewById(R.id.tv_name)
        val productPrice : TextView =  itemView.findViewById(R.id.item_price)
        val productUrl : TextView = itemView.findViewById(R.id.tv_url)
        val parent : ConstraintLayout = itemView.findViewById(R.id.parent_layout)


        init {
            productUrl.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.invoke(position , productUrl.text.toString())
                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val item = inflater.inflate(R.layout.item_rv , parent , false)



        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return itemData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = itemData[position]

        holder.productName.text = product.productName
        holder.productPrice.text = product.price
        holder.productUrl.text = product.url

//        holder.productUrl.setOnClickListener {
//            //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://flax-studio.vercel.app"))
//            //startActivity(browserIntent)
//        }

    }
}