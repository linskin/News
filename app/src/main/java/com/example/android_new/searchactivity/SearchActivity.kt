package com.example.android_new.searchactivity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_new.theme.ApprenticeshipTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

lateinit var searchResults: MutableList<String>

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApprenticeshipTheme {
                // 使用主题的颜色作为背景色的Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SearchScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    ApprenticeshipTheme {
        SearchScreen()
    }
}

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                performSearch(query)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            Thread.sleep(1000)
            items(searchResults) { result ->
                Text(result, modifier = Modifier.padding(16.dp),)
            }
        }
    }
}

private fun performSearch(query: String) {
    val client = OkHttpClient()

    val url = "https://www.baidu.com/s?rtt=1&bsst=1&cl=2&tn=news&rsv_dl=ns_pc&word=$query"

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 处理请求失败的情况
        }

        override fun onResponse(call: Call, response: Response) {
            val responseData = response.body?.string()

            // 解析响应数据并获取搜索结果
            val searchResults = parseSearchResults(responseData)

            // 在主线程更新界面
            updateSearchResults(searchResults)
        }
    })
}

private fun parseSearchResults(responseData: String?): List<String> {
    val results: MutableList<String> = mutableListOf()
    responseData?.let {
        val document: Document = Jsoup.parse(responseData)
        // 使用 CSS 选择器选择所有搜索结果的标题元素
        val titleElements = document.select(".c-title")
        for (element in titleElements) {
            val title = element.text()
            results.add(title)
        }
    }
    return results
}

@MainThread
private fun updateSearchResults(results: List<String>) {
    // 在主线程更新搜索结果列表
    searchResults.clear()
    searchResults.addAll(results)
}