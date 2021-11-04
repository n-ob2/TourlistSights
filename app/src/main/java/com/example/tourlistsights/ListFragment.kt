package com.example.tourlistsights

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourlistsights.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.apply{ //fragment_list内のコンテンツはRecyclerViewだけ
            layoutManager =
                when{
                    resources.configuration.orientation
                            == Configuration.ORIENTATION_PORTRAIT   //縦向きの時
                    -> LinearLayoutManager(context)
                    else
                    -> GridLayoutManager(context, 2)    //縦じゃない時
                }
            adapter = SightAdapter(context, getSights(resources)).apply{
                setOnItemClickListener{ position: Int ->   //セルがタップされた時
                    fragmentManager?.let { manager: FragmentManager ->
                        val tag ="DetailFragment"
                        var fragment = manager.findFragmentByTag(tag)
                        if (fragment == null){  //現在開いているフラグメントがなければ
                            fragment = DetailFragment() //詳細画面フラグメントを作成
                            fragment.arguments = Bundle().apply {
                                putInt(ROW_POSITION, position)  //アーギュメンツにタップされた番号を渡して
                            }
                             manager.beginTransaction().apply{
                                replace(R.id.content, fragment, tag)    //フラグメントを表示
                                addToBackStack(null)
                            }.commit()
                        }
                    }
                }
            }
        }
    }   //onViewCreated↑↑

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}