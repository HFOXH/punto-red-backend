CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              cell_phone VARCHAR(15) NOT NULL,
                              message VARCHAR(255) NOT NULL,
                              transactional_id CHAR(36) NOT NULL,
                              value INT NOT NULL,
                              transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);