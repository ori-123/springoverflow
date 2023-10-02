DROP TABLE IF EXISTS questions_tags;
DROP TABLE IF EXISTS tags;
ALTER TABLE IF EXISTS questions
    DROP CONSTRAINT IF EXISTS questions_accepted_answer_id_fkey;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS user_sessions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO roles (name)
VALUES ('USER');
INSERT INTO roles (name)
VALUES ('ADMIN');

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE user_sessions
(
    user_id INTEGER NOT NULL,
    token   VARCHAR(500)
);

CREATE TABLE questions
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER                              NOT NULL,
    title      VARCHAR(100)                        NOT NULL,
    content    TEXT                                NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) references users (id) ON DELETE CASCADE
);

CREATE TABLE answers
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER                              NOT NULL,
    question_id INTEGER                              NOT NULL,
    content     TEXT                                NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) references users (id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) references questions (id) ON DELETE CASCADE
);

ALTER TABLE questions
    ADD COLUMN accepted_answer_id INTEGER,
    ADD FOREIGN KEY (accepted_answer_id) REFERENCES answers (id);


CREATE TABLE tags
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE questions_tags
(
    question_id INTEGER NOT NULL,
    tag_id      INTEGER NOT NULL,
    PRIMARY KEY (question_id, tag_id),
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE
);
