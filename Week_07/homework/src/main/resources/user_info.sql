drop table if exists user_info;
create table user_info(
id bigint(20) AUTO_INCREMENT NOT NULL ,
uname varchar(64) DEFAULT NULL,
upassword varchar(64) DEFAULT NULL,
nickname varchar(64) DEFAULT NULL,
phone varchar(64) DEFAULT NULL,
status int (1) DEFAULT NULL,
createtime bigint(20) DEFAULT NULL,
updatetime bigint(20) DEFAULT NULL,
primary key (id) 
)engine=InnoDB charset=utf8mb4 ;
