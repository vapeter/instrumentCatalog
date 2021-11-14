CREATE DATABASE IF NOT EXISTS `keycloak`;
CREATE DATABASE IF NOT EXISTS `catalog`;

CREATE USER 'keycloak'@'%' IDENTIFIED BY 'KeycloakAdmin';
GRANT ALL ON `keycloak`.* TO 'keycloak'@'%';

CREATE USER 'catalog_db_user'@'%' IDENTIFIED BY 'db_user';
GRANT ALL ON `catalog`.* TO 'catalog_db_user'@'%';