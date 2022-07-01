CREATE TABLE `resposta_comentarios` (
  `id_resposta_comentario` int NOT NULL AUTO_INCREMENT,
  `nickname` int NOT NULL,
  `id_comentario` int NOT NULL,
  `comentario` varchar(500) NOT NULL,
  PRIMARY KEY (`id_resposta_comentario`),
  KEY `nickname_idx` (`nickname`),
  CONSTRAINT `nickname_resposta_comentario` FOREIGN KEY (`nickname`) REFERENCES `dados_usuario` (`nickname`),
  KEY `id_comentario_idx` (`id_comentario`),
  CONSTRAINT `comentario` FOREIGN KEY (`id_comentario`) REFERENCES `comentarios` (`id_comentario`)
)