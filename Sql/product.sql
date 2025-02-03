create table if not exists usercenter.production
(
    id         int                      auto_increment
        primary key,
    price      int                      null,
    decip      varchar(512)             null,
    isDelete   tinyint  default 0       null comment '下架0，上架1',
    creatTime  datetime default (now()) null,
    updateTime datetime default (now()) null
);
INSERT INTO usercenter.production (id, price, decip, isDelete, creatTime, updateTime)
VALUES
    (1, 299, '这是一个高质量的电子产品，适合家庭使用。', 1, NOW(), NOW()), -- 上架
    (2, 499, '适合办公使用的笔记本电脑，性能强劲，轻便易携带。', 1, NOW(), NOW()), -- 上架
    (3, 199, '一款舒适的耳机，音质优秀，适合长时间佩戴。', 0, NOW(), NOW()), -- 下架
    (4, 799, '顶级智能手机，支持5G网络，拥有超清摄像头。', 1, NOW(), NOW()), -- 上架
    (5, 1200, '专业级相机，适合摄影师使用，提供高质量照片。', 1, NOW(), NOW()), -- 上架
    (6, 850, '高效空气净化器，适合各种家庭环境。', 0, NOW(), NOW()); -- 下架

