package com.hicoding.smkpubmak.views.activity.intro

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.activity.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_intro.*
import org.jetbrains.anko.startActivity

@Suppress("DEPRECATION")
class IntroActivity : AppCompatActivity() {

    private lateinit var introAdapter: IntroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initViews()
    }

    private fun initViews() {
        val layouts = intArrayOf(
            R.layout.intro_slide_1,
            R.layout.intro_slide_2,
            R.layout.intro_slide_3
        )
        introAdapter = IntroAdapter()
        introAdapter.addItems(this, layouts)
        view_pager_intro.adapter = introAdapter

        view_pager_intro.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == layouts.size - 1) {
                    button_skip_slide.visibility = View.INVISIBLE
                    button_next_slide.text = resources.getString(R.string.button_text_start)
                } else {
                    button_skip_slide.visibility = View.VISIBLE
                    button_next_slide.text = resources.getString(R.string.text_button_next)
                }
            }
        })

        //button next
        button_next_slide.setOnClickListener {
            val current = getCurrrentItem(+1)
            if (current < layouts.size) {
                view_pager_intro.setCurrentItem(current, true)
            } else {
                startActivity<SignInActivity>()
                finish()
            }
        }

        //button skip
        button_skip_slide.setOnClickListener {
            view_pager_intro.setCurrentItem(layouts.size, true)
        }
    }

    private fun getCurrrentItem(item: Int) = view_pager_intro.currentItem + item
}