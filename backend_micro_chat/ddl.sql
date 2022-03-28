
DROP SCHEMA IF EXISTS microchat_db;
create schema microchat_db;
use microchat_db;
drop user IF EXISTS 'microchat_user'@'localhost';
create user 'microchat_user'@'localhost' identified by '123456';

grant select, insert, delete, update on microchat_db .* to 'microchat_user'@'localhost';

create table cnv_conversa ( 
	cnv_id bigint unsigned not null auto_increment,
    cnv_assunto varchar(20) not null,
    primary key (cnv_id),
    unique key uni_cnv_assunto(cnv_assunto)
);

create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nickname varchar(20) not null,
    usr_email varchar(40) not null,
    usr_senha varchar(100) not null, 
    primary key (usr_id),
    unique key uni_usuario_nick(usr_nickname)
);

create table aut_autorizacao(
	aut_id bigint unsigned not null auto_increment,
    aut_nome varchar (20) not null,    
    primary key (aut_id),
    unique key uni_aut_nome (aut_nome)
);

create table aua_autorizacao_usuario(
	aua_usr_id bigint unsigned not null,
    aua_aut_id bigint unsigned not null,
    primary key (aua_usr_id, aua_aut_id),
    foreign key aua_usr_id_fk (aua_usr_id) references usr_usuario(usr_id),
    foreign key aua_aut_id_fk (aua_aut_id) references aut_autorizacao(aut_id)
);

create table ucu_conversa_usuario (
	ucu_usr_id bigint unsigned not null,
    ucu_cnv_id bigint unsigned not null,
    primary key (ucu_usr_id, ucu_cnv_id),
    foreign key usr_id_fk (ucu_usr_id) references usr_usuario(usr_id),
    foreign key cnv_id_fk (ucu_cnv_id) references cnv_conversa(cnv_id)
);

create table msg_mensagem (
    msg_id bigint unsigned not null auto_increment,
    msg_texto varchar(100) not null,
    msg_remetente_id bigint unsigned not null,
    msg_conversa_id bigint unsigned,
    primary key (msg_id),
    foreign key msg_usr_orign_fk (msg_remetente_id) references usr_usuario(usr_id),
    foreign key msg_cnv_fk (msg_conversa_id) references cnv_conversa(cnv_id)
);

insert into cnv_conversa (cnv_assunto) values("grupo_A");
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("joselito", "jose@teste.com", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("maria", "maria@teste.com", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("Bot", "bot@minichat.com.br", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into aut_autorizacao (aut_nome) values("ROLE_ADMIN");
insert into aut_autorizacao (aut_nome) values("ROLE_DEFAULT");
insert into aua_autorizacao_usuario (aua_usr_id, aua_aut_id) values ( 1, 1);
insert into aua_autorizacao_usuario (aua_usr_id, aua_aut_id) values ( 2, 2);
insert into aua_autorizacao_usuario (aua_usr_id, aua_aut_id) values ( 3, 2); 
insert into ucu_conversa_usuario values( 1, 1);
insert into ucu_conversa_usuario values( 2, 1);
insert into msg_mensagem (msg_texto, msg_remetente_id, msg_conversa_id) values ("ola_maria", 1, 1);
insert into msg_mensagem (msg_texto, msg_remetente_id, msg_conversa_id) values ("ola_joao", 2, 1);
