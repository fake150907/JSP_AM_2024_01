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

INSERT INTO article
SET regDate = NOW(),
title = '고양이',
`body` = '냥냥';

INSERT INTO article
SET regDate = NOW(),
title = '강아지',
`body` = '멍멍';

INSERT INTO article
SET regDate = NOW(),
title = '병아리',
`body` = '삐약삐약';

INSERT INTO article
SET regDate = NOW(),
title = '수달',
`body` = '삑뺙';

INSERT INTO article
SET regDate = NOW(),
title = '고수달',
`body` = '초고수달';

INSERT INTO article
SET regDate = NOW(),
title = '코끼리',
`body` = '뿌우뿌우';

INSERT INTO article
SET regDate = NOW(),
title = CONCAT('제목__', RAND()),
`body` = CONCAT('내용__',RAND());

SELECT *
FROM article
ORDER BY id DESC;