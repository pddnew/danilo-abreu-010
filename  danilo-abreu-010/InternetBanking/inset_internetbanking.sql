INSERT INTO `internetbanking`.`client`
(
`CPF/CPNPJ`,
`FIST_NAME`,
`LAST_NAME`)
VALUES
(
'25233567750',
'Joseph',
'Bach'
);
 
INSERT INTO `internetbanking`.`account_type`
(
`CODE`,
`DESCRIPTION`)
VALUES
(
'100',
'Corrente'
);

INSERT INTO `internetbanking`.`account_type`
(
`CODE`,
`DESCRIPTION`)
VALUES
(
'500',
'Poupança'
);

INSERT INTO `internetbanking`.`account`
(
`idCLIENT`,
`idACCOUNT_TYPE`,
`NUMBER`,
`PASSWORD`)
VALUES
(

1,
1,
'1234567890',
'123456'
);
