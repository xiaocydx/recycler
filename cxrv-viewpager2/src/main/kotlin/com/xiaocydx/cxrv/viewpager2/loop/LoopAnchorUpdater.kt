/*
 * Copyright 2022 xiaocydx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiaocydx.cxrv.viewpager2.loop

import androidx.recyclerview.widget.LoopPagerAdapter
import androidx.recyclerview.widget.LoopPagerScroller
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2

/**
 * [ViewPager2]循环页面的锚点信息更新器，[LoopPagerScroller]是唯一实现类
 *
 * @author xcc
 * @date 2023/5/13
 */
internal fun interface LoopAnchorUpdater {

    /**
     * 更新锚点信息
     *
     * 以A和B是原始页面，`B*`和`A*`是附加页面为例：
     * ```
     * {B* ，A ，B ，A*}
     * ```
     * 假设`viewPager.currentItem`为`B*`，当开始滚动`B*`时，
     * 会更新锚点信息，下一帧以`B`为锚点，往两侧填充子View。
     *
     * 实现类会优化更新锚点信息的过程，避免产生其他影响，
     * 优化效果可以理解为将`B*`的`itemView`，挪到`B`处，
     * `itemView`不会被移除，也不会绑定新的[ViewHolder]。
     *
     * @param offset 锚点位置的偏移，当对数据源进行`move`更新时，会有偏移值。
     * @param contentCount 内容`item`数量，当数据源更新时，会同步更新附加页面，
     * 同步更新的初衷是更新离屏缓存，但如果`viewPager.currentItem`是附加页面，
     * 那么会导致当前可见内容发生变化，这不符合预期，这种情况也需要更新锚点信息，
     * 此时`adapter.itemCount`是最新值，且未布局完成，因此需要传入之前的数量，
     * 数据源更新后的处理由[LoopPagerAdapter]完成。
     */
    fun updateAnchor(offset: Int, contentCount: Int)
}