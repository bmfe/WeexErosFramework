# 0.0.1
1.Loading
    1.1 完善loading样式
    1.2 修复loading.hide的bug 
2.拓展Axios 
    2.1 支持拓展Content-type
    2.2 拓展head，put，delete，patch请求方式
    2.3 返回http code
3.alert 
    3.1 修改alert样式

# 0.0.1
1.Loding
    1.1 不设置message bug
2.shorage 
    2.1 shorage 修改setData()方法返回值   
    2.2 支持shorage set get delete remove方法的同步返回

# 0.0.3
1.image标签 border-radius的问题  
2.中介者实例没有注入
3.请求没有域名崩溃
4.router.open backCallback没回调

# 0.0.4
1.修复bug
  1.1 修复中介者失效问题
  1.2 修复android weex debug 无法调试问题
  1.3 修复下拉刷新

2.weex.config.env 调整
  3.1 原weex.config.env自定义参数调整至weex.config.eros中
  3.2 weex.config.eros增加状态栏高度参数
3.iconfont
  4.1 支持bmLocal本地iconfont加载

4.Storage
  5.1  增加同步存取方法
5.http
  6.1  保留前端调用fetch时url的上的参数
  6.2  支持添加端口号

# 2.0.0
1.目录调整
2.集成了个推推送
3.集成日历控件
5.iconfont加载逻辑优化
6.集成bmGeo模块

#2.0.1
1.更新编译gradle版本
2.打开jsbundle更新逻辑
3.修复上传问题 上传图片可附带参数
4.支持BMChart
5.集成weex-amap
6.增加bmAuth moudle 支持微信授权登陆

#2.0.2
1.修改 AbstractWeexActivity 基类，删除 基类里返回和销毁 时检查是否开启dialog代码。
2.修改 bmRouter callPhone方法，增加 int型 nowCall参数，如果传递并且 等于1时 不弹出dialog 直接跳转拨打电话页面

#2.0.3
1、增加第一个页面的 物理返回按钮监听抛出事件'homeBack'
2、增加 finish 方法 给前端调用。
3、eros.native.json 增加配置 isListentHomeBack 等于1时 监听HomeBack

#2.0.4
1、修改 EventFetch patch 方法
2、修改 pages.zip 改成 bundle.zip