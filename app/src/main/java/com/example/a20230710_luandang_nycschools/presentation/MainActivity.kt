package com.example.a20230710_luandang_nycschools.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a20230710_luandang_nycschools.R
import com.example.a20230710_luandang_nycschools.presentation.ui.SchoolList
import com.example.a20230710_luandang_nycschools.presentation.viewmodel.SchoolViewModel
import com.example.a20230710_luandang_nycschools.ui.theme.LuanDangNYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private lateinit var schoolViewModel: SchoolViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            schoolViewModel = hiltViewModel()
            LuanDangNYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column() {
                                TopAppBar(
                                    backgroundColor = MaterialTheme.colors.primarySurface,
                                    title = { Text(text = stringResource(id = R.string.app_name))}
                                )
                                SchoolList( schoolViewModel)
                            }
                        }
                    }
                )
            }
        }
    }

}
