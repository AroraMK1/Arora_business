CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE users (
    user_id BIGINT NOT NULL DEFAULT nextval('user_id_seq'),

    username VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    avatar_img VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    dob DATE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT pk_users
        PRIMARY KEY (user_id),

    CONSTRAINT uk_users_username
        UNIQUE (username),

    CONSTRAINT uk_users_email
        UNIQUE (email)
);