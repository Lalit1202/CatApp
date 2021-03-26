package com.example.catapp

import android.renderscript.ScriptGroup
import android.util.Log
import android.util.Log.ASSERT
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.BindingMethod
import androidx.databinding.ObservableArrayList
import androidx.test.ext.junit.runners.AndroidJUnit4
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
import org.junit.rules.TestName
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
        //assertEquals(5,2*3)

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


    @get : Rule
    val testName  = TestName()



    @Test
    fun check()
    {
       testCoroutineRule.runBlockingTest {

           val vm = CatVM()
           vm.fetchCatData()
       }

    }





}
@RunWith(AndroidJUnit4::class)
class Test{
    @Test
    fun abc()
    {

        assertTrue(5==5)

    }
}




