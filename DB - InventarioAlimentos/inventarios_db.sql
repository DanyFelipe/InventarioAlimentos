-- MySQL Script generated by MySQL Workbench
-- Mon Sep 25 07:41:39 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema inventarios
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema inventarios
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `inventarios` DEFAULT CHARACTER SET utf8 ;
USE `inventarios` ;

-- -----------------------------------------------------
-- Table `inventarios`.`Sucursales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventarios`.`Sucursales` (
  `idSucursal` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(40) NULL,
  PRIMARY KEY (`idSucursal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventarios`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventarios`.`Productos` (
  `idProducto` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(80) NULL,
  `precio` DOUBLE NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`idProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventarios`.`Sucursal_has_Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventarios`.`Sucursal_has_Producto` (
  `Sucursal_idSucursal` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  PRIMARY KEY (`Sucursal_idSucursal`, `Producto_idProducto`),
  INDEX `fk_Sucursal_has_Producto_Producto1_idx` (`Producto_idProducto` ASC) VISIBLE,
  INDEX `fk_Sucursal_has_Producto_Sucursal_idx` (`Sucursal_idSucursal` ASC) VISIBLE,
  CONSTRAINT `fk_Sucursal_has_Producto_Sucursal`
    FOREIGN KEY (`Sucursal_idSucursal`)
    REFERENCES `inventarios`.`Sucursales` (`idSucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sucursal_has_Producto_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `inventarios`.`Productos` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- Insertar productos
INSERT INTO Productos (idProducto ,nombre, descripcion, precio, cantidad)
VALUES (117,'Laptop HP', 'Laptop HP de 15 pulgadas', 800.00, 10);

INSERT INTO Productos (idProducto, nombre, descripcion, precio, cantidad)
VALUES (421,'Impresora Epson', 'Impresora de inyección de tinta', 120.00, 5);

INSERT INTO Productos (idProducto, nombre, descripcion, precio, cantidad)
VALUES (255,'Monitor LG', 'Monitor LED de 24 pulgadas', 200.00, 8);

INSERT INTO Productos (idProducto, nombre, descripcion, precio, cantidad)
VALUES (632,'Teclado Logitech', 'Teclado inalámbrico', 40.00, 15);

-- Insertar sucursales
INSERT INTO Sucursales (idSucursal, nombre, direccion, telefono)
VALUES (245,'Sucursal A', 'Calle Principal 123', '123-456-7890');

INSERT INTO Sucursales (idSucursal, nombre, direccion, telefono)
VALUES (763,'Sucursal B', 'Avenida Secundaria 456', '987-654-3210');

INSERT INTO Sucursales (idSucursal, nombre, direccion, telefono)
VALUES (234,'Sucursal C', 'Almacén Central 789', '555-555-5555');

-- Relaciones
INSERT INTO Sucursal_has_Producto (Sucursal_idSucursal, Producto_idProducto)
VALUES (234, 117);

INSERT INTO Sucursal_has_Producto (Sucursal_idSucursal, Producto_idProducto)
VALUES (245, 421);

INSERT INTO Sucursal_has_Producto (Sucursal_idSucursal, Producto_idProducto)
VALUES (245, 255);

INSERT INTO Sucursal_has_Producto (Sucursal_idSucursal, Producto_idProducto)
VALUES (763, 632);

-- Consultas
select * from Sucursal_has_Producto;
select * from productos;
select * from sucursales;
