package com.example.jetcomposedemo.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.jetcomposedemo.HomeViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.jetcomposedemo.model.Record

@Composable
fun HomeScreen(vm: HomeViewModel,navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "SPH coding test - Home",
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
        )
    }) {
        homeScreenListView(vm,navController)
    }
}

@Composable
fun homeScreenListView(vm: HomeViewModel,navController: NavController) {
    val items by vm.allData.observeAsState(listOf())
    if (items.isNotEmpty()) {
        val recordByYear = vm.getDataByYear(items);
        LazyColumn(Modifier.fillMaxSize()) {

            items(recordByYear.size) { index ->
                yearItem(recordByYear[index],navController)
                Divider(color = Color.Black)

                val lastIndex = recordByYear.lastIndex
                if (index == lastIndex) {
                    vm.loadData()
                }
            }
        }
    }
}

@Composable
fun yearItem(record: Record,navController: NavController) {
    Column(Modifier.clickable {
        navController.navigate(route = "${Screen.Detail.route}/${record.quarter}")
    }.fillMaxWidth(),) {
        Row(Modifier.padding(all = 12.dp)) {
            Text(
                modifier = Modifier.padding(end = 12.dp), text = "Year: ",
                fontSize = 24.sp,
            )
            Text(
                text = record.quarter,
                fontSize = 24.sp,
            )
        }
        Row(Modifier.padding(all = 12.dp)) {
            Text(
                modifier = Modifier.padding(end = 12.dp), text = "Total data volume: ",
                fontSize = 24.sp,
            )
            Text(
                text = record.volumeOfMobileData.toBigDecimal().toString(),
                fontSize = 24.sp,
            )
        }
    }
}
