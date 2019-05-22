CREATE TABLE if not exists `Blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL,
  `remark` varchar(24) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 ;

-- insert into `Blog` (`id`, `name`, `remark`, `createtime`, `updatetime`) values('1','博客1','博客1',NULL,NULL);
--
-- insert into `Blog` (`id`, `name`, `remark`, `createtime`, `updatetime`) values('2','博客2','博客2',NULL,NULL);