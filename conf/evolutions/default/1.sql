# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table menu (
  id                        bigint not null,
  name                      varchar(255),
  created                   timestamp,
  updated                   timestamp,
  raiz                      integer,
  orden                     integer,
  accion                    varchar(255),
  controlador               varchar(255),
  url                       varchar(255),
  icon                      varchar(255),
  constraint pk_menu primary key (id))
;

create table menu_tipousuario (
  id                        bigint not null,
  created                   timestamp,
  updated                   timestamp,
  menu_id                   bigint,
  tipousuario_id            bigint,
  constraint pk_menu_tipousuario primary key (id))
;

create table tipousuario (
  id                        bigint not null,
  name                      varchar(255),
  created                   timestamp,
  updated                   timestamp,
  constraint pk_tipousuario primary key (id))
;

create table usuario (
  id                        bigint not null,
  name                      varchar(255),
  created                   timestamp,
  updated                   timestamp,
  login                     varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  tipousuario_id            bigint,
  constraint pk_usuario primary key (id))
;

create sequence menu_seq;

create sequence menu_tipousuario_seq;

create sequence tipousuario_seq;

create sequence usuario_seq;

alter table menu_tipousuario add constraint fk_menu_tipousuario_menu_1 foreign key (menu_id) references menu (id) on delete restrict on update restrict;
create index ix_menu_tipousuario_menu_1 on menu_tipousuario (menu_id);
alter table menu_tipousuario add constraint fk_menu_tipousuario_tipousuari_2 foreign key (tipousuario_id) references tipousuario (id) on delete restrict on update restrict;
create index ix_menu_tipousuario_tipousuari_2 on menu_tipousuario (tipousuario_id);
alter table usuario add constraint fk_usuario_tipousuario_3 foreign key (tipousuario_id) references tipousuario (id) on delete restrict on update restrict;
create index ix_usuario_tipousuario_3 on usuario (tipousuario_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists menu;

drop table if exists menu_tipousuario;

drop table if exists tipousuario;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists menu_seq;

drop sequence if exists menu_tipousuario_seq;

drop sequence if exists tipousuario_seq;

drop sequence if exists usuario_seq;

