package com.xiaocydx.sample.paging.article

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.xiaocydx.accompanist.lifecycle.launchRepeatOnLifecycle
import com.xiaocydx.accompanist.view.dp
import com.xiaocydx.accompanist.view.layoutParams
import com.xiaocydx.accompanist.view.matchParent
import com.xiaocydx.accompanist.view.overScrollNever
import com.xiaocydx.accompanist.windowinsets.enableGestureNavBarEdgeToEdge
import com.xiaocydx.cxrv.binding.bindingAdapter
import com.xiaocydx.cxrv.divider.Edge
import com.xiaocydx.cxrv.divider.divider
import com.xiaocydx.cxrv.itemtouch.itemTouch
import com.xiaocydx.cxrv.list.ListAdapter
import com.xiaocydx.cxrv.list.adapter
import com.xiaocydx.cxrv.list.fixedSize
import com.xiaocydx.cxrv.list.linear
import com.xiaocydx.cxrv.paging.PagingPrefetch
import com.xiaocydx.cxrv.paging.onEach
import com.xiaocydx.cxrv.paging.pagingCollector
import com.xiaocydx.sample.*
import com.xiaocydx.sample.databinding.ItemArticleBinding
import com.xiaocydx.accompanist.paging.withPaging
import com.xiaocydx.accompanist.paging.withSwipeRefresh

/**
 * Paging示例代码（网络请求）
 *
 * @author xcc
 * @date 2022/3/17
 */
class ArticleListActivity : AppCompatActivity() {
    private lateinit var rvArticle: RecyclerView
    private lateinit var articleAdapter: ListAdapter<ArticleInfo, *>

    /**
     * 已使用预取策略[PagingPrefetch.ItemCount]
     */
    private val viewModel: ArticleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initCollect()
        initEdgeToEdge()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        articleAdapter = bindingAdapter(
            uniqueId = ArticleInfo::id,
            inflate = ItemArticleBinding::inflate
        ) {
            itemTouch {
                onSwipe { position, _ -> viewModel.deleteArticle(position) }
            }
            onBindView {
                tvTitle.text = it.title ?: ""
                tvAuthor.text = "作者：${it.author ?: ""}"
            }
        }

        rvArticle = RecyclerView(this)
            .apply { id = viewModel.rvId }
            .layoutParams(matchParent, matchParent)
            .overScrollNever().linear().fixedSize()
            .divider(10.dp, 10.dp) { edge(Edge.all()) }
            .adapter(articleAdapter.withPaging())

        setContentView(rvArticle.withSwipeRefresh(articleAdapter))
    }

    private fun initCollect() {
        viewModel.pagingFlow
            .onEach(articleAdapter.pagingCollector)
            .launchRepeatOnLifecycle(lifecycle)
    }

    private fun initEdgeToEdge() {
        window.enableGestureNavBarEdgeToEdge()
        rvArticle.enableGestureNavBarEdgeToEdge()
    }
}