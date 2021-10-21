package com.example.tourlistsights

import android.content.res.Resources
import android.net.wifi.aware.AwareResources
import kotlinx.serialization.Serializable
import java.io.BufferedReader
import java.io.InputStreamReader

@Serializable
class Sight (
    val name: String,
    val imageName: String,
    val descripttion: String,
    val kind: String
    )

fun getSights(resources: Resources): List<Sight>{
    val assetManager = resources.assets
    val inputStream = assetManager.open("sights.json")  //jsonデータを参照してテキストを取得
    val bufferedReader = BufferedReader(InputStreamReader(inputStream)) //jsonデータ内のテキストを読み込んで
    val str: String = bufferedReader.readText() //変数strに格納
    val obj = Json(JsonConfiguration.Stable).parse(Sight.serializer().list, str)    //デコード
    return obj
}