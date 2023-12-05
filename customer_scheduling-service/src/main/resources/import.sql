INSERT INTO clients (name, birthday, phone) VALUES ('Jhone', '2004-02-11', '(11) 91112-1113');
INSERT INTO clients (name, birthday, phone) VALUES ('Pedro', '2000-10-07', '(11) 90002-0001');

INSERT INTO schedulings (client_id, col_day, col_time) VALUES (1, '2023-12-07', 1);
INSERT INTO schedulings (client_id, col_day, col_time) VALUES (1, '2023-12-20', 3);
INSERT INTO schedulings (client_id, col_day, col_time) VALUES (2, '2023-11-30', 2);

INSERT INTO job (duration_minutes, price, name) VALUES (60,30.00,'Corte de cabelo');
INSERT INTO job (duration_minutes, price, name) VALUES (30,10.00,'Sobrancelha');
INSERT INTO job (duration_minutes, price, name) VALUES (60,20.00,'Barba');

INSERT INTO tb_schedulings_jobs (job_id, scheduling_id) VALUES (1,1)
INSERT INTO tb_schedulings_jobs (job_id, scheduling_id) VALUES (1,3)
INSERT INTO tb_schedulings_jobs (job_id, scheduling_id) VALUES (2,1)
INSERT INTO tb_schedulings_jobs (job_id, scheduling_id) VALUES (3,3)
INSERT INTO tb_schedulings_jobs (job_id, scheduling_id) VALUES (3,2)
