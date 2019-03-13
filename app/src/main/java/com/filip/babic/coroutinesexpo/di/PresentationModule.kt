package com.filip.babic.coroutinesexpo.di

import com.filip.babic.coroutinesexpo.ui.feed.FeedContract
import com.filip.babic.coroutinesexpo.ui.feed.FeedPresenter
import com.filip.babic.coroutinesexpo.ui.login.LoginContract
import com.filip.babic.coroutinesexpo.ui.login.LoginPresenter
import com.filip.babic.coroutinesexpo.ui.posts.featured.FeaturedPostsContract
import com.filip.babic.coroutinesexpo.ui.posts.featured.FeaturedPostsPresenter
import com.filip.babic.coroutinesexpo.ui.posts.post.AddPostContract
import com.filip.babic.coroutinesexpo.ui.posts.post.AddPostPresenter
import com.filip.babic.coroutinesexpo.ui.profile.ProfileContract
import com.filip.babic.coroutinesexpo.ui.profile.ProfilePresenter
import com.filip.babic.coroutinesexpo.ui.register.RegisterContract
import com.filip.babic.coroutinesexpo.ui.register.RegisterPresenter
import com.filip.babic.coroutinesexpo.ui.users.UsersContract
import com.filip.babic.coroutinesexpo.ui.users.UsersPresenter
import com.filip.babic.coroutinesexpo.ui.welcome.WelcomeContract
import com.filip.babic.coroutinesexpo.ui.welcome.WelcomePresenter
import org.koin.dsl.module.module

val PresentationModule = module {

    factory { WelcomePresenter(get()) as WelcomeContract.Presenter }
    factory { LoginPresenter(get()) as LoginContract.Presenter }
    factory { RegisterPresenter(get(), get()) as RegisterContract.Presenter }
    factory { AddPostPresenter(get(), get()) as AddPostContract.Presenter }
    factory { FeaturedPostsPresenter(get()) as FeaturedPostsContract.Presenter }
    factory { FeedPresenter(get()) as FeedContract.Presenter }
    factory { UsersPresenter(get()) as UsersContract.Presenter }
    factory { ProfilePresenter(get(), get()) as ProfileContract.Presenter }
}