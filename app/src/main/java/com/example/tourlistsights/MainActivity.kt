package com.example.tourlistsights

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment
import com.example.tourlistsights.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = "ListFragment"
        var fragment = supportFragmentManager.findFragmentByTag(tag)    //現在アクティビティに表示されているフラグメントを取得
        if (fragment == null) { //現在表示されているフラグメントがなければ
            fragment = ListFragment()
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.content, fragment, tag)    //ListFragmentを表示
            }.commit()
        }   //if ↑↑
    }   //onCreate ↑↑
}