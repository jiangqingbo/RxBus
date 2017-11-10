package com.huyunit.rxbus.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.huyunit.rxbuskt.RxBuskt
import com.huyunit.rxbuskt.registerInRxBus
import kotlinx.android.synthetic.main.activity_rxbuskt.*
import rx.android.schedulers.AndroidSchedulers

/**
 * author: bobo
 * create time: 2017/11/7 上午11:28
 * email: jqbo84@163.com
 */
class RxbusktActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbuskt)

        subscribeToExampleEvents()
        setClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBuskt.unregister(this)
    }

    private fun setClickListeners() {
        //produce example events on click
        buttonTextEvent.setOnClickListener {
            RxBuskt.post(Event.Text("Single Text method."))
        }

        var counter = 0
        buttonCounterEvent.setOnClickListener {
            counter++
            RxBuskt.post(Event.Counter(count = counter))
        }
    }

    private fun subscribeToExampleEvents() {
        RxBuskt.observe<Event.Text>()
                .map { "Modified ${it.title}"} //you can use any Rx operator to transform event data
                .subscribe { textView.text = it }
                .registerInRxBus(this)

        RxBuskt.observe<Event.Counter>()
                .observeOn(AndroidSchedulers.mainThread()) //optional, if you need to receive event in main thread
                .subscribe { textView.text = "Counter event post ${it.count} times" }
                .registerInRxBus(this)

        //if you want to manage each of your subscriptions separately, you can store them locally
        val notRegisteredSubscription = RxBuskt.observe<Event.Text>()
                .subscribe { textView.text = it.title }
        //here we don't call registerInBus(this)

        //... somewhere later, unsubscribe when you need
        notRegisteredSubscription.unsubscribe()
    }
}

class Event {
    data class Text(var title: String)

    data class Counter(var count: Int)
}