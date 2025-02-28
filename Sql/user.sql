create table usercenter.user
(
    id           bigint auto_increment
        primary key,
    userPassword varchar(256)                       not null,
    userName     varchar(256)                       null,
    createTime    datetime default CURRENT_TIMESTAMP null,
    updateTime    datetime default CURRENT_TIMESTAMP null,
    isDelete     tinyint  default 0                 null comment '删除0， 不删除1',
    userRole     tinyint  default 0                 null comment 'user 0, admin 1',
    userUrl      varchar(1024)                      null,
    gender       tinyint                            null comment '男0 女1',
    userStatus   int      default 0                 null comment '正常0 ，异常1',
    email        varchar(512)                       null,
    accessKey    varchar(512)                       not null comment '公开密钥',
    secretKey    varchar(512)                       not null comment '加密密钥'
);

