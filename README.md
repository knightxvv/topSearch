## topSearch
对多个网站的实时热点进行抓取、分析、展示

### 当前功能
1.热榜抓取
<br>
2.邮件发送(订阅)
<br>
3.图表展示(echarts)

### 技术栈
目前：tomcat,springboot,mysql,<del>jdbc</del>,mybatis
<br>
计划：nginx,redis,kafka


### 问题
1.linux定时任务crontab时区和系统时区不一致，需要修改时区并重启服务
<br>
2.运行一段时间定时任务后会出现mysql的max_allowed_packet过小导致后续所有sql出错，定时任务中设定每天23:55执行mysqldump导出数据并清空数据表。
排查发现是B站sql长度过长导致出错，
<br>
05.16 再次发现是由于max_allowed_packet被修改过，查看错误日志有大量未知ip的登录请求，因此修改mysql端口并开启全局日志，效果有待观察。
另一方法：当sql再次出错时，发送邮件通知

<br>
2020.06.14 22:34
<br>
数据量1434251，fetch用时2.844s，只有主键索引

<br>
2020.07.08 添加用户注册，邮件发送与激活功能
<br>
下一步：redis做用户登录服务器，邮箱校验

