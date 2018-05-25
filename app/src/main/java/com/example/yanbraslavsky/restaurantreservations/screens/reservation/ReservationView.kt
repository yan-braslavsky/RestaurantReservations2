package com.example.yanbraslavsky.restaurantreservations.screens.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.yanbraslavsky.restaurantreservations.App
import com.example.yanbraslavsky.restaurantreservations.R
import com.example.yanbraslavsky.restaurantreservations.api.models.responses.CustomerModel
import com.example.yanbraslavsky.restaurantreservations.mvp.BaseView
import com.example.yanbraslavsky.restaurantreservations.screens.customers.CustomersAdapter
import kotlinx.android.synthetic.main.activity_customer_selection.*
import javax.inject.Inject

class ReservationView : BaseView(), ReservationContract.View {

    @Inject
    lateinit var mPresenter: ReservationContract.Presenter

    companion object {

        private const val CUSTOMER_BUNDLE_EXTRA_KEY = "customer_bundle_extra_key"

        fun open(fromContext: Context, withCustomerModel: CustomerModel) {
            val intent = Intent(fromContext, ReservationView::class.java)
            intent.putExtra(CUSTOMER_BUNDLE_EXTRA_KEY, withCustomerModel)
            fromContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_selection)
        initRecyclerView()

        App.appComponent.inject(this)
        mPresenter.bind(this)
    }

    private fun initRecyclerView() {
        //TODO : Span count to be calculated depending on the view size
        recyclerView?.let {
            val gridLayoutManager = GridLayoutManager(it.context,8)
            it.layoutManager = gridLayoutManager
            val dividerItemDecoration = DividerItemDecoration(it.context,
                    gridLayoutManager.orientation)
            it.addItemDecoration(dividerItemDecoration)
        }
    }


    override fun showTables(data: List<Boolean>) {
        recyclerView?.adapter = ReservationAdapter(data, {
            mPresenter.onTableItemClick(it)
        })
    }
}