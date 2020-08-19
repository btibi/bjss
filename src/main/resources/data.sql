INSERT INTO cv (cv_id, name) VALUES
  (1, 'Jim'),
  (2, 'Jack'),
  (3, 'John');

INSERT INTO skills (cv_id, skill) VALUES
  (1, 'Java'),
  (1, 'Spring'),
  (1, 'Hibernate'),
  (2, 'Agile'),
  (2, 'Scrum'),
  (3, 'Docker'),
  (3, 'AWS');

INSERT INTO company_history (cv_id, company_name, start_date, end_date) VALUES
  (1, 'BJSS', {d '2015-06-01'}, null),
  (1, 'Microsoft', {d '2012-08-01'}, {d '2015-05-31'}),
  (2, 'Apple', {d '2018-07-01'}, null),
  (2, 'Facebook', {d '2010-01-01'}, {d '2018-06-30'}),
  (2, 'Amazon', {d '2007-10-01'}, {d '2009-11-30'}),
  (3, 'Google', {d '2020-08-01'}, null);

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 4 increment by 1;