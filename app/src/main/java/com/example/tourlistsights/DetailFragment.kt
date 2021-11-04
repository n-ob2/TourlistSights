package com.example.tourlistsights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tourlistsights.databinding.FragmentDetailBinding

const val ROW_POSITION = "ROW_POSITION" //アーギュメンツの値を格納するプロパティを定義

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ROW_POSITION)  //アーギュメンツに格納されてる値をプロパティに格納
        }
    }   //onCreate ↑↑

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }   //onCreateView ↑↑

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sights = getSights(resources)   //データを配列で取得して格納
        binding.detailKind.text = sights[position].kind //position番目のデータを画面に表示
        binding.detailName.text = sights[position].name
        binding.detailDescription.text = sights[position].descripttion
        var img =resources.getIdentifier(sights[position].imageName, "drawable", context?.packageName)
        binding.detailImage.setImageResource(img)
    }   //onViewCreated　↑↑
}