DROP DATABASE IF EXISTS `JSP_AM`;
CREATE DATABASE `JSP_AM`;
USE `JSP_AM`;

CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw CHAR(200) NOT NULL,
    `name` CHAR(100) NOT NULL
);

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL;

#############################################
# article 테이블 데이터 넣기.

INSERT INTO article
SET regDate = NOW(),
title = '고양이',
`body` = '냥냥',
memberId = 1;

INSERT INTO article
SET regDate = NOW(),
title = '강아지',
`body` = '멍멍',
memberId = 2;

INSERT INTO article
SET regDate = NOW(),
title = '병아리',
`body` = '삐약삐약',
memberId = 4;

INSERT INTO article
SET regDate = NOW(),
title = '수달',
`body` = '삑뺙',
memberId = 3;

INSERT INTO article
SET regDate = NOW(),
title = '고수달',
`body` = '초고수달',
memberId = 3;

INSERT INTO article
SET regDate = NOW(),
title = '코끼리',
`body` = '뿌우뿌우',
memberId = 5;

INSERT INTO article
SET regDate = NOW(),
title = CONCAT('제목__', RAND()),
`body` = CONCAT('내용__',RAND()),
memberId = 3;

SELECT *
FROM article
ORDER BY id DESC;
#################################
# member 테이블 데이터 넣기
INSERT INTO `member`
SET regDate = NOW(),
loginId = 'loginId1',
loginPw = 'loginPw1',
`name` = '고먐미';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'loginId2',
loginPw = 'loginPw2',
`name` = '강아지';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'loginId3',
loginPw = 'loginPw3',
`name` = '고수달';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'loginId4',
loginPw = 'loginPw4',
`name` = '병아리';

INSERT INTO `member`
SET regDate = NOW(),
loginId = 'loginId5',
loginPw = 'loginPw5',
`name` = '코끼리';

SELECT *
FROM `member`
ORDER BY id DESC;

SELECT COUNT(*)
FROM `member`
WHERE loginId = 'poi' 
AND loginPw = 'qweiop';

