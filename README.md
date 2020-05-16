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
