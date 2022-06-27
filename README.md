<p align="center">
	<img alt="logo" src="https://img-blog.csdnimg.cn/c45859dc282442b8a0ec927f2c66890c.png" style="width:100px;">
</p>
<h1 align="center" style="margin: 10px 0 20px; font-weight: bold;">Hunau Management</h1>
<h3 align="center">基于SpringBoot和Vue开发的后台管理系统</h4>
<p align="center" style="margin-top:30px;">
  <img alt="logo" src="https://img-blog.csdnimg.cn/ad2e1122bca144c4b1f431ab45516727.png" style="width:100px;margin-right:10px;">
  <img alt="logo" src="https://img-blog.csdnimg.cn/ab8dc1a3165d401fb87977e884a739e0.png" style="width:30px;margin-left:10px;">
</p>

## 项目简介

Hunau Management是一个基于SpringBoot和Vue开发的后台管理系统

前端使用了Vue 2、Element UI、ECharts，后端使用了SpringBoot、Mybatis、Mybatis Plus

## 功能实现

1.  登陆页面：使用了Element UI支持的前端校验及JWT后端校验，并结合LocalStorage将Token保存至浏览器本地LocalStorage以方便下次打开网页时不用重复登录，提高效率
2.  首页：首页使用了ECharts实现数据可视化，将数据库中用户统计数据以折线图和饼图的形式形象地向用户展显出来。
3.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。页面使用Element UI设计表单样式及按钮。实现了分页、搜索、新增用户、修改用户数据、删除用户、批量删除用户、导入用户数据、导出用户数据等功能。
4.  文件管理：可以为系统用户提供文件管理功能，包括上传文件、下载文件、预览文件、修改文件信息、删除文件、启用禁用文件、搜索文件等功能
5.  用户信息：用户信息页面是为当前系统用户设计的，用于修改用户个人信息，包括更改姓名、昵称、头像等信息的一个单独设计的页面。

## 界面截图

<table>
	<tr>
    <td><img src="https://img-blog.csdnimg.cn/43e4a21e1953488295c45a89290b64d6.jpeg"/></td>
  </tr>
  <tr>
    <td><img src="https://img-blog.csdnimg.cn/ba444ac97d9b4d9ba188271409de588d.jpeg"/></td>
  </tr>
  <tr>
    <td><img src="https://img-blog.csdnimg.cn/d1149f7cffde4758815abb6b4bec8d17.jpeg"/></td>
  </tr>
  <tr>
    <td><img src="https://img-blog.csdnimg.cn/3af1e29b923941e891f297d8ed7eada8.jpeg"/></td>
  </tr>
  <tr>
    <td><img src="https://img-blog.csdnimg.cn/175161de3c8142d19bbf0d40c0eefb16.jpeg"/></td>
  </tr>
  <tr>
  <td><img src="https://img-blog.csdnimg.cn/f626ce479058476d8648d4188e312718.jpeg"/></td>
  </tr>
</table>

