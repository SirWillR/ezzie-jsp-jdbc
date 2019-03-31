CREATE TABLE IF NOT EXISTS TiposDeItens (
	ID INT NOT NULL,
    Nome VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS Estados (
	ID INT NOT NULL,
    Nome VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS Cidades (
	ID INT NOT NULL,
    Nome VARCHAR(45) NOT NULL,
    UFID VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (UFID) REFERENCES Estados(ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ItensPerdidos (
	ID INT NOT NULL AUTO_INCREMENT,
	Nome VARCHAR(45) NOT NULL,
	TipoID INT NOT NULL,
	UFID VARCHAR(45) NULL,
	CidadeID VARCHAR(45) NULL,
	PRIMARY KEY (ID),
    FOREIGN KEY (TipoID) REFERENCES TiposDeItens(ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (UFID) REFERENCES Estados(ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CidadeID) REFERENCES Cidades(ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);    