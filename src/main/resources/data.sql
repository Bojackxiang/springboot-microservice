INSERT INTO USER_DAO (id, local_date, name)
VALUES (10001, '2024-01-01', 'User 1'),
       (10002, '2024-01-02', 'User 2');

INSERT INTO POST(Id, description, author_id)
VALUES (20001, 'Post 1', 10001),
       (20002, 'Post 2', 10002);