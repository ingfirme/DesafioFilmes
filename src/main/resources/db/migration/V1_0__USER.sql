CREATE TABLE `perfil` (
  `id_perfil` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id_perfil`));

CREATE TABLE `dados_usuario` (
   `nome` varchar(255) NOT NULL,
   `sobrenome` varchar(255) DEFAULT NULL,
   `nickname` int NOT NULL AUTO_INCREMENT,
   `email` varchar(255) NOT NULL,
   `idade` varchar(3) NOT NULL,
   `senha` varchar(255) NOT NULL,
   `qtde_nota` int DEFAULT NULL,
   `qtde_comentario` int DEFAULT NULL,
   `qtde_resposta` int DEFAULT NULL,
   `qtde_total` int DEFAULT NULL,
   `id_perfil` int NOT NULL,
   PRIMARY KEY (`nickname`),
   KEY `id_perfil_idx` (`id_perfil`),
   CONSTRAINT `id_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`)
 );

INSERT INTO `perfil` (`id_perfil`, `nome`) VALUES ('1', 'Leitor');
INSERT INTO `perfil` (`id_perfil`, `nome`) VALUES ('2', 'Basico');
INSERT INTO `perfil` (`id_perfil`, `nome`) VALUES ('3', 'Avancado');
INSERT INTO `perfil` (`id_perfil`, `nome`) VALUES ('4', 'Moderador');

CREATE TABLE `notas` (
   `id_nota` int NOT NULL AUTO_INCREMENT,
   `titulo` varchar(180) NOT NULL,
   `nickname` int NOT NULL,
   `nota` int NOT NULL,
   PRIMARY KEY (`id_nota`),
   KEY `nickname_idx` (`nickname`),
   CONSTRAINT `nickname` FOREIGN KEY (`nickname`) REFERENCES `dados_usuario` (`nickname`)
 )
