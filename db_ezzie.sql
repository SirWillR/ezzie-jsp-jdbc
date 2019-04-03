CREATE TABLE IF NOT EXISTS Login (
  ID INT NOT NULL AUTO_INCREMENT,
  Usuario VARCHAR(10) NOT NULL,
  Senha VARCHAR(10) NOT NULL,
  Nome VARCHAR(20) NOT NULL,
  Email VARCHAR(20) NOT NULL,
  Telefone VARCHAR(20) NOT NULL,
  RedeSocial VARCHAR(60) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Pais (
  ID INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(60) NOT NULL,
  Sigla VARCHAR(10) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2;

CREATE TABLE IF NOT EXISTS Estado (
  ID INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(75) NOT NULL,
  UF VARCHAR(5) NOT NULL,
  PaisID INT(7) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (PaisID) REFERENCES Pais(ID)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

CREATE TABLE IF NOT EXISTS Cidade (
	ID INT NOT NULL AUTO_INCREMENT,
    Nome VARCHAR(45) NOT NULL,
    EstadoID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (EstadoID) REFERENCES Estado(ID)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5565 ;

CREATE TABLE IF NOT EXISTS TipoDeItem (
	ID INT NOT NULL AUTO_INCREMENT,
    Nome VARCHAR(60) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10;

CREATE TABLE IF NOT EXISTS ItemPerdido (
	ID INT NOT NULL AUTO_INCREMENT,
	UsuarioID INT NOT NULL,
	Nome VARCHAR(45) NOT NULL,
	TipoID INT NOT NULL,
	CidadeID INT NOT NULL,
	Data DATE NOT NULL,
	LocalEncontrado VARCHAR(60) NOT NULL,
	Prazo INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (UsuarioID) REFERENCES Login(ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (TipoID) REFERENCES TipoDeItem(ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CidadeID) REFERENCES Cidade(ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);