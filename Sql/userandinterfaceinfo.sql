CREATE TABLE `userandinterfaceinfo`
(
    `id`           bigint   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `userId`       bigint   NOT NULL COMMENT '用户id',
    `apiId`        bigint   NOT NULL COMMENT '接口id',
    `isDelete`     tinyint  NOT NULL DEFAULT '0' COMMENT '0存在，1删除',
    `createTime`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`       tinyint  NOT NULL DEFAULT '0' COMMENT '0正常，1禁用',
    `totalNum`     int      NOT NULL COMMENT '该用户从账号存在开始总共调用的次数',
    `remainderNum` int               DEFAULT NULL COMMENT '剩余用户可接口调用次数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户和接口调用关系表'

INSERT INTO `userandinterfaceinfo` (`userId`, `apiId`, `isDelete`, `createTime`, `updateTime`, `status`, `totalNum`,
                                    `remainderNum`)
VALUES (1, 101, 0, NOW(), NOW(), 0, 50, 10),
       (2, 102, 0, NOW(), NOW(), 0, 120, 30),
       (3, 103, 0, NOW(), NOW(), 1, 75, 0),
       (4, 104, 0, NOW(), NOW(), 0, 200, 50),
       (5, 105, 1, NOW(), NOW(), 1, 0, 0),
       (6, 106, 0, NOW(), NOW(), 0, 500, 100),
       (7, 107, 0, NOW(), NOW(), 0, 30, 5),
       (8, 108, 0, NOW(), NOW(), 0, 80, 20),
       (9, 109, 0, NOW(), NOW(), 0, 90, 10),
       (10, 110, 0, NOW(), NOW(), 0, 150, 40);
