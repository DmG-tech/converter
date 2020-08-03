DROP TABLE IF EXISTS conversions;
DROP TABLE IF EXISTS quotes;
DROP TABLE IF EXISTS currencies;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    username VARCHAR                          NOT NULL,
    password VARCHAR                          NOT NULL,
    enabled  BOOL                DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX users_unique_username_idx ON users (username);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE currencies
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    num_code  VARCHAR(3) UNIQUE NOT NULL,
    char_code VARCHAR(3) UNIQUE NOT NULL,
    nominal   INT               NOT NULL CHECK (nominal > 0),
    name      VARCHAR(50)       NOT NULL
);

CREATE TABLE quotes
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    currency_id INTEGER   NOT NULL,
    rub_value   REAL      NOT NULL,
    date        TIMESTAMP NOT NULL,
    FOREIGN KEY (currency_id) REFERENCES currencies (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX quotes_unique_currency_date_idx ON quotes (currency_id, date);

CREATE TABLE conversions
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id          INTEGER   NOT NULL,
    from_currency_id INTEGER   NOT NULL,
    to_currency_id   INTEGER   NOT NULL,
    input_value      REAL      NOT NULL,
    result_value     REAL      NOT NULL,
    date             TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);