create table answers (
  id                        bigint not null,
  content                   varchar(255),
  question_id               bigint,
  user_id                   bigint,
  created_at                timestamp,
  constraint pk_answers primary key (id))
;

create table articles (
  id                        bigint not null,
  content                   varchar(255),
  user_id                   bigint,
  constraint pk_articles primary key (id))
;

create table comments (
  comment_for               varchar(31) not null,
  id                        bigint not null,
  content                   varchar(255),
  user_id                   bigint,
  created_at                timestamp,
  update_at                 timestamp,
  answer_id                 bigint,
  quesiton_id               bigint,
  constraint pk_comments primary key (id))
;

create table questions (
  id                        bigint not null,
  title                     varchar(255),
  content                   varchar(255),
  user_id                   bigint,
  created_at                timestamp,
  constraint pk_questions primary key (id))
;

create table tags (
  id                        bigint not null,
  name                      varchar(30),
  created_at                timestamp,
  updated_at                timestamp,
  user_id                   bigint,
  constraint pk_tags primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  salt                      varchar(255),
  password                  varchar(255),
  created_at                timestamp,
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table question_tag_r (
  questions_id                   bigint not null,
  tags_id                        bigint not null,
  constraint pk_question_tag_r primary key (questions_id, tags_id))
;
create sequence answers_seq;

create sequence articles_seq;

create sequence comments_seq;

create sequence questions_seq;

create sequence tags_seq;

create sequence users_seq;

alter table answers add constraint fk_answers_question_1 foreign key (question_id) references questions (id);
create index ix_answers_question_1 on answers (question_id);
alter table answers add constraint fk_answers_user_2 foreign key (user_id) references users (id);
create index ix_answers_user_2 on answers (user_id);
alter table articles add constraint fk_articles_user_3 foreign key (user_id) references users (id);
create index ix_articles_user_3 on articles (user_id);
alter table comments add constraint fk_comments_user_4 foreign key (user_id) references users (id);
create index ix_comments_user_4 on comments (user_id);
alter table comments add constraint fk_comments_answer_5 foreign key (answer_id) references answers (id);
create index ix_comments_answer_5 on comments (answer_id);
alter table comments add constraint fk_comments_quesiton_6 foreign key (quesiton_id) references questions (id);
create index ix_comments_quesiton_6 on comments (quesiton_id);
alter table questions add constraint fk_questions_user_7 foreign key (user_id) references users (id);
create index ix_questions_user_7 on questions (user_id);
alter table tags add constraint fk_tags_user_8 foreign key (user_id) references users (id);
create index ix_tags_user_8 on tags (user_id);



alter table question_tag_r add constraint fk_question_tag_r_questions_01 foreign key (questions_id) references questions (id);

alter table question_tag_r add constraint fk_question_tag_r_tags_02 foreign key (tags_id) references tags (id);
