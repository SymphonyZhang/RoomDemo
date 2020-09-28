package com.symphony.coroutinedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.symphony.coroutinedemo.databinding.ActivityMainBinding
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //E(Thread.currentThread().name)
        /*GlobalScope.launch {
            println("Coroutines Camp: Coroutine ${Thread.currentThread().name}")
        }
        Thread{
            println("Coroutines Camp: Thread ${Thread.currentThread().name}")
        }.start()

        thread {
            println("Coroutines Camp: thread() ${Thread.currentThread().name}")
        }*/

        /*ioCode1()
        uiCode1()
        ioCode2()
        uiCode2()
        ioCode3()
        uiCode3()*/

        /*thread {
            ioCode1()
            runOnUiThread {
                uiCode1()
                thread {
                    ioCode2()
                    runOnUiThread {
                        uiCode2()
                        thread {
                            ioCode3()
                            runOnUiThread {
                                uiCode3()

                            }
                        }
                    }
                }
            }
        }*/
        /*GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }

        classicIoCode1(false){
            uiCode1()
        }*/

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        val api = retrofit.create(Api::class.java)

        /*api.listRepos("rengwuxian")
            .enqueue(object : Callback<List<Repo>?> {
                override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                    val rengwuxiainName = response.body()?.get(0)?.name
                    api.listRepos("google")
                        .enqueue(object :Callback<List<Repo>?>{
                            override fun onResponse(
                                call: Call<List<Repo>?>,
                                response: Response<List<Repo>?>
                            ) {
                                binding.textView.text = "$rengwuxiainName - ${response.body()?.get(0)?.name}"
                            }

                            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {

                            }

                        })
                }

                override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {

                }

            })*/
        /*GlobalScope.launch(Dispatchers.Main) {
            try {
                val repos = api.listReposKt("rengwuxian")
                binding.textView.text = repos[0].name
            }catch (e:Exception){
                binding.textView.text = e.message
            }
        }*/

        /*api.listReposRx("rengwuxian")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Repo>>{
                override fun onSubscribe(d: Disposable?) {
                    binding.textView.text = "开始请求了"
                }

                override fun onSuccess(repos: List<Repo>) {
                    binding.textView.text = repos[0].name
                }

                override fun onError(e: Throwable) {
                    binding.textView.text = e.message
                }

            })*/

        /*Single.zip(
            api.listReposRx("rengwuxian"),
            api.listReposRx("google"),
            { list1, list2 -> "${list1[0].name} -- ${list2[0].name}" })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ combined -> binding.textView.text = combined}
        */

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val asyncRepos1 = async { api.listReposKt("rengwuxian") }
                val asyncRepos2 = async { api.listReposKt("google") }
                textView.text = "${asyncRepos1.await()[0].name}  - ${asyncRepos2.await()[0].name}"
            } catch (e: Exception) {
                textView.text = e.message
            }
        }


        /*lifecycleScope.launch {
            try {
                val asyncRepos1 = async { api.listReposKt("rengwuxian") }
                val asyncRepos2 = async { api.listReposKt("google") }
                binding.textView.text = "${asyncRepos1.await()[0].name}  -><- ${asyncRepos2.await()[0].name}"
            } catch (e: Exception) {
                binding.textView.text = e.message
            }
        }*/
        lifecycleScope.launch {
            ioCode1()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private suspend fun ioCode1() = withContext(Dispatchers.IO) {
        println("Coroutines Camp: ioCode1() ${Thread.currentThread().name}")
    }


    private val executor = ThreadPoolExecutor(5, 20, 1, TimeUnit.MINUTES, LinkedBlockingDeque())

    private fun classicIoCode1(uiThread: Boolean = true, block: () -> Unit) {
        executor.execute {
            println("Coroutines Camp: ioCode1() ${Thread.currentThread().name}")
            if (uiThread) {
                runOnUiThread {
                    block()
                }
            } else {
                block()
            }
        }
    }



    private fun uiCode1() {
        println("Coroutines Camp: uiCode1() ${Thread.currentThread().name}")
    }

    private suspend fun ioCode2() = withContext(Dispatchers.IO) {
        println("Coroutines Camp: ioCode2() ${Thread.currentThread().name}")
    }

    private fun uiCode2() {
        println("Coroutines Camp: uiCode2() ${Thread.currentThread().name}")
    }

    private suspend fun ioCode3() = withContext(Dispatchers.IO) {
        println("Coroutines Camp: ioCode3() ${Thread.currentThread().name}")
    }

    private fun uiCode3() {
        println("Coroutines Camp: uiCode3() ${Thread.currentThread().name}")
    }
}
