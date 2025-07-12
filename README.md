# 数据库与信息系统课程设计
姓名：苏巴提·阿不力克木 班级：软件193 学号：2018250821

### 题目
汽车修理管理系统

### 代码及版本管理
[输入链接说明](http://gitee.com/su_ba_ti/Car_repair_management)
![](https://images.gitee.com/uploads/images/2021/0625/063158_f53d7a93_6513783.png "版本.png")

### 问题描述
本项目学生需要通过此项目完成项目数据库设计、信息系统开发等。

### 技术栈
- 前端：Vue2.0 + Element—UI组件
- 后端：Java Spring boot + Mybatis-Plus
- 数据库：MySQL

### 系统介绍
系统分为两个权限部分：管理员用户登录模块，普通用户登录模块。管理员用户登录模块包括：员工信息，零件入库管理，零件出库管理。普通用户登录模块：汽车登记表，汽车修理表。

### 管理员用户登录模块：

#### 员工信息

- 批量录入员工信息，手动输入员工编号，员工姓名，小时工资。

- 查询并列出表中所有的员工信息。

- 修改指定员工的信息。

- 删除指定员工的信息。

#### 零件入库管理

- 批量录入零件入库信息，手动输入零件型号，入库数量，库存数量，价格。

- 查询并列出表中所有的零件入库信息

- 修改指定零件入库的信息。

- 删除指定零件入库的信息。

#### 零件出库管理

- 批量录入零件出库信息，手动输入零件型号，出库数量，库存数量，价格。

- 查询并列出表中所有的零件出库信息。

- 修改指定零件入库的信息。

- 删除指定零件入库的信息。

### 普通用户登录模块：

#### 汽车登记表

- 批量录入汽车登记信息，手动输入车主姓名，车牌号，汽车型号，登记时间。

- 查询并列出表中所有的汽车登记信息。

#### 汽车修理表

- 批量录入汽车修理信息，手动输入车牌号，员工编号，修理单编号，修理项目，修理用时。

- 查询并列出表中所有的汽车修理信息。

### 数据库概念模型设计（E-R图）
![](https://images.gitee.com/uploads/images/2021/0624/223636_cb5ecd43_6513783.png "报告截图.png")

用户列表

![](https://images.gitee.com/uploads/images/2021/0624/231711_2a614d20_6513783.png "用户.png")

员工信息表

![](https://images.gitee.com/uploads/images/2021/0624/231640_9d6983a1_6513783.png "员工.png")

零件入库表

![](https://images.gitee.com/uploads/images/2021/0624/231741_9f9dfd06_6513783.png "入库.png")

零件出库表

![](https://images.gitee.com/uploads/images/2021/0624/231835_792e0cb2_6513783.png "出库.png")

汽车登记表

![](https://images.gitee.com/uploads/images/2021/0624/231922_9f2adad0_6513783.png "登记.png")

汽车修理表

![](https://images.gitee.com/uploads/images/2021/0624/231945_6f2ae1fe_6513783.png "修理.png")


### 触发器
插入零件入库信息之后

```
BEGIN
    INSERT INTO partsdelivery(part_number,inventory_quantity,price) VALUE(new.part_number,new.inventory_quantity,new.price);
END
```
修改零件入库信息之后

```
BEGIN
    UPDATE partsdelivery SET part_number = new.part_number ,
    inventory_quantity = new.inventory_quantity , price = new.price WHERE part_number = new.part_number;
END
```

删除零件入库信息之后

```
BEGIN
    DELETE FROM partsdelivery WHERE part_number = old.part_number;
END
```
### 关于前端

![](https://images.gitee.com/uploads/images/2021/0625/064240_ccaee184_6513783.png "前端.png")

| Vue文件                | 说明             |
|----------------------|----------------|
| Login.vue            | 项目登录页面         |
| Home.vue             | 项目主体以及动态路由实现页面 |
| UserList.vue         | 用户列表页面         |
| EmployeeInfor        | 员工信息表页面        |
| PartsWarehousing.vue | 零件入库信息表页面      |
| PartsDelivery.vue    | 零件出库信息表页面      |
| CarRegistration.vue  | 汽车登记信息表页面      |
| CarRepair.vue        | 汽车修理信息表页面      |
| Welcome.vue          | 登录后欢迎页面        |


### 关于后端

![](https://images.gitee.com/uploads/images/2021/0624/233009_6b1afb18_6513783.png "1.png")
![](https://images.gitee.com/uploads/images/2021/0624/233023_2d8f538e_6513783.png "2.png")

nfig层
| 类         | 方法   | 说明
|-----------|------|------|
| WebConfig | addCorsMappings | 跨域处理 |

entity层
| 类             | 说明           |
|---------------|--------------|
| User          | 对应用户表的实体类    |
| MainMenu      | 主导航实体类       |
| SubMenu       | 分支导航实体类      |
| QueryInfo     | 模糊查询和获取页数实体类 |
| PartsW        | 对应零件入库表的实体类  |
| PartsD        | 对应零件出库表的实体类  |
| EmployeeInfor | 对应员工信息表的实体类  |
| Carreg        | 对应汽车登记表的实体类  |
| Carrep        | 对应汽车修理表的实体类  |

dao层
| 接口          | 说明         |
|-------------|------------|
| UserDao     | 对应用户列表的接口  |
| MenuDao     | 对应菜单栏的接口   |
| EmployeeDao | 对应员工信息表的接口 |
| PartsWDao   | 对应零件入库表的接口 |
| PartsDDao   | 对应零件出库表的接口 |
| CarregDao   | 对应汽车登记表的接口 |
| CarrepDao   | 对应汽车修理表的接口 |

LoginController
| 方法                            | 说明            |
|-------------------------------|---------------|
| login(@RequestBody User user) | 用户登录时前后端交互的方法 |


UserController
| 方法                                                                                  | 说明             |
|-------------------------------------------------------------------------------------|----------------|
| getUserList(QueryInfo queryInfo)                                                    | 获取所有用户信息的方法    |
| updateUserState(@RequestParam("id")Integer id, @RequestParam("state")Boolean state) | 状态按钮的方法        |
| addUser(@RequestBody User user)                                                     | 添加用户的方法        |
| deleteUser(int id)                                                                  | 删除用户的方法        |
| getUpdateUser(int id)                                                               | 修改用户的方法1(获取信息) |
| editUser(@RequestBody User user)                                                    | 修改用户的方法2       |

MenuController
| 方法            | 说明         |
|---------------|------------|
| getAllMenus() | 获取菜单栏信息的方法 |

EmployeeController
| 方法                                                     | 说明          |
|--------------------------------------------------------|-------------|
| getEmployeeList(QueryInfo queryInfo)                   | 获取所有员工信息的方法 |
| addEmployee(@RequestBody EmployeeInfor employeeinfor)  | 添加员工信息的方法   |
| deleteEmployee(int id)                                 | 删除员工信息的类别   |
| getUpdateEmployee(int id)                              | 修改员工信息的方法1  |
| editEmployee(@RequestBody EmployeeInfor imployeeinfor) | 修改员工信息的方法2  |

PartsWController
| 方法                                     | 说明            |
|----------------------------------------|---------------|
| getPartsWList(QueryInfo queryInfo)     | 获取所有零件入库信息的方法 |
| addPartsW(@RequestBody PartsW partsW)  | 添加零件入库信息的方法   |
| deletePartsW(int id)                   | 删除零件入库信息的方法   |
| getUpdatePartsW(int id)                | 修改零件入库信息的方法1  |
| editPartsW(@RequestBody PartsW partsW) | 修改零件入库信息的方法2  |

PartsDController
| 方法                                     | 说明            |
|----------------------------------------|---------------|
| getPartsDList(QueryInfo queryInfo)     | 获取所有零件出库信息的方法 |
| addPartsD(@RequestBody PartsD partsD)  | 添加零件出库信息的方法   |
| deletePartsD(int id)                   | 删除零件出库信息的方法   |
| getUpdatePartsD(int id)                | 修改零件出库信息的方法1  |
| editPartsD(@RequestBody PartsD partsD) | 修改零件出库信息的方法2  |

CargController
| 方法                                                  | 说明            |
|-----------------------------------------------------|---------------|
| getCarregList(QueryInfo queryInfo)                  | 获取所有汽车登记信息的方法 |
| public String addCarreg(@RequestBody Carreg carreg) | 添加汽车登记信息的方法   |

CarrepController
| 方法                                    | 说明            |
|---------------------------------------|---------------|
| getCarrepList(QueryInfo queryInfo)    | 获取所有汽车修理信息的方法 |
| addCarrep(@RequestBody Carrep carrep) | 添加汽车修理信息的方法   |

Mapper层
| 映射文件            | 说明             |
|-----------------|----------------|
| UserMapping     | 对应用户列表的映射文件    |
| MenuMapping     | 对应菜单栏的关系映射     |
| EmployeeMapping | 对应员工信息列表的映射文件  |
| PartsWMapping   | 对应零件入库的映射文件    |
| PartsDMapping   | 对应零件出库的映射文件    |
| CarregMapping   | 对应汽车登记信息表的映射文件 |
| CarrepMapping   | 对应汽车修理表的映射文件   |

### 项目效果
- 登录界面

![](https://images.gitee.com/uploads/images/2021/0625/065531_ce9a307e_6513783.png "登录啊.png")

- 登录后的欢迎界面

![](https://images.gitee.com/uploads/images/2021/0625/065636_e3927636_6513783.png "欢迎.png")

- 用户列表

![](https://images.gitee.com/uploads/images/2021/0625/065757_73459d81_6513783.png "用户.png")

- 员工信息表

![](https://images.gitee.com/uploads/images/2021/0625/065911_b2220480_6513783.png "员工信息.png")

- 零件入库表

![](https://images.gitee.com/uploads/images/2021/0625/070152_8cc95900_6513783.png "零件入库.png")

- 零件出库表

![](https://images.gitee.com/uploads/images/2021/0625/070240_c0f266f5_6513783.png "零件出库表.png")

- 汽车登记信息表

![](https://images.gitee.com/uploads/images/2021/0625/070700_e85a7e8a_6513783.png "汽车登记.png")

- 汽车修理信息表

![](https://images.gitee.com/uploads/images/2021/0625/070811_6ae93119_6513783.png "汽车修理.png")

###功能效果

- 查询

![](https://images.gitee.com/uploads/images/2021/0625/071219_f73c12e3_6513783.png "查询功能.png")

- 添加信息

![](https://images.gitee.com/uploads/images/2021/0625/072006_10b67c2f_6513783.png "汽车登记图.png")
![](https://images.gitee.com/uploads/images/2021/0625/072019_54c8997f_6513783.png "添加信息图.png")

- 删除

![](https://images.gitee.com/uploads/images/2021/0625/072142_6bbfda4a_6513783.png "删除图.png")

- 修改

修改前
![](https://images.gitee.com/uploads/images/2021/0625/072435_c206de83_6513783.png "修改前.png")
修改后
![](https://images.gitee.com/uploads/images/2021/0625/072507_141af341_6513783.png "修改中.png")
![](https://images.gitee.com/uploads/images/2021/0625/072522_d1c6b1f8_6513783.png "修改后.png")

### 演示视频

### 总结

经过这9天的努力，我终于将课程设计做完了，在这段日子里，可以说的是苦多于甜，但是可以学到很多很多的知识，同时不仅可以巩固之前所学过的知识，而且学到了很多在书本上所没想过的知识。在这次作业过程中，我遇到了许多困难，一遍又一遍的计算，一次又一次的设计方案修改暴露出了前期我在这方面的只是欠缺和经验不足。刚开始我什么都不会，通过b站vue和spring boot视频的简单学习，学习视频中别人怎么做项目的，怎么去前期设计和开发，这些都给了我开发的经验。此次设计也让我明白了思路即出路，有什么不懂不明白的地方要及时请教或上网查询，只要认真钻研，动脑思考，动手实践，就没有弄不懂的知识，收获颇多。