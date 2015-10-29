## 饭否精选 第三方客户端 for Android

「饭否精选」是我的一个小练习。RecyclerView + Volley. 另外使用的一些第三方库有 Butterknife 和 CircleView 等。

T^T 讲真我觉得我还是要再努力学习。


Still under building.

### 已完成：
- 可以从 Api 接口中解析数据并完整呈现
- 可以在「每日精选」和「每周精选」间切换




### To do:
- Feed 中的超链接呈现 :beers:
- 下拉刷新
- 在解析 Api 之前先解析得到全部可以使用的 api 地址，从中挑选出 weekly 和 daily
- 全面使用 butterknife 精简代码 :beers:
- 复用 FragmentDaily 和 FragmentWeekly，精简代码
- 支持饭否帐号登录，增加收藏功能
- 点击 Feed 后能够进入 FragmentDetail


## Some thing I would like to write it here:
- 很感谢 [rex](https://github.com/zhasm) 提供的 Api，才使得这个小项目成为可能。我想继续完善，一直到自己觉得可以日常使用的时候才会上架以及公开提供给饭友使用。
-  rex 提供的 api 中，有一个地址是提供了从 `2015-10-05` 开始的能够抓到数据的地址 index。半个月前我看的时候只有大概十几条地址，以为规则大概是从当天开始往上数十天内的数据是可以提供的。但今天再看的时候发现我想错了。 rex 是保留了从 15 年 10 月 5 日开始的全部饭否精选数据索引。直接询问他后证实了这一点。

