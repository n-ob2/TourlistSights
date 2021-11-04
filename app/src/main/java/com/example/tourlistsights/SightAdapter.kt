package com.example.tourlistsights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SightAdapter (
    private val context: Context,   //JSONデータ内imageNameと同じ名前の画像を取得する処理で使用
    private val sights: List<Sight> //JSONデータのテキストが格納されている
): RecyclerView.Adapter<SightAdapter.ViewHolder>(){

    private var listener: ((Int) -> Unit)? = null   //クリックリスナーコールバックを保持
    fun setOnItemClickListener(listener: (Int) -> Unit){    //クリックしたセルの値を受けとって詳細画面フラグメントを開くラムダ式
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){   //セルに使用する各ビューを保持
        val name: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.image)
        val description: TextView = view.findViewById(R.id.description)
        val kind: TextView = view.findViewById(R.id.kind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)   //cardlayout.xmlの使用を定義
        return ViewHolder(view)
    }   //onCreateViewHolder↑↑

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {  //実際表示するコンテンツの設定（1セル分）
        holder.name.text = sights[position].name
        holder.description.text = sights[position].descripttion
        holder.kind.text = sights[position].kind
        var imageResource = context.resources.getIdentifier(
            sights[position].imageName,
            "drawable", context.packageName)    //画像名から画像のリソースIDを取得
        holder.image.setImageResource(imageResource)
        holder.itemView.setOnClickListener {
            listener?.invoke(position)  //関数型変数を実行
        }
    }//onBindViewHolder↑↑

    override fun getItemCount():Int =  sights.size  //Sightオブジェクトの数を返す
}