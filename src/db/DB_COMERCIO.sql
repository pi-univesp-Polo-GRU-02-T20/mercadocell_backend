
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------------------------------------------------------
-- Table bairro
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `bairro` (
  `COD_BAIRRO` INT NOT NULL AUTO_INCREMENT,
  `NME_BAIRRO` VARCHAR(100) NOT NULL,
  `COD_MUNICIPIO` INT NULL DEFAULT NULL,
  PRIMARY KEY (`COD_BAIRRO`),
  INDEX `FK_BAIRRO_MUNICIPIO_IDX` (`COD_MUNICIPIO` ASC) VISIBLE,
  INDEX `IDX_BAIRRO` (`NME_BAIRRO` ASC) VISIBLE,
  CONSTRAINT `FK_BAIRRO_MUNICIPIO`
    FOREIGN KEY (`COD_MUNICIPIO`)
    REFERENCES `municipio` (`COD_MUNICIPIO`))
ENGINE = InnoDB
AUTO_INCREMENT = 34518
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table categoria
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `categoria` (
  `COD_CATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NME_CATEGORIA` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`COD_CATEGORIA`))
ENGINE = InnoDB
AUTO_INCREMENT = 277
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table endereco
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `endereco` (
  `COD_ENDERECO` INT NOT NULL,
  `DSC_COMPLEMENTO` VARCHAR(255) NULL DEFAULT NULL,
  `NRO_ENDERECO` INT NULL DEFAULT NULL,
  `DSC_PONTO_REFERENCIA` VARCHAR(255) NULL DEFAULT NULL,
  `COD_CEP` INT NULL DEFAULT NULL,
  `COD_PESSOA` INT NULL DEFAULT NULL,
  PRIMARY KEY (`COD_ENDERECO`),
  INDEX `FK_ENDERECO_LOGRADOURO_idx` (`COD_CEP` ASC) VISIBLE,
  INDEX `FK_ENDERECO_PESSOA_idx` (`COD_PESSOA` ASC) VISIBLE,
  CONSTRAINT `FK_ENDERECO_LOGRADOURO`
    FOREIGN KEY (`COD_CEP`)
    REFERENCES `logradouro` (`COD_CEP`),
  CONSTRAINT `FK_ENDERECO_PESSOA`
    FOREIGN KEY (`COD_PESSOA`)
    REFERENCES `pessoa` (`COD_PESSOA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table estado
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado` (
  `COD_ESTADO` INT NOT NULL AUTO_INCREMENT,
  `NME_ESTADO` VARCHAR(100) NOT NULL,
  `SGL_UF` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`COD_ESTADO`))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table imagem
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `imagem` (
  `COD_IMAGEM` INT NOT NULL AUTO_INCREMENT,
  `NME_IMAGEM` VARCHAR(255) NULL DEFAULT NULL,
  `BNR_IMAGEM` LONGBLOB NULL DEFAULT NULL,
  `TPO_IMAGEM` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_IMAGEM`))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table imagem_produto
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `imagem_produto` (
  `COD_PRODUTO` INT NOT NULL,
  `COD_IMAGEM` INT NOT NULL,
  PRIMARY KEY (`COD_PRODUTO`, `COD_IMAGEM`),
  INDEX `FK_IMAGEM_PRODUTO_IMAGEM` (`COD_IMAGEM` ASC) VISIBLE,
  CONSTRAINT `FK_IMAGEM_PRODUTO_IMAGEM`
    FOREIGN KEY (`COD_IMAGEM`)
    REFERENCES `imagem` (`COD_IMAGEM`),
  CONSTRAINT `FK_IMAGEM_PRODUTO_PRODUTO`
    FOREIGN KEY (`COD_PRODUTO`)
    REFERENCES `produto` (`COD_PRODUTO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table item_operacao
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `item_operacao` (
  `COD_ITEM_OPERACAO` INT NOT NULL AUTO_INCREMENT,
  `QTD_ITEM` FLOAT NULL DEFAULT NULL,
  `VLR_ITEM` DECIMAL(10,2) NULL DEFAULT NULL,
  `COD_OPERACAO` INT NOT NULL,
  `COD_PRODUTO` INT NOT NULL,
  PRIMARY KEY (`COD_ITEM_OPERACAO`),
  INDEX `FK_ITEM_COMPRA_IDX` (`COD_OPERACAO` ASC) VISIBLE,
  INDEX `FK_ITEM_COMPRA_PRODUTO_IDX` (`COD_PRODUTO` ASC) VISIBLE,
  CONSTRAINT `FK_ITEM_COMPRA`
    FOREIGN KEY (`COD_OPERACAO`)
    REFERENCES `operacao` (`COD_OPERACAO`),
  CONSTRAINT `FK_ITEM_COMPRA_PRODUTO`
    FOREIGN KEY (`COD_PRODUTO`)
    REFERENCES `produto` (`COD_PRODUTO`))
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table logradouro
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `logradouro` (
  `COD_CEP` INT NOT NULL AUTO_INCREMENT,
  `COD_BAIRRO` INT NOT NULL,
  `DSC_LOGRADOURO` VARCHAR(255) NULL DEFAULT NULL,
  `DSC_COMPLEMENTO` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_CEP`, `COD_BAIRRO`),
  INDEX `FK_BAIRRO_ENDERECO_IDX` (`COD_BAIRRO` ASC) VISIBLE,
  CONSTRAINT `FK_BAIRRO_ENDERECO`
    FOREIGN KEY (`COD_BAIRRO`)
    REFERENCES `bairro` (`COD_BAIRRO`))
ENGINE = InnoDB
AUTO_INCREMENT = 8421142
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table municipio
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `municipio` (
  `COD_MUNICIPIO` INT NOT NULL AUTO_INCREMENT,
  `NME_MUNICIPIO` VARCHAR(100) NOT NULL,
  `COD_ESTADO` INT NOT NULL,
  PRIMARY KEY (`COD_MUNICIPIO`),
  INDEX `FK_MUNICIPIO_ESTADO_IDX` (`COD_ESTADO` ASC) VISIBLE,
  CONSTRAINT `FK_MUNICIPIO_ESTADO`
    FOREIGN KEY (`COD_ESTADO`)
    REFERENCES `estado` (`COD_ESTADO`))
ENGINE = InnoDB
AUTO_INCREMENT = 3557304
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table operacao
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `operacao` (
  `COD_OPERACAO` INT NOT NULL AUTO_INCREMENT,
  `DTA_OPERACAO` DATETIME NULL DEFAULT NULL,
  `COD_NOTA_FISCAL` VARCHAR(30) NULL DEFAULT NULL,
  `VLR_TOTAL` DECIMAL(10,2) NULL DEFAULT NULL,
  `QTD_PARCELA` INT NULL DEFAULT NULL,
  `TPO_STATUS` ENUM('P', 'O') NULL DEFAULT NULL COMMENT 'P - pedido (compra ou venda); O - orçamento\\\\n',
  `COD_PESSOA` INT NULL DEFAULT NULL,
  `FLG_PAGO` TINYINT NULL DEFAULT NULL,
  `TPO_OPERACAO` ENUM('C', 'V') NULL DEFAULT NULL COMMENT 'C - Compra; V - Venda',
  `COD_TIPO_PAGAMENTO` INT NULL DEFAULT NULL,
  PRIMARY KEY (`COD_OPERACAO`),
  INDEX `FK_OPERACAO_PESSOA_idx` (`COD_PESSOA` ASC) VISIBLE,
  INDEX `FK_OPERACAO_TIPO_PAGAMENTO` (`COD_TIPO_PAGAMENTO` ASC) VISIBLE,
  CONSTRAINT `FK_OPERACAO_PESSOA`
    FOREIGN KEY (`COD_PESSOA`)
    REFERENCES `pessoa` (`COD_PESSOA`),
  CONSTRAINT `FK_OPERACAO_TIPO_PAGAMENTO`
    FOREIGN KEY (`COD_TIPO_PAGAMENTO`)
    REFERENCES `tipo_pagamento` (`COD_TIPO_PAGAMENTO`))
ENGINE = InnoDB
AUTO_INCREMENT = 201
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table pagamento_operacao
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pagamento_operacao` (
  `COD_PAGAMENTO` INT NOT NULL AUTO_INCREMENT,
  `VLR_PAGAMENTO` DECIMAL(10,2) NULL DEFAULT NULL,
  `DTA_PAGAMENTO` DATE NULL DEFAULT NULL,
  `DTA_VENCIMENTO` DATE NULL DEFAULT NULL,
  `COD_OPERACAO` INT NOT NULL,
  PRIMARY KEY (`COD_PAGAMENTO`),
  INDEX `FK_PARCELA_COMPRA_IDX` (`COD_OPERACAO` ASC) VISIBLE,
  CONSTRAINT `FK_PARCELA_COMPRA`
    FOREIGN KEY (`COD_OPERACAO`)
    REFERENCES `operacao` (`COD_OPERACAO`))
ENGINE = InnoDB
AUTO_INCREMENT = 95
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table pessoa
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa` (
  `COD_PESSOA` INT NOT NULL AUTO_INCREMENT,
  `NME_PESSOA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`COD_PESSOA`))
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table pessoa_fisica
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa_fisica` (
  `COD_PESSOA` INT NOT NULL AUTO_INCREMENT,
  `SGL_UF_NATURALIDADE` CHAR(2) NULL DEFAULT NULL,
  `DTA_NASCIMENTO` DATE NOT NULL,
  `TPO_SEXO` CHAR(1) NULL DEFAULT NULL COMMENT 'M - MASCULINO; F - FEMININO; N - NEUTRO',
  PRIMARY KEY (`COD_PESSOA`),
  INDEX `FK_PESSOA_FISICA_PESSOA_IDX` (`COD_PESSOA` ASC) VISIBLE,
  CONSTRAINT `FK_PESSOA_FISICA`
    FOREIGN KEY (`COD_PESSOA`)
    REFERENCES `pessoa` (`COD_PESSOA`))
ENGINE = InnoDB
AUTO_INCREMENT = 336
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table pessoa_juridica
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa_juridica` (
  `COD_PESSOA` INT NOT NULL AUTO_INCREMENT,
  `NME_RAZAO_SOCIAL` VARCHAR(200) NOT NULL,
  `COD_CNPJ` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_PESSOA`),
  INDEX `FK_PESSOA_JURIDICA_PESSOA_IDX` (`COD_PESSOA` ASC) VISIBLE,
  CONSTRAINT `FK_PESSOA_JURIDICA`
    FOREIGN KEY (`COD_PESSOA`)
    REFERENCES `pessoa` (`COD_PESSOA`))
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table produto
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto` (
  `COD_PRODUTO` INT NOT NULL AUTO_INCREMENT,
  `NME_PRODUTO` VARCHAR(95) NULL DEFAULT NULL,
  `DSC_PRODUTO` VARCHAR(255) NULL DEFAULT NULL,
  `COD_SUBCATEGORIA` INT NULL DEFAULT NULL,
  `COD_UNIDADE_MEDIDA` INT NULL DEFAULT NULL,
  `QTD_ESTOQUE_MIN` INT NULL DEFAULT NULL,
  `QTD_ESTOQUE_MAX` INT NULL DEFAULT NULL,
  `QTD_ESTOQUE_ATUAL` INT NULL DEFAULT NULL,
  PRIMARY KEY (`COD_PRODUTO`),
  INDEX `FK_PRODUTO_SUBCATEGORIA_IDX` (`COD_SUBCATEGORIA` ASC) VISIBLE,
  INDEX `FK_PRODUTO_UNIDADE_MEDIDA_IDX` (`COD_UNIDADE_MEDIDA` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUTO_SUBCATEGORIA`
    FOREIGN KEY (`COD_SUBCATEGORIA`)
    REFERENCES `subcategoria` (`COD_SUBCATEGORIA`),
  CONSTRAINT `FK_PRODUTO_UNIDADE_MEDIDA`
    FOREIGN KEY (`COD_UNIDADE_MEDIDA`)
    REFERENCES `unidade_medida` (`COD_UNIDADE_MEDIDA`))
ENGINE = InnoDB
AUTO_INCREMENT = 216546665
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table subcategoria
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `subcategoria` (
  `COD_SUBCATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NME_SUBCATEGORIA` VARCHAR(95) NULL DEFAULT NULL,
  `COD_CATEGORIA` INT NULL DEFAULT NULL,
  PRIMARY KEY (`COD_SUBCATEGORIA`),
  INDEX `FK_SUBCATEGORIA_CATEGORA_IDX` (`COD_CATEGORIA` ASC) VISIBLE,
  CONSTRAINT `FK_SUBCATEGORIA_CATEGORA`
    FOREIGN KEY (`COD_CATEGORIA`)
    REFERENCES `categoria` (`COD_CATEGORIA`))
ENGINE = InnoDB
AUTO_INCREMENT = 243
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table tipo_pagamento
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `tipo_pagamento` (
  `COD_TIPO_PAGAMENTO` INT NOT NULL AUTO_INCREMENT,
  `NME_TIPO_PAGAMENTO` VARCHAR(90) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_TIPO_PAGAMENTO`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table unidade_medida
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `unidade_medida` (
  `COD_UNIDADE_MEDIDA` INT NOT NULL AUTO_INCREMENT,
  `NME_UNIDADE_MEDIDA` VARCHAR(100) NULL DEFAULT NULL,
  `SGL_UNIDADE_MEDIDA` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_UNIDADE_MEDIDA`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table usuario
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `COD_USUARIO` INT NOT NULL AUTO_INCREMENT,
  `DSC_LOGIN` VARCHAR(20) NULL DEFAULT NULL,
  `DSC_SENHA` VARCHAR(128) NULL DEFAULT NULL,
  `FLG_ATIVO` TINYINT NULL DEFAULT NULL,
  `COD_PESSOA` INT NULL DEFAULT NULL,
  `DSC_COMPLEMENTO_SENHA` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`COD_USUARIO`),
  UNIQUE INDEX `DSC_LOGIN_UNIQUE` (`DSC_LOGIN` ASC) VISIBLE,
  INDEX `FK_USUARIO_PESSOA` (`COD_PESSOA` ASC) VISIBLE,
  CONSTRAINT `FK_USUARIO_PESSOA`
    FOREIGN KEY (`COD_PESSOA`)
    REFERENCES `pessoa` (`COD_PESSOA`))
ENGINE = InnoDB
AUTO_INCREMENT = 126
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- View vw_apuracao_faturamento_detalhado
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_apuracao_faturamento_detalhado` AS select `pr`.`COD_PRODUTO` AS `COD_PRODUTO`,`pr`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`itop`.`QTD_ITEM`),2) AS `QTD_ITEM`,round(avg(`itop`.`VLR_ITEM`),2) AS `VLR_PRECO_MEDIO`,`op`.`DTA_OPERACAO` AS `DSC_PERIODO`,`op`.`TPO_OPERACAO` AS `TPO_OPERACAO`,`pr`.`QTD_ESTOQUE_MIN` AS `QTD_ESTOQUE_MIN`,`pr`.`QTD_ESTOQUE_MAX` AS `QTD_ESTOQUE_MAX` from ((`operacao` `op` join `item_operacao` `itop` on((`op`.`COD_OPERACAO` = `itop`.`COD_OPERACAO`))) join `produto` `pr` on((`pr`.`COD_PRODUTO` = `itop`.`COD_PRODUTO`))) group by `pr`.`COD_PRODUTO`,`pr`.`NME_PRODUTO`,`op`.`DTA_OPERACAO`,`op`.`TPO_OPERACAO`;

-- ----------------------------------------------------------------------------
-- View vw_apuracao_faturamento_sumarizado
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_apuracao_faturamento_sumarizado` AS select `a`.`COD_PRODUTO` AS `COD_PRODUTO`,`a`.`NME_PRODUTO` AS `NME_PRODUTO`,round((`b`.`QTD_ITEM` * `a`.`VLR_PRECO_MEDIO`),2) AS `VLR_CUSTO_VENDA`,round((`b`.`QTD_ITEM` * `b`.`VLR_PRECO_MEDIO`),2) AS `VLR_FATURADO`,round(((`b`.`QTD_ITEM` * `b`.`VLR_PRECO_MEDIO`) - (`b`.`QTD_ITEM` * `a`.`VLR_PRECO_MEDIO`)),2) AS `VLR_LIQUIDO`,`a`.`QTD_ITEM` AS `QTD_ITEM_ESTOQUE_ENTRADA`,`b`.`QTD_ITEM` AS `QTD_ITEM_ESTOQUE_SAIDA`,round(((`b`.`QTD_ITEM` / `a`.`QTD_ITEM`) * 100),2) AS `VLR_PCT_ESTOQUE_UTILIZADO`,`b`.`DSC_PERIODO` AS `DSC_PERIODO` from ((select `vafd`.`QTD_ITEM` AS `QTD_ITEM`,`vafd`.`VLR_PRECO_MEDIO` AS `VLR_PRECO_MEDIO`,`vafd`.`COD_PRODUTO` AS `COD_PRODUTO`,`vafd`.`NME_PRODUTO` AS `NME_PRODUTO`,cast(`vafd`.`DSC_PERIODO` as date) AS `DSC_PERIODO` from `vw_apuracao_faturamento_detalhado` `vafd` where (`vafd`.`TPO_OPERACAO` = 'C')) `a` join (select `vafd`.`QTD_ITEM` AS `QTD_ITEM`,`vafd`.`VLR_PRECO_MEDIO` AS `VLR_PRECO_MEDIO`,`vafd`.`COD_PRODUTO` AS `COD_PRODUTO`,cast(`vafd`.`DSC_PERIODO` as date) AS `DSC_PERIODO`,`vafd`.`QTD_ESTOQUE_MIN` AS `QTD_ESTOQUE_MIN`,`vafd`.`QTD_ESTOQUE_MAX` AS `QTD_ESTOQUE_MAX` from `vw_apuracao_faturamento_detalhado` `vafd` where (`vafd`.`TPO_OPERACAO` = 'V')) `b` on((`a`.`COD_PRODUTO` = `b`.`COD_PRODUTO`)));

-- ----------------------------------------------------------------------------
-- View vw_faturamento_detalhado_anual
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_detalhado_anual` AS select `vapd`.`COD_PRODUTO` AS `COD_PRODUTO`,`vapd`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`vapd`.`QTD_ITEM`),2) AS `QTD_ITEM`,round(sum(`vapd`.`VLR_PRECO_MEDIO`),2) AS `VLR_PRECO_MEDIO`,date_format(`vapd`.`DSC_PERIODO`,'%Y') AS `DSC_PERIODO` from `vw_apuracao_faturamento_detalhado` `vapd` where (`vapd`.`TPO_OPERACAO` = 'V') group by `vapd`.`COD_PRODUTO`,`vapd`.`NME_PRODUTO`,date_format(`vapd`.`DSC_PERIODO`,'%Y');

-- ----------------------------------------------------------------------------
-- View vw_faturamento_detalhado_diario
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_detalhado_diario` AS select `vapd`.`COD_PRODUTO` AS `COD_PRODUTO`,`vapd`.`NME_PRODUTO` AS `NME_PRODUTO`,sum(`vapd`.`QTD_ITEM`) AS `QTD_ITEM`,sum(`vapd`.`VLR_PRECO_MEDIO`) AS `VLR_PRECO_MEDIO`,date_format(`vapd`.`DSC_PERIODO`,'%Y-%m-%d') AS `DSC_PERIODO` from `vw_apuracao_faturamento_detalhado` `vapd` where (`vapd`.`TPO_OPERACAO` = 'V') group by `vapd`.`COD_PRODUTO`,`vapd`.`NME_PRODUTO`,date_format(`vapd`.`DSC_PERIODO`,'%Y-%m-%d');

-- ----------------------------------------------------------------------------
-- View vw_faturamento_detalhado_mensal
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_detalhado_mensal` AS select `vapd`.`COD_PRODUTO` AS `COD_PRODUTO`,`vapd`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`vapd`.`QTD_ITEM`),2) AS `QTD_ITEM`,round(sum(`vapd`.`VLR_PRECO_MEDIO`),2) AS `VLR_PRECO_MEDIO`,date_format(`vapd`.`DSC_PERIODO`,'%Y-%m') AS `DSC_PERIODO` from `vw_apuracao_faturamento_detalhado` `vapd` where (`vapd`.`TPO_OPERACAO` = 'V') group by `vapd`.`COD_PRODUTO`,`vapd`.`NME_PRODUTO`,date_format(`vapd`.`DSC_PERIODO`,'%Y-%m');

-- ----------------------------------------------------------------------------
-- View vw_faturamento_sumarizado_anual
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_sumarizado_anual` AS select `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` AS `COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_CUSTO_VENDA`),2) AS `VLR_CUSTO_VENDA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_FATURADO`),2) AS `VLR_FATURADO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_LIQUIDO`),2) AS `VLR_LIQUIDO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_ENTRADA`),2) AS `QTD_ITEM_ESTOQUE_ENTRADA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_SAIDA`),2) AS `QTD_ITEM_ESTOQUE_SAIDA`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y') AS `DSC_PERIODO` from `vw_apuracao_faturamento_sumarizado` where (`vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` = `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`) group by `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y');

-- ----------------------------------------------------------------------------
-- View vw_faturamento_sumarizado_diario
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_sumarizado_diario` AS select `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` AS `COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_CUSTO_VENDA`),2) AS `VLR_CUSTO_VENDA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_FATURADO`),2) AS `VLR_FATURADO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_LIQUIDO`),2) AS `VLR_LIQUIDO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_ENTRADA`),2) AS `QTD_ITEM_ESTOQUE_ENTRADA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_SAIDA`),2) AS `QTD_ITEM_ESTOQUE_SAIDA`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y-%m-%d') AS `DSC_PERIODO` from `vw_apuracao_faturamento_sumarizado` where (`vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` = `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`) group by `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y-%m-%d');

-- ----------------------------------------------------------------------------
-- View vw_faturamento_sumarizado_mensal
-- ----------------------------------------------------------------------------

CREATE   VIEW `vw_faturamento_sumarizado_mensal` AS select `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` AS `COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO` AS `NME_PRODUTO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_CUSTO_VENDA`),2) AS `VLR_CUSTO_VENDA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_FATURADO`),2) AS `VLR_FATURADO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`VLR_LIQUIDO`),2) AS `VLR_LIQUIDO`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_ENTRADA`),2) AS `QTD_ITEM_ESTOQUE_ENTRADA`,round(sum(`vw_apuracao_faturamento_sumarizado`.`QTD_ITEM_ESTOQUE_SAIDA`),2) AS `QTD_ITEM_ESTOQUE_SAIDA`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y-%m') AS `DSC_PERIODO` from `vw_apuracao_faturamento_sumarizado` where (`vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO` = `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`) group by `vw_apuracao_faturamento_sumarizado`.`COD_PRODUTO`,`vw_apuracao_faturamento_sumarizado`.`NME_PRODUTO`,date_format(`vw_apuracao_faturamento_sumarizado`.`DSC_PERIODO`,'%Y-%m');

-- ----------------------------------------------------------------------------
-- Trigger TRG_ATUALIZAR_ESTOQUE
-- ----------------------------------------------------------------------------
DELIMITER $$

CREATE  TRIGGER `TRG_ATUALIZAR_ESTOQUE` AFTER INSERT ON `item_operacao` FOR EACH ROW BEGIN
    SELECT TPO_OPERACAO INTO @TPO_OPERACAO 
		FROM OPERACAO OP WHERE OP.COD_OPERACAO = NEW.COD_OPERACAO;
       
	IF @TPO_OPERACAO  = 'C' THEN
		UPDATE 	ITEM_OPERACAO ITOP
		INNER JOIN PRODUTO PR 
		ON 	PR.COD_PRODUTO 			= NEW.COD_PRODUTO 		AND 
            ITOP.COD_ITEM_OPERACAO 	= NEW.COD_ITEM_OPERACAO
		SET PR.QTD_ESTOQUE_ATUAL = PR.QTD_ESTOQUE_ATUAL + ITOP.QTD_ITEM;
    ELSEIF @TPO_OPERACAO  = 'V' THEN
		UPDATE ITEM_OPERACAO ITOP
		INNER JOIN PRODUTO PR 
		ON 	PR.COD_PRODUTO 			= NEW.COD_PRODUTO		AND 
			ITOP.COD_ITEM_OPERACAO 	= NEW.COD_ITEM_OPERACAO
		SET PR.QTD_ESTOQUE_ATUAL = PR.QTD_ESTOQUE_ATUAL - ITOP.QTD_ITEM;
	END IF;
END;
SET FOREIGN_KEY_CHECKS = 1;
