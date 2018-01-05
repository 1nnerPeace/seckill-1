# Host: localhost  (Version 5.7.17-log)
# Date: 2017-12-26 17:14:01
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "seckill"
#

DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
    #索引,加速查询
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

#
# Data for table "seckill"
#

INSERT INTO `seckill` VALUES (1000,'1000元秒杀iPhone8',100,'2017-10-01 00:00:00','2017-12-31 00:00:00','2017-12-26 17:13:06'),
                              (1001,'500元秒杀ipad2017',200,'2017-10-01 00:00:00','2017-12-31 00:00:00','2017-12-26 17:13:06'),
                              (1002,'300元秒杀小米6',300,'2017-10-01 00:00:00','2017-12-31 00:00:00','2017-12-26 17:13:06'),
                              (1003,'200元秒杀红米note4x',400,'2017-10-01 00:00:00','2017-12-31 00:00:00','2017-12-26 17:13:06');

#
# Structure for table "success_killed"
#

DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),#联合组建:id和手机号双主键确定唯一性
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

#
# Data for table "success_killed"
#

