DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS author CASCADE;
DROP TABLE IF EXISTS reader CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS genre CASCADE;
DROP TABLE IF EXISTS audit_log CASCADE;

CREATE TABLE genre (
                       name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE member (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100)
);

CREATE TABLE author (
                        member_id INTEGER PRIMARY KEY REFERENCES member(id) ON DELETE CASCADE
);

CREATE TABLE reader (
                        member_id INTEGER PRIMARY KEY REFERENCES member(id) ON DELETE CASCADE
);

CREATE TABLE book (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author_id INTEGER NOT NULL REFERENCES author(member_id),
                      genre VARCHAR(50) NOT NULL REFERENCES genre(name),
                      is_available BOOLEAN NOT NULL DEFAULT TRUE,
                      borrower_id INTEGER REFERENCES reader(member_id),
                      reserved_until TIMESTAMP
);

CREATE INDEX idx_book_title ON book(title);
CREATE INDEX idx_member_name ON member(name);

INSERT INTO genre (name) VALUES
                             ('FICTION'),
                             ('NONFICTION'),
                             ('MYSTERY'),
                             ('SCIENCE'),
                             ('HISTORY'),
                             ('ROMANCE'),
                             ('KIDS');

INSERT INTO member (name, email) VALUES ('carolina', 'carolina@cca.com') RETURNING id;
INSERT INTO author (member_id) VALUES (1);

INSERT INTO member (name, email) VALUES ('corina', 'corina@cca.com') RETURNING id;
INSERT INTO author (member_id) VALUES (2);

INSERT INTO member (name, email) VALUES ('felix', 'felix@cca.com') RETURNING id;
INSERT INTO reader (member_id) VALUES (3);

INSERT INTO book (title, author_id, genre) VALUES ('Harry Potter', 1, 'FICTION');
INSERT INTO book (title, author_id, genre) VALUES ('The Philosopher''s Stone', 2, 'FICTION');
INSERT INTO book (title, author_id, genre) VALUES ('The Chamber of Secrets', 2, 'FICTION');
INSERT INTO book (title, author_id, genre) VALUES ('The Prisoner of Azkaban', 1, 'FICTION');
INSERT INTO book (title, author_id, genre) VALUES ('A Brief History of Time', 2, 'SCIENCE');
INSERT INTO book (title, author_id, genre) VALUES ('The Da Vinci Code', 1, 'MYSTERY');
INSERT INTO book (title, author_id, genre) VALUES ('Sapiens: A Brief History of Humankind', 1, 'HISTORY');
INSERT INTO book (title, author_id, genre) VALUES ('Pride and Prejudice', 1, 'ROMANCE');
INSERT INTO book (title, author_id, genre) VALUES ('Charlotte''s Web', 2, 'KIDS');
INSERT INTO book (title, author_id, genre) VALUES ('Educated', 1, 'NONFICTION');
INSERT INTO book (title, author_id, genre) VALUES ('The Silent Patient', 2, 'MYSTERY');

CREATE OR REPLACE FUNCTION add_member_with_role(
    p_name VARCHAR,
    p_email VARCHAR,
    p_role VARCHAR
) RETURNS VOID AS $$
DECLARE
    new_member_id INTEGER;
BEGIN
    INSERT INTO member (name, email) VALUES (p_name, p_email) RETURNING id INTO new_member_id;

    IF lower(p_role) = 'author' THEN
        INSERT INTO author (member_id) VALUES (new_member_id);
    ELSIF lower(p_role) = 'reader' THEN
        INSERT INTO reader (member_id) VALUES (new_member_id);
    ELSE
        RAISE EXCEPTION 'Invalid role %', p_role;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE audit_log (
                           id SERIAL PRIMARY KEY,
                           event_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           user_id INTEGER,
                           entity VARCHAR(50) NOT NULL,
                           entity_id INTEGER,
                           action VARCHAR(50) NOT NULL,
                           description TEXT
);
