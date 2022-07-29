INSERT INTO users
(
   name,
   password,
   mail,
   create_datetime
)
VALUES
(
   'サーモン',
   'password',
   'email@co.com',
   LOCALTIME ()
);