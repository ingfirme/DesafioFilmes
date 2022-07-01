CREATE TABLE `comentarios` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(180) NOT NULL,
  `nickname` int NOT NULL,
  `comentario` varchar(500) NOT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `nickname_idx` (`nickname`),
  CONSTRAINT `nickname_comentario` FOREIGN KEY (`nickname`) REFERENCES `dados_usuario` (`nickname`)
)

