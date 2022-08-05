INSERT INTO users
(
   name,
   password,
   mail,
   create_datetime
)
VALUES
(
   'user',
   'password',
   'email@co.com',
   LOCALTIME ()
),
(
   'name',
   'password',
   'sample@co.jp',
   LOCALTIME ()
),
(
   'salmon',
   'password',
   'salmon@co.com',
   LOCALTIME ()
);
