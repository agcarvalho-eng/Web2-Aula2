create table produto
    (id int AUTO_INCREMENT PRIMARY KEY,
    descricao varchar(80) not null,
    valor double not null);

insert into produto (descricao, valor) values ('Água sem gás', 5.00);
insert into produto (descricao, valor) values ('Guaraná', 7.50);
insert into produto (descricao, valor) values ('Fanta', 7.00);
insert into produto (descricao, valor) values ('Suco uva', 6.50);
insert into produto (descricao, valor) values ('Vodka Smirnoff', 9.00);
