package com.example.catapp

import android.renderscript.ScriptGroup
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.BindingMethod
import androidx.databinding.ObservableArrayList
import com.bumptech.glide.load.engine.Resource
import com.example.catapp.cat.data.CatRepository
import com.example.catapp.cat.data.model.CatData
import com.example.catapp.cat.vm.CatVM
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.databinding.GridViewItemBinding
import com.example.catapp.util.bindImage
import com.example.freetogame.base.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success
import kotlin.Result.Companion.success
import kotlin.jvm.Throws



class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }




}
@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    override fun apply(base: Statement, description: Description?) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            Dispatchers.resetMain()
            testCoroutineScope.cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest { block() }

}

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameVMTest
{
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()





    @Test
    fun check()
    {
       testCoroutineRule.runBlockingTest {

           val vm = CatVM()
           vm.fetchCatData()
       }

    }

    @Test
    fun incrementCheck()
    {
        var page =1
        page++
       assertEquals(3,page)
        assertEquals(2,page)



    }

//    @Test
//    fun resetpageNoCheck()    {
//        val vm = CatVM()
//        vm.updatePageNumberByOne()
//        assertEquals()
//
//
//    }
}




