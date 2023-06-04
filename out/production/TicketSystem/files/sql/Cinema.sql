-- 创建电影院影院表
CREATE TABLE IF NOT EXISTS `ticket_cinema`(
    `id` INT AUTO_INCREMENT COMMENT '影院id',
    `name` VARCHAR(50) NOT NULL COMMENT '影院名称',
    `address` VARCHAR(200) NOT NULL COMMENT '影院地址',
    `opening_time` VARCHAR(50) not null COMMENT '开门时间',
    `shutdown_time` VARCHAR(50) not null COMMENT '关门时间',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;