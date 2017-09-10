-- >>> A aplicação "locadora" utiliza um banco de dados para persistir e manipular dados. O objetivo deste script é criar a base de dados que a aplicação necessita.
-- >>> Caso a base de dados já exista o script não terá efeito.

CREATE DATABASE IF NOT EXISTS db_locadora
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

