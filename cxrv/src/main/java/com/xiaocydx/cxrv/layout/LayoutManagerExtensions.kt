package com.xiaocydx.cxrv.layout

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.xiaocydx.cxrv.itemvisible.findFirstCompletelyVisibleItemPosition
import com.xiaocydx.cxrv.itemvisible.findFirstVisibleItemPosition
import com.xiaocydx.cxrv.itemvisible.findLastCompletelyVisibleItemPosition
import com.xiaocydx.cxrv.itemvisible.findLastVisibleItemPosition

/**
 * [LayoutManager]扩展接口
 *
 * 在目录src\main\resources\META-INF\services下，
 * 创建文件com.xiaocydx.cxrv.layout.LayoutManagerExtensions，
 * 在文件中添加实现了[LayoutManagerExtensions]的类名（包含包名）。
 *
 * @author xcc
 * @date 2022/9/24
 */
interface LayoutManagerExtensions<T : LayoutManager> {
    /**
     * 需要匹配的[LayoutManager]类型
     */
    val layoutClass: Class<out T>

    /**
     * 对应[RecyclerView.findFirstVisibleItemPosition]
     */
    fun findFirstVisibleItemPosition(layout: T): Int = NO_POSITION

    /**
     * 对应[RecyclerView.findFirstCompletelyVisibleItemPosition]
     */
    fun findFirstCompletelyVisibleItemPosition(layout: T): Int = NO_POSITION

    /**
     * 对应[RecyclerView.findLastVisibleItemPosition]
     */
    fun findLastVisibleItemPosition(layout: T): Int = NO_POSITION

    /**
     * 对应[RecyclerView.findLastCompletelyVisibleItemPosition]
     */
    fun findLastCompletelyVisibleItemPosition(layout: T): Int = NO_POSITION
}