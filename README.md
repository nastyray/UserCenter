# UserCenter

## 项目简介

一个企业中最最最常用的系统 —— `用户中心` ，说白了，就是一个简单的 “用户管理系统” ，实现了用户注册、登录、查询等基础功能。

虽然系统逻辑和功能并不复杂，代码量也不多，但是正因如此，才不需要关注特定的、复杂的业务流程，可以更轻松地学习到 **通用的技术和做项目的经验** 。

麻雀虽小五脏俱全，真真正正地 **从 0 到 1** 完成了这个项目的完整前端和后端！从产生想法、到需求分析、到技术选型、系统设计、项目初始化、编码实现、测试、再到最后的部署上线，每一个环节都从理论到实践、每一个细节都不放过！再加上学习过程中踩的种种坑点以及问题的解决，相信一定可以走出书本、走出校园、走出死板  / 单纯讲知识点的网课和教程，学到企业 真正需要的开发技能和经验。

经过一些前端或后端开发的基础知识，完完整整地做完这个项目并跟着实践后，你绝对可以 **独立、一条龙开发和上线** 绝大多数常见的系统了！



## 技术选型

### 前端

主要运用阿里 Ant Design 生态：

- HTML + CSS + JavaScript 三件套
- React 开发框架
- Ant Design Pro 项目模板
- Ant Design 端组件库
- Umi 开发框架
- Umi Request 请求库

### 

### 后端

- Java 编程语言
- Spring + SpringMVC + SpringBoot 框架
- MyBatis + MyBatis Plus 数据访问框架
- MySQL 数据库
- jUnit 单元测试库

### 

### 部署

- 单机部署
- Nginx
- 容器



## 项目演示

![](https://github.com/nastyray/UserCenter/blob/main/Image/%E9%A1%B9%E7%9B%AE%E6%80%BB%E8%A7%88.png)



## 项目收获

1. 学会前后端企业主流开发技术的应用
2. 了解做项目的完整流程，能够独立开发及上线项目
3. 学到系统设计的方法和经验
4. 学到一些实际的编码技巧，比如开发工具、快捷键、插件的使用
5. 学到代码的优化技巧，比如抽象、封装、提高系统性能、节约资源的方法
6. 学习登录态、代理、多环境、容器、跨域等重要的开发知识
7. 学到一些源码阅读的技巧
8. 提升自主解决问题的能力



## 学习过程

### 企业做项目的流程

需求分析 => 设计（概要设计、详细设计）=> 技术选型 => 初始化 / 引入需要的技术 => 写 Demo => 写代码（实现业务逻辑） => 测试（单元测试、系统测试）=> 代码提交 / 代码评审 => 部署 => 发布上线

### 需求分析

1. 登录 / 注册
2. 用户管理（仅管理员可见）对用户的查询或者修改
3. 用户校验（仅星球用户可见）

### 前端初始化

使用Ant-design pro

访问官网https://pro.ant.design/zh-CN/docs/getting-started

![](https://github.com/nastyray/UserCenter/blob/main/Image/%255CUsers%255Clil%2520ray%255CAppData%255CRoaming%255CTypora%255Ctypora-user-images%255C1695194879643.png)

初始化项目，在初始文件夹中执行

```
# 使用 npm
npm i @ant-design/pro-cli -g
pro create myapp
```

选择 umi 的版本，这里选择umi@3

```
? 🐂 使用 umi@4 还是 umi@3 ? (Use arrow keys)
❯ umi@4
  umi@3
```

> 如果选择了 umi@4 版本，暂时还不支持全量区块。

如果选择了 umi@3，还可以选择 pro 的模板，pro 是基础模板，只提供了框架运行的基本内容，complete 包含所有区块，不太适合当基础模板来进行二次开发，我们选择simple

```
? 🚀 要全量的还是一个简单的脚手架? (Use arrow keys)
❯ simple
  complete
```

安装依赖：

```
$ cd myapp && tyarn
// 或
$ cd myapp && npm install
```

执行前端项目：

![](https://github.com/nastyray/UserCenter/blob/main/Image/5D6H%7B7YI%7B(OTSQK9E30JJ%5BT.png)

通过  http://localhost:8000 在浏览器访问前端页面

![](https://github.com/nastyray/UserCenter/blob/main/Image/V3~(LWECZEM%5BJPT(JYG_%7DK7.png)

