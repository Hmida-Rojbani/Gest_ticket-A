create table client (id integer not null auto_increment, courriel varchar(255), date_de_naissance date, nom varchar(255), prenom varchar(255), telephone varchar(255), primary key (id)) engine=InnoDB
create table met_entity (dtype varchar(31) not null, id integer not null auto_increment, name varchar(255) not null, prix double precision not null, primary key (id)) engine=InnoDB
create table table_entity (numero integer not null auto_increment, nb_couverts integer not null, supplement double precision not null, type varchar(255), primary key (numero)) engine=InnoDB
create table ticket_entity (numero integer not null auto_increment, addition double precision not null, date_time datetime, nb_coverts integer not null, client_id integer, table_numero integer, primary key (numero)) engine=InnoDB
create table ticket_entity_mets (ticket_entity_numero integer not null, mets_id integer not null) engine=InnoDB
alter table met_entity add constraint UK_148649msanb4f3eoun9q6rkpb unique (name)
alter table ticket_entity add constraint FKccc6pcwmjcccckwresg0550q1 foreign key (client_id) references client (id)
alter table ticket_entity add constraint FKijlgpe3j3aw917448id23646m foreign key (table_numero) references table_entity (numero)
alter table ticket_entity_mets add constraint FK174n9wgohypt155tb3yg97nto foreign key (mets_id) references met_entity (id)
alter table ticket_entity_mets add constraint FKaxq0bv991jdh2uc8hg8pkhlsf foreign key (ticket_entity_numero) references ticket_entity (numero)
