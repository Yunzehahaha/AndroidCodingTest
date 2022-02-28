package com.example.jetcomposedemo

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor

class InstantTaskExecutorRule {

    fun start() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    fun stop() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}