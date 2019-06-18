# CREATE TABLE `short_link`.`links`
# (
#     `urlid` INTEGER UNSIGNED                                        NOT NULL UNIQUE AUTO_INCREMENT,
#     `url`   VARCHAR(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
#     `date`  BIGINT UNSIGNED                                         NOT NULL,
#     `link`  VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
#     PRIMARY KEY (`urlid`),
#     UNIQUE INDEX `Index_Link` USING BTREE (`link`)
# )
#     ENGINE = InnoDB
#     CHARACTER SET utf8
#     COLLATE utf8_general_ci;
#
#
# CREATE TABLE `short_link`.`visitors`
# (
#     `urlid` INTEGER  UNSIGNED  NOT NULL,
#     `ip`    TEXT   NOT NULL,
#     `date`  BIGINT NOT NULL,
#     INDEX `Index_Url` USING BTREE (`urlid`),
#     FOREIGN KEY (`urlid`) REFERENCES `short_link`.`links` (`urlid`)
#         ON DELETE CASCADE
#         ON UPDATE CASCADE
# )
#     ENGINE = InnoDB
#     CHARACTER SET utf8
#     COLLATE utf8_general_ci;
