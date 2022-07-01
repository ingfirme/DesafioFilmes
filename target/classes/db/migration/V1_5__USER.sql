CREATE TABLE `citar_comentario` (
  `id_citar_comentario` INT NOT NULL AUTO_INCREMENT,
  `id_comentario` INT NOT NULL,
  `comentario` varchar(180) NOT NULL,
  PRIMARY KEY (`id_citar_comentario`),
  INDEX `citar_comentario_idx` (`id_comentario` ASC) VISIBLE,
  CONSTRAINT `citar_comentario`
    FOREIGN KEY (`id_comentario`)
    REFERENCES `comentarios` (`id_comentario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
