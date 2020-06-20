/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mypetstore

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 21/06/2020 03:22:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('ad', '9c1ad00a16a7c6', '12345678909', 'http://qbyy9dziv.bkt.clouddn.com/ad.jpg');
INSERT INTO `admin` VALUES ('admin', '21232f297a57a5', '12345678909', 'http://qbyy9dziv.bkt.clouddn.com/admin.jpg');
INSERT INTO `admin` VALUES ('adminn', '9c1ad00a16a7c6', '12345678909', 'http://qbyy9dziv.bkt.clouddn.com/adminn.jpg');
INSERT INTO `admin` VALUES ('yyx', '9c47fc50338e71', '12345678909', 'http://qbyy9dziv.bkt.clouddn.com/yyx.jpg');

-- ----------------------------
-- Table structure for cartitem
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `itemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `quantity` int(10) NOT NULL,
  PRIMARY KEY (`username`, `itemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cartitem
-- ----------------------------
INSERT INTO `cartitem` VALUES ('EST-1', 'wx', 7);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `categoryId` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('BIRDS', 'Birds');
INSERT INTO `category` VALUES ('CATS', 'Cats');
INSERT INTO `category` VALUES ('DOGS', 'Dogs');
INSERT INTO `category` VALUES ('FISH', 'Fish');
INSERT INTO `category` VALUES ('REPTILES', 'Reptiles');
INSERT INTO `category` VALUES ('testCategoryId', 'testCategoryName');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `itemid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  `attribute` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `itemProd`(`productid`) USING BTREE,
  CONSTRAINT `fk_item_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('EST-1', 'FI-SW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-1.jpg', 10.00, 'Large', 9999);
INSERT INTO `item` VALUES ('EST-10', 'K9-DL-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-10.jpg', 12.00, 'Spotted Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-11', 'RP-SN-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-11.jpg', 12.00, 'Venomless', 10000);
INSERT INTO `item` VALUES ('EST-12', 'RP-SN-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-12.jpg', 12.00, 'Rattleless', 10000);
INSERT INTO `item` VALUES ('EST-13', 'RP-LI-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-13.jpg', 12.00, 'Green Adult', 10000);
INSERT INTO `item` VALUES ('EST-14', 'FL-DSH-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-14.jpg', 12.00, 'Tailless', 10000);
INSERT INTO `item` VALUES ('EST-15', 'FL-DSH-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-15.jpg', 12.00, 'With tail', 10000);
INSERT INTO `item` VALUES ('EST-16', 'FL-DLH-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-16.jpg', 12.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-17', 'FL-DLH-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-17.jpg', 12.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-18', 'AV-CB-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-18.jpg', 92.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-19', 'AV-SB-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-19.jpg', 2.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-2', 'FI-SW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-2.jpg', 10.00, 'Small', 10000);
INSERT INTO `item` VALUES ('EST-20', 'FI-FW-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-20.jpg', 2.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-21', 'FI-FW-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-21.jpg', 1.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-22', 'K9-RT-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-22.jpg', 100.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-23', 'K9-RT-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-23.jpg', 100.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-24', 'K9-RT-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-24.jpg', 92.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-25', 'K9-RT-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-25.jpg', 90.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-26', 'K9-CW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-26.jpg', 92.00, 'Adult Male', 10000);
INSERT INTO `item` VALUES ('EST-27', 'K9-CW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-27.jpg', 90.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-28', 'K9-RT-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-28.jpg', 90.00, 'Adult Female', 10000);
INSERT INTO `item` VALUES ('EST-3', 'FI-SW-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-3.jpg', 12.00, 'Toothless', 9999);
INSERT INTO `item` VALUES ('EST-4', 'FI-FW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-4.jpg', 12.00, 'Spotted', 10000);
INSERT INTO `item` VALUES ('EST-5', 'FI-FW-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-5.jpg', 12.00, 'Spotless', 10000);
INSERT INTO `item` VALUES ('EST-6', 'K9-BD-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-6.jpg', 12.00, 'Male Adult', 10000);
INSERT INTO `item` VALUES ('EST-7', 'K9-BD-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-7.jpg', 12.00, 'Female Puppy', 10000);
INSERT INTO `item` VALUES ('EST-8', 'K9-PO-02', 'http://qbyy9dziv.bkt.clouddn.com/EST-8.jpg', 12.00, 'Male Puppy', 10000);
INSERT INTO `item` VALUES ('EST-9', 'K9-DL-01', 'http://qbyy9dziv.bkt.clouddn.com/EST-9.jpg', 12.00, 'Spotless Male Puppy', 10000);
INSERT INTO `item` VALUES ('testItemId', 'testProductId', 'http://qbyy9dziv.bkt.clouddn.com/testItemId.jpg', 10.00, 'testAttribute', 1);

-- ----------------------------
-- Table structure for lineitem
-- ----------------------------
DROP TABLE IF EXISTS `lineitem`;
CREATE TABLE `lineitem`  (
  `orderid` int(11) NOT NULL,
  `itemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` float(10, 2) NOT NULL,
  PRIMARY KEY (`orderid`, `itemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lineitem
-- ----------------------------
INSERT INTO `lineitem` VALUES (1028, 'EST-1', 1, 10.00);
INSERT INTO `lineitem` VALUES (1028, 'EST-3', 1, 12.00);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `logid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `objectid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`logid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 221 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderdate` date NULL DEFAULT NULL,
  `totalCount` int(99) NULL DEFAULT NULL,
  `subTotal` float(10, 2) NULL DEFAULT NULL,
  `paid` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendTo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkout` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dispatched` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1029 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1028, 'wx', '2020-06-15', 2, 22.00, 'Y', 'csu', 'N', 'N');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `categoryid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`productid`) USING BTREE,
  INDEX `productCat`(`categoryid`) USING BTREE,
  INDEX `productName`(`name`) USING BTREE,
  CONSTRAINT `fk_product_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('AV-CB-01', 'BIRDS', 'Amazon Parrot', 'Great companion for up to 75 years');
INSERT INTO `product` VALUES ('AV-SB-02', 'BIRDS', 'Finch', 'Great stress reliever');
INSERT INTO `product` VALUES ('FI-FW-01', 'FISH', 'Koi', 'Fresh Water fish from Japan');
INSERT INTO `product` VALUES ('FI-FW-02', 'FISH', 'Goldfish', 'Fresh Water fish from China');
INSERT INTO `product` VALUES ('FI-SW-01', 'FISH', 'Angelfish', 'Salt Water fish from Australia');
INSERT INTO `product` VALUES ('FI-SW-02', 'FISH', 'Tiger Shark', 'Salt Water fish from Australia');
INSERT INTO `product` VALUES ('FL-DLH-02', 'CATS', 'Persian', 'Friendly house cat, doubles as a princess');
INSERT INTO `product` VALUES ('FL-DSH-01', 'CATS', 'Manx', 'Great for reducing mouse populations');
INSERT INTO `product` VALUES ('K9-BD-01', 'DOGS', 'Bulldog', 'Friendly dog from England');
INSERT INTO `product` VALUES ('K9-CW-01', 'DOGS', 'Chihuahua', 'Great companion dog');
INSERT INTO `product` VALUES ('K9-DL-01', 'DOGS', 'Dalmation', 'Great dog for a Fire Station');
INSERT INTO `product` VALUES ('K9-PO-02', 'DOGS', 'Poodle', 'Cute dog from France');
INSERT INTO `product` VALUES ('K9-RT-01', 'DOGS', 'Golden Retriever', 'Great family dog');
INSERT INTO `product` VALUES ('K9-RT-02', 'DOGS', 'Labrador Retriever', 'Great hunting dog');
INSERT INTO `product` VALUES ('RP-LI-02', 'REPTILES', 'Iguana', 'Friendly green friend');
INSERT INTO `product` VALUES ('RP-SN-01', 'REPTILES', 'Rattlesnake', 'Doubles as a watch dog');
INSERT INTO `product` VALUES ('testProductId', 'testCategoryId', 'testName', 'testText');

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `userid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `langpref` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `favcategory` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mylistopt` tinyint(1) NULL DEFAULT NULL,
  `banneropt` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES ('a', 'japanese', 'DOGS', NULL, NULL);
INSERT INTO `profile` VALUES ('ACID', 'english', 'CATS', 1, 1);
INSERT INTO `profile` VALUES ('j2ee', 'English', 'DOGS', 1, 1);
INSERT INTO `profile` VALUES ('xyz', 'japanese', 'DOGS', NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('wx', '79b4de7cf79777', 'wx@csu.deu.cn', 'csu', '12345678909', 'http://qbyy9dziv.bkt.clouddn.com/wx.jpg');

SET FOREIGN_KEY_CHECKS = 1;
