package com.example.yanbraslavsky.restaurantreservations.di.app

import com.example.yanbraslavsky.restaurantreservations.App
import com.example.yanbraslavsky.restaurantreservations.di.AppModule
import com.example.yanbraslavsky.restaurantreservations.screens.customers.CustomersContract
import com.example.yanbraslavsky.restaurantreservations.screens.customers.CustomersUseCase
import com.example.yanbraslavsky.restaurantreservations.screens.main.MainContract
import com.example.yanbraslavsky.restaurantreservations.screens.main.MainPresenter
import com.example.yanbraslavsky.restaurantreservations.screens.reservation.ReservationContract
import com.example.yanbraslavsky.restaurantreservations.screens.reservation.ReservationUseCase
import dagger.Module
import dagger.Provides

@Module
class TestAppModule(mApp: App) : AppModule(mApp) {

    //we leave a possibility to change that presenter before the injection
    var mMockedMainPresenter: MainContract.Presenter? = null
    var mMockedCustomerPresenter: CustomersContract.Presenter? = null
    var mMockedReservationsPresenter: ReservationContract.Presenter? = null


    @Provides
    override fun provideMainPresenter(): MainContract.Presenter {
        return mMockedMainPresenter ?: MainPresenter()
    }

    @Provides
    override fun provideCustomersPresenter(customersUseCase: CustomersUseCase): CustomersContract.Presenter {
        return mMockedCustomerPresenter ?: super.provideCustomersPresenter(customersUseCase)
    }

    @Provides
    override fun provideReservationPresenter(reservationUseCase: ReservationUseCase): ReservationContract.Presenter {
        return mMockedReservationsPresenter ?: super.provideReservationPresenter(reservationUseCase)
    }

}