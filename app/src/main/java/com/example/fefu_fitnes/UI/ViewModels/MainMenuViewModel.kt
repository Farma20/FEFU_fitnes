package com.example.fefu_fitnes.UI.ViewModels


import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.MainInfo


class MainMenuViewModel: ViewModel() {
    val mainInfo = listOf<MainInfo>(
        MainInfo("Текст1", "Текстовый текст1"),
        MainInfo("Текст2", "Текстовый текст2"),
        MainInfo("Текст3", "Текстовый текст3"),
        MainInfo("Текст4", "Текстовый текст4"),
        MainInfo("Текст5", "Текстовый текст5"),
        MainInfo("Текст6", "Текстовый текст6")
    )
}