package com.example.tourlistsights

import kotlinx.serialization.Serializable

@Serializable
class Slight (
    val name: String,
    val imageName: String,
    val descripttion: String,
    val kind: String

    )