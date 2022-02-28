package com.example.jetcomposedemo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetcomposedemo.HomeViewModel
import com.example.jetcomposedemo.model.Record
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(vm: HomeViewModel, navController: NavController, year: String) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "SPH coding test - Detail",
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back to home screen"
                    )
                }
            }
        )
    }) {
        vm.setSelectedYear(year)
        MyViewPager(vm = vm)
    }
}

@ExperimentalPagerApi
@Composable
fun MyViewPager(vm: HomeViewModel) {
    val items by vm.allData.observeAsState(listOf())
    if (items.isNotEmpty()) {
        vm.groupDataByYear()
        val pageCount = vm.totalPage
        val initPage = vm.initPage
        val pagerState = rememberPagerState(
            pageCount = pageCount,
            initialPage = initPage,
            initialOffscreenLimit = 1,
            infiniteLoop = false
        )
        Box(Modifier.fillMaxSize()) {
            HorizontalPager(modifier = Modifier.fillMaxSize(), state = pagerState) { page ->
                val records = vm.getDataByPage(page)
                vm.onYearPageChange(pagerState.currentPage)
                QuarterItemForYear(records)
            }
        }
    }
}

@Composable
fun QuarterItemForYear(records: List<Record>) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column() {
            records.forEach {
                QuarterItem(item = it)
            }
        }
    }
}

@Composable
fun QuarterItem(item: Record) {

    Row(Modifier.padding(all = 20.dp)) {
        Text(text = item.quarter)
        Spacer(Modifier.width(36.dp))
        Text(text = item.volumeOfMobileData.toBigDecimal().toString())
    }
}
