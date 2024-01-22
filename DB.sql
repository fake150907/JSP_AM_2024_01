DROP DATABASE IF EXISTS `JSP_AM`;
CREATE DATABASE `JSP_AM`;
USE `JSP_AM`;

CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
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

SELECT *
FROM article
ORDER BY id DESC;