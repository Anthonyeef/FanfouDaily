## 饭否精选 第三方客户端 for Android

「饭否精选」是我的一个小练习。RecyclerView + Volley. 另外使用的一些第三方库有 `Butterknife` , `EventBus`, `CircleView` 等。




<del> Still under building. </del>

Almost there ;)

### Screenshot

<img src="screenshots/Screenshot_1.jpg" width="45%" />
<img src="screenshots/Screenshot_2.jpg" width="45%" />
<img src="screenshots/Screenshot_3.jpg" width="45%" />


### 已完成：
- 可以从 Api 接口中解析数据并完整呈现
- 可以在「每日精选」和「每周精选」间切换
- 可以查看 `2015-10-05` 开始的任意时间的 `每日精选`，以及最新一期的`每周精选`

### TO DO
- 增加卡片的点击事件
- 实现饭否帐号登录，实现「收藏」「转发」等功能
- 实现转发到其他平台
- 夜间模式
- 「每周精选」的 `Fragment` 底部增加 footer，浏览到最底部后可以自动加载上一期「每周精选」
- 考虑增加这个[工具](https://github.com/mcxiaoke/gradle-packer-plugin)，用以管理多渠道打包和版本号
- 多语言支持

### Installation
<del>Still trying to figure out the publishing method.</del>

<del>And currently only support</del> 

- Build from source code(using Android Studio).
- [GitHub release](https://github.com/Anthonyeef/FanfouDaily/releases/download/0.9.1/app-fir-release.apk)
- [Fir](http://fir.im/zhj3)
- Scan QR code:

<img src="screenshots/QRcode.png"  width="33%" />

## Some thing I would like to write it here:
- 很感谢 [rex](https://github.com/zhasm) 提供的 Api，才使得这个小项目成为可能。


## License

```
Copyright 2015 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```

