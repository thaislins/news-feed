package com.thaislins.newsfeed.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.NewsTypeAttribute
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        val news = ArrayList<News?>()

        news.add(News(3687314,
            "Will Smith banned from the Oscars for 10 years over Chris Rock slap",
            "Apr 08 2022",
            NewsTypeAttribute("https://i.cbc.ca/1.6413040.1649418297!/fileImage/httpImage/image.jpg_gen/derivatives/16x9tight_140/1239829641.jpg")
            ))
        news.add(News(3709955,
            "At least 50 killed, dozens wounded in Russian missile strike on Ukrainian train station, say officials",
            "Apr 08 2022",
            NewsTypeAttribute("https://i.cbc.ca/1.6406331.1649446824!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_620/aptopix-94th-academy-awards.jpg")
        ))
        news.add(News(3715700,
            "RCMP investigating after 'suspicious package' triggers Trans Mountain pipeline shutdown",
            "Apr 08 2022",
            NewsTypeAttribute("https://i.cbc.ca/1.6383436.1649451267!/fileImage/httpImage/image.JPG_gen/derivatives/16x9_620/canada-pipeline-transmountain.JPG")
            ))
        news.add(News(3715674,
            "Indian international student shot and killed at Toronto subway station",
            "Apr 08 2022",
            NewsTypeAttribute("https://i.cbc.ca/1.6413887.1649449379!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_620/kartik-vasudev.jpg")
        ))

        val adapter = NewsAdapter(news, view.context)
        binding.rvNews.adapter = adapter
    }
}