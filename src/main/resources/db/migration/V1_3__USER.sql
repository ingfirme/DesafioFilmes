CREATE TABLE `reacoes` (
  `id_reacao` int NOT NULL AUTO_INCREMENT,
  `id_comentario` int NOT NULL,
  `nickname` int NOT NULL,
  `reacao` bool NOT NULL,
  PRIMARY KEY (`id_reacao`),
  KEY `nickname_idx` (`nickname`),
  CONSTRAINT `nickname_reacao` FOREIGN KEY (`nickname`) REFERENCES `dados_usuario` (`nickname`),
  KEY `id_comentario_idx` (`id_comentario`),
  CONSTRAINT `comentario_reacao` FOREIGN KEY (`id_comentario`) REFERENCES `comentarios` (`id_comentario`)
)