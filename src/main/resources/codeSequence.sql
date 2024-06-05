CREATE TABLE IF NOT EXISTS user_code_sequence (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  current_value BIGINT NOT NULL
);
INSERT INTO user_code_sequence (current_value) VALUES (100000);


CREATE TABLE IF NOT EXISTS company_code_sequence (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  current_value BIGINT NOT NULL
);
INSERT INTO company_code_sequence (current_value) VALUES (100000);