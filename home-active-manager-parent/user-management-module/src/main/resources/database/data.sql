INSERT INTO roles (role) VALUES ('ROLE_USER');
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role) VALUES ('ROLE_GUEST');

INSERT INTO users (username, password, first_name, role_id) VALUES ('user', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userName1', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, enabled, role_id) VALUES ('guest', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userName2', false, (SELECT role_id FROM roles WHERE role='ROLE_GUEST'));
INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('admin', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'adminFirstName', 'adminLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_ADMIN'));
INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('deleteUser', 'pass', 'deleteFirstName', 'deleteLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('updateUser', 'pass', 'updateFirstName', 'updateLastName' ,true, 'update.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, role_id) VALUES ('UserPassChange', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userPassChange', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
