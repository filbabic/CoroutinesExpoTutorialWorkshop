package com.filip.babic.coroutinesexpo.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.ui.posts.featured.FeaturedPostsFragment
import com.filip.babic.coroutinesexpo.ui.feed.FeedFragment
import com.filip.babic.coroutinesexpo.ui.profile.ProfileFragment
import com.filip.babic.coroutinesexpo.ui.users.UsersFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)

        private const val PAGE_FEED = 0
        private const val PAGE_FEATURED = 1
        private const val PAGE_USERS = 2
        private const val PAGE_PROFILE = 3
    }

    private val homePagerAdapter by lazy { HomePagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initUi()
    }

    private fun initUi() {
        homePagerAdapter.setData(listOf(FeedFragment(), FeaturedPostsFragment(), UsersFragment(), ProfileFragment()))

        mainPager.adapter = homePagerAdapter
        mainPager.offscreenPageLimit = 3

        bottomNavigation.setOnNavigationItemSelectedListener { showPage(it.itemId) }
    }

    private fun showPage(itemId: Int): Boolean {
        val nextPage = when (itemId) {
            R.id.feedOption -> PAGE_FEED
            R.id.featuredOptions -> PAGE_FEATURED
            R.id.usersOptions -> PAGE_USERS
            R.id.profileOption -> PAGE_PROFILE
            else -> null
        }

        nextPage?.run { mainPager.currentItem = this }
        return true
    }
}