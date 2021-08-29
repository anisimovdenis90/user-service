/* Задача 1
Найти всех пользователей, которые зарегистрировались, но не подтвердили почту.
*/
SELECT tUser.Userid, tUser.Name, tUser.Pass, tUser.Mail
FROM tUser
INNER JOIN tAudit ON tUser.Userid = tAudit.Userid
WHERE tAudit.ActionType = 1
AND (
        SELECT tAudit.ActionType
        FROM tAudit
        WHERE tAudit.UserID = tUser.UserID
        AND tAudit.ActionType = 2
    ) IS NULL;


/* Задача 2
Найти всех пользователей у которых просрочился токен доступа, текущую дату время из функции GetDate()
*/
SELECT tUser.Userid, tUser.Name, tUser.Pass, tUser.Mail
FROM tUser u
INNER JOIN tAccessToken a ON tUser.Userid = tAccessToken.Userid
WHERE tAccessToken.ExpireDate <= getdate();


/* Задача 3
Найти всех пользователей которые зарегистрировались, но ни разу не заходили в систему.
*/
SELECT tUser.UserID, tUser.Name, tUser.Pass, tUser.Mail
FROM tUser
INNER JOIN tAudit ON tUser.Userid = tAudit.UserID
WHERE tAudit.ActionType = 1
AND (
        SELECT tAudit.ActionType
        FROM tAudit
        WHERE tAudit.UserID = tUser.UserID
        AND tAudit.ActionType = 3
    ) IS NULL;


/* Задача 4
Найти первых четырех пользователей, которые чаще всего неверно вводят пароль.
*/
SELECT TOP(4) count(*) fails, tUser.Userid, tUser.Name, tUser.Pass, tUser.Mail
FROM tUser
INNER JOIN tAudit ON tUser.Userid = tAudit.Userid
WHERE tAudit.ActionType = 5
GROUP BY tUser.Userid, tUser.Name, tUser.Pass, tUser.Mail
ORDER BY fails DESC;