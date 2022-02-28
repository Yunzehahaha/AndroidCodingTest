package com.example.jetcomposedemo

import com.example.jetcomposedemo.backendApi.EndPoint
import com.example.jetcomposedemo.model.Record
import com.example.jetcomposedemo.model.ResponseData
import com.example.jetcomposedemo.repository.MyRepository
import com.example.jetcomposedemo.room.Dao
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.Flow
import org.junit.After
import org.junit.Before
import org.junit.Test

class MyRepositoryTest {
    private val rxRule = RxImmediateSchedulerRule()
    private val rule = InstantTaskExecutorRule()
    private val mockDao = mockk<Dao>()
    private lateinit var instance: MyRepository
    private val mockAllData = mockk<Flow<List<Record>>>()
    private val mockApi = mockk<EndPoint>()

    @Before
    fun before() {
        rxRule.run()
        rule.start()
        every { mockDao.getAllData() } returns mockAllData
        instance = MyRepository(mockApi, mockDao)
    }

    @After
    fun after() {
        rxRule.reset()
        rule.stop()
    }

    @Test
    fun testOnLoadSuccess() {
        val mockResponse = mockk<ResponseData>(relaxed = true)
        val mockUnit = mockk<Unit>(relaxed = true)
        every { mockDao.addNewData(any()) } returns mockUnit
        every { mockApi.fetchData(any(), any(), any()) } returns Single.just(mockResponse)
        instance.loadData(0)
        assertEquals(instance.isSuccess, true)
    }

    @Test
    fun testOnLoadError() {
        val mockThrowable = mockk<Throwable>(relaxed = true)
        val mockUnit = mockk<Unit>(relaxed = true)
        every { mockDao.addNewData(any()) } returns mockUnit
        every { mockApi.fetchData(any(), any(), any()) } returns Single.error(mockThrowable)
        instance.loadData(0)
        assertEquals(instance.isSuccess, false)
    }
}