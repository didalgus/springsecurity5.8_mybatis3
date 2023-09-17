USE demo_security;
INSERT INTO board (title, content, reg_type, user_id) VALUES ('YOTSUBA&!', '언제나 오늘이 가장 즐거운 날.', 'User', 'azmakiyhiko');
INSERT INTO board (title, content, reg_type, user_id) VALUES ('정글은 언제나 하레와 구우', 'Ongaku mo Itsumo Hare Nochi Guu: Jungle wa Itsumo Hare Nochi Guu Original Soundtrack.', 'User', 'RenjuroKindaichi');
INSERT INTO board (title, content, reg_type, user_id) VALUES ('죽음에 관하여', '언제나 오삶과 죽음의 경계선, 그 곳엔 누가 있을까.', 'User', 'sini');

INSERT INTO user (id, name, password, authority) VALUES ('azma', 'azmakiyhiko', '1234', 'ROLE_ADMIN');
INSERT INTO user (id, name, password, authority) VALUES ('renjuro', 'RenjuroKindaichi', '1234', 'ROLE_USER');