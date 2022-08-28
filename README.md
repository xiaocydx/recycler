[CXRV（一）概述](https://www.yuque.com/u12192380/khwdgb/fe9gsu)

[CXRV（二）ListAdapter的使用说明](https://www.yuque.com/u12192380/khwdgb/rpbw6f)

[CXRV（三）ViewTypeDelegate的使用说明](https://www.yuque.com/u12192380/khwdgb/qkpmiu)

[CXRV（四）扩展的使用说明](https://www.yuque.com/u12192380/khwdgb/kcxn6o)

[CXRV（五）ListState的使用说明](https://www.yuque.com/u12192380/khwdgb/uvgw43)

[CXRV（六）分页组件的使用说明](https://www.yuque.com/u12192380/khwdgb/gh9sbc)


1. 在根目录的build.gradle添加
```
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. 在module的build.gradle添加
```
dependencies {
    implementation 'com.github.xiaocydx:CXRV:1.0.0'
    // RecyclerView的版本需要1.2.0或以上
    implementation 'androidx.recyclerview:recyclerview:1.2.0'
}
```
