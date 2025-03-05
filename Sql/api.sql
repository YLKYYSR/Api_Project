# 所有的id字段全部都用bigint ,对应的实体类id字段用Long


create table if not exists usercenter.api

(
    id                bigint auto_increment comment '主键',
    name              varchar(256)                       not null comment '接口名',
    description       varchar(512)                       not null comment '接口描述',
    url               varchar(526)                       not null comment '接口地址',
    responseHeader    text                               null comment '响应头',
    requestHeader     text                               null comment '请求头',
    userId            bigint                             not null comment '创建人',
    status            tinyint  default 0                 not null comment '接口状态,0开启，1关闭',
    method            varchar(256)                       not null comment '请求类型',
    isDelete          tinyint  default 0                 not null comment '逻辑删除，0正常，1删除',
    `createTime`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    requestparameters text                               not null comment '请求参数',
    constraint api_info_pk
        unique (id)
)
    comment '接口信息';


INSERT INTO api (name, description, url, responseHeader, requestHeader, userId, status, method, isDelete,
                      createTime, updateTime, requestparameters)
VALUES ('获取用户信息', '根据用户ID获取用户的详细信息', 'https://api.example.com/user/get',
        '{"Content-Type":"application/json"}', '{"Authorization":"Bearer token"}', 1, 0, 'GET', 0, NOW(), NOW(),
        '{"id": "long"}'),

       ('创建用户', '创建新的用户', 'https://api.example.com/user/create', '{"Content-Type":"application/json"}',
        '{"Authorization":"Bearer token"}', 2, 0, 'POST', 0, NOW(), NOW(), '{"name": "string", "email": "string"}'),

       ('更新用户信息', '根据用户ID更新用户资料', 'https://api.example.com/user/update',
        '{"Content-Type":"application/json"}', '{"Authorization":"Bearer token"}', 1, 0, 'PUT', 0, NOW(), NOW(),
        '{"id": "long", "name": "string", "email": "string"}'),

       ('删除用户', '根据用户ID删除用户', 'https://api.example.com/user/delete', '{"Content-Type":"application/json"}',
        '{"Authorization":"Bearer token"}', 3, 1, 'DELETE', 0, NOW(), NOW(), '{"id": "long"}'),

       ('获取订单列表', '分页获取订单信息', 'https://api.example.com/order/list', '{"Content-Type":"application/json"}',
        '{"Authorization":"Bearer token"}', 2, 0, 'GET', 0, NOW(), NOW(), '{"page": "int", "size": "int"}'),

       ('创建订单', '创建新的订单', 'https://api.example.com/order/create', '{"Content-Type":"application/json"}',
        '{"Authorization":"Bearer token"}', 3, 0, 'POST', 0, NOW(), NOW(), '{"userId": "long", "items": "json"}'),

       ('获取天气信息', '获取指定城市的天气', 'https://api.example.com/weather', '{"Content-Type":"application/json"}',
        '', 4, 0, 'GET', 0, NOW(), NOW(), '{"city": "string"}');
