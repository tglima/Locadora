--- >>> Nossa aplicação precisará de um usuário com acesso ao BD, para não expormos outras bases de dados e também por outras medidas de segurança, vamos criar um usuário  limitado apenas ao banco da aplicação.

-- >>> Nosso usuário será o: userBD
-- >>> Sua senha de acesso ao BD será: 418x3v5o

-- Criando o usuário
CREATE USER 'userBD'@'%'
IDENTIFIED BY '418x3v5o';

-- Definindo privilégios
GRANT CREATE, DELETE, SELECT, INSERT, UPDATE ON db_locadora . * TO 'userBD'@'%';

-- Recarregar privilégios
FLUSH PRIVILEGES;
