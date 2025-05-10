CREATE TABLE IF NOT EXISTS bank_clients (
    id BIGSERIAL PRIMARY KEY,
    name varchar(50) NOT NULL,
    address varchar(100) NOT NULL,
    active_status boolean NOT NULL
    );

CREATE TABLE IF NOT EXISTS bank_accounts (
                               id BIGSERIAL PRIMARY KEY,
                               client_id BIGSERIAL NOT NULL,
                               account_type varchar(50) NOT NULL,
                               balance decimal(12, 2) NOT NULL,
                               currency varchar(10) NOT NULL,
                               active boolean DEFAULT TRUE,
                               CONSTRAINT fk_client
                                   FOREIGN KEY (client_id)
                                       REFERENCES bank_clients(id)
                                       ON DELETE CASCADE
);
