CREATE TABLE IF NOT EXISTS rest_api_mysql.users
(
    id        INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_status VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS rest_api_mysql.files
(
    id        INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(100) NOT NULL,
    file_path VARCHAR(100) NOT NULL,
    file_status VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS rest_api_mysql.events
(
    id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    file_id INTEGER NOT NULL,
    event_status VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_events_user_id (user_id) REFERENCES users (id),
    FOREIGN KEY fk_events_file_id (file_id) REFERENCES files (id),
    UNIQUE (user_id, file_id)
    );