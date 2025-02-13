drop table if exists user;

create table usercenter.user
(
    id           bigint auto_increment
        primary key,
    userPassword varchar(256)                       not null,
    userName     varchar(256)                       null,
    creatTime    datetime default CURRENT_TIMESTAMP null,
    updatTime    datetime default CURRENT_TIMESTAMP null,
    isDelete     tinyint  default 0                 null comment '删除1， 不删除0',
    userRole     tinyint  default 0                 null comment 'user 0, admin 1',
    userUrl      varchar(1024)                      null,
    gender       tinyint                            null comment '男0 女1',
    userStatus   int      default 0                 null comment '正常0 ，异常1',
    email        varchar(512)                       null
);



use usercenter;
INSERT INTO user (id, userPassword, userName, creatTime, updatTime, isDelete, userRole, userUrl, gender, userStatus,
                  email)
VALUES (1, '123456', 'Alice', '2025-01-01 10:00:00', '2025-01-01 10:00:00', 0, 0, 'https://example.com/alice', 1, 0,
        'alice@example.com'),
       (2, 'abc2525def', 'Bob', '2025-01-02 12:00:00', '2025-01-02 12:00:00', 0, 1, 'https://example.com/bob', 0, 0,
        'bob@example.com'),
       (3, 'qwerty', 'Charlie', '2025-01-03 14:30:00', '2025-01-03 14:30:00', 0, 0, 'https://example.com/charlie', 0, 1,
        'charlie@example.com'),
       (4, 'password', 'Diana', '2025-01-04 16:45:00', '2025-01-04 16:45:00', 0, 0, 'https://example.com/diana', 1, 0,
        'diana@example.com'),
       (5, 'pass1234', 'Eve', '2025-01-05 18:20:00', '2025-01-05 18:20:00', 1, 0, 'https://example.com/eve', 1, 1,
        'eve@example.com'),
       (6, '525525', 'Frank', '2025-01-06 20:10:00', '2025-01-06 20:10:00', 0, 0, NULL, 0, 0, 'frank@example.com'),
       (7, 'test987', 'Grace', '2025-01-07 22:05:00', '2025-01-07 22:05:00', 0, 1, NULL, 1, 0, 'grace@example.com'),
       (8, 'secure1', 'Hank', '2025-01-08 23:15:00', '2025-01-08 23:15:00', 1, 0, 'https://example.com/hank', 0, 1,
        'hank@example.com'),
       (9, '2525', 'Ivy', '2025-01-09 09:40:00', '2025-01-09 09:40:00', 0, 0, 'https://example.com/ivy', 1, 0,
        'ivy@example.com'),
       (10, 'admin123', 'Jack', '2025-01-10 11:25:00', '2025-01-10 11:25:00', 0, 1, NULL, 0, 0, 'jack@example.com');
