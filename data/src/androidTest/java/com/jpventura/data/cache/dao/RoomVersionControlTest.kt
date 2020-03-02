package com.jpventura.data.cache.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.jpventura.data.entity.Repository
//import com.jpventura.data.ktx.fromJson
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test


class RoomVersionControlTest {

    data class Repositories(val items: List<Repository>)

    private lateinit var db: RoomVersionControl
  //  private lateinit var repositories: List<Repository>
    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
//        val repositories: List<Repository> = requireNotNull(
//            javaClass.getResourceAsStream("/repositories.json")
//                ?.fromJson(Repositories::class.java)
//        ).items
//
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            RoomVersionControl::class.java
        ).build()

        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
    }

    @Test
    fun shouldCreateRepositories() {
    }

    @After
    fun terDown() {
        RxJavaPlugins.setComputationSchedulerHandler { null }
        RxJavaPlugins.setIoSchedulerHandler { null }
        db.close()
    }

}
