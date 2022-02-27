package com.example.jetcomposedemo

import android.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.example.jetcomposedemo.ui.theme.JetComposeDemoTheme
import com.example.jetcomposedemo.view.SetNavGraph
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val vm: HomeViewModel by viewModel()
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeDemoTheme {
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetNavGraph(navController = navController, vm =vm)
                }
            }
        }
        vm.loadData()
    }

    override fun onResume() {
        super.onResume()
        Log.d("oppa", "main activity on resume")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetComposeDemoTheme {
        Greeting("Android")
    }
}