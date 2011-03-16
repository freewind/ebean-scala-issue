create table articles (
  id                        bigint not null,
  content                   varchar(255),
  user_id                   bigint,
  constraint pk_articles primary key (id))
;

create table questions (
  id                        bigint not null,
  content                   varchar(255),
  user_id                   bigint,
  constraint pk_questions primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;

create sequence articles_seq;

create sequence questions_seq;

create sequence users_seq;

alter table articles add constraint fk_articles_user_1 foreign key (user_id) references users (id);
create index ix_articles_user_1 on articles (user_id);
alter table questions add constraint fk_questions_user_2 foreign key (user_id) references users (id);
create index ix_questions_user_2 on questions (user_id);


