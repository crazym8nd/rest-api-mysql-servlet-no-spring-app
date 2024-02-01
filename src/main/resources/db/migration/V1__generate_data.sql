INSERT INTO users(user_name,user_status) VALUES
                                             ('testuser1','ACTIVE'),
                                             ('testuser2','ACTIVE'),
                                             ('testuser3','ACTIVE');
INSERT INTO files (file_name, file_path,file_status) VALUES
                                                         ('testfile1', '/fake_path/testfile1','ACTIVE'),
                                                         ('testfile2', '/fake_path/testfile2','ACTIVE'),
                                                         ('testfile3', '/fake_path/testfile3','ACTIVE');
INSERT INTO events (user_id, file_id,event_status) VALUES
                                                       (1,1,'ACTIVE'),
                                                       (2,2,'ACTIVE'),
                                                       (3,3,'ACTIVE');