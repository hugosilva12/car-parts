Insert into user (user_id,is_enabled,password,username,user_account_id)  values('f7ed0ed9-8b90-4db4-8359-3d9f5e86b2ae', 1, '$2a$10$9.EtAIPH4Clt./rochxgCOpyrPozT09NHKWzH/ZuhZT7309jOuhWi', 'root', null);

-- Features
Insert into feature values('0184c95f-5176-4303-b76e-bac7496e3bca', 'Client', 'CLIENT');
Insert into feature values('0184f853-c016-4679-a360-ab503466e1d7', 'Administrador', 'ADMIN');
Insert into feature values('0184f853-c014-4f3c-933c-457d469bf73d', 'Funcionário', 'WORKER');

-- User- Features
Insert into user_features values('f7ed0ed9-8b90-4db4-8359-3d9f5e86b2ae','0184c95f-5176-4303-b76e-bac7496e3bca');
Insert into user_features values('f7ed0ed9-8b90-4db4-8359-3d9f5e86b2ae','0184f853-c016-4679-a360-ab503466e1d7');
Insert into user_features values('f7ed0ed9-8b90-4db4-8359-3d9f5e86b2ae','0184f853-c014-4f3c-933c-457d469bf73d');


-- Auth Users
Insert into path values('d6c4ff66-e47b-4908-9c61-4a6a7c4003ca','PUT','/api/auth/users');
Insert into path values('d6c4ff66-e47b-4908-9c61-4a6a7c4003cd','DELETE','/api/auth/users');
Insert into path values('0184862f-45bf-47fa-b6f3-185b6cdc8b92','POST','/api/auth/users');
Insert into path values('01848630-647c-44a1-ba4f-e56cb3918ba6','GET','/api/auth/users');
Insert into path values('87170cab-bf36-4464-bb2c-737ca4476af7','POST','/api/users/employees');
Insert into path values('b2cceda9-1fa7-4202-a6d3-bc8ae0275a07','GET','/api/auth/token/refresh');

-- Paths
Insert into path values('0183c735-6b6a-45a9-a146-0d6196c5522e','PUT','/api/auth/paths');
Insert into path values('0183d790-0a35-49a5-9a06-faa181ab1bdb','DELETE','/api/auth/paths');
Insert into path values('0183f795-5349-47ee-9a34-3fb8d56dc543','POST','/api/auth/paths');
Insert into path values('0183f794-e7cf-48e4-9907-d1ab6c65e5bc','GET','/api/auth/paths');

-- Clients
Insert into path values('0184c95f-5175-4c11-bcae-b50b12b3ad92','GET','/api/users/clients/');
Insert into path values('0184f84e-ae30-4a3e-a756-50a866cde6b8','PUT','/api/users/clients/');
Insert into path values('0180f71e-2eac-4aac-9d0c-bce4940b8023','GET','/api/users/clients');
Insert into path values('018451d5-386b-41e2-ae74-d7ab17cd1dc2','DELETE','/api/users/clients/');
-- Employees
Insert into path values('018451d4-0c23-4630-8865-3ec9cad1968d','DELETE','/api/users/employees/');
Insert into path values('018451cd-d5ce-4311-9206-46a7af8aa88a','GET','/api/users/employees/');
Insert into path values('018451ce-b58a-4ebe-9c8a-d6544736cdff','PUT','/api/users/employees/');
Insert into path values('018451d0-f965-45e3-9baf-3aced14f032a','GET','/api/users/employees');

--Purchases
Insert into path values('0184ed7a-1a5f-444b-be5e-8688af1dacc7','DELETE','/api/purchases/cars');
Insert into path values('0184ed7a-1a60-40dd-8f58-014d20fc6f70','GET','/api/purchases/cars');
Insert into path values('0184ed7a-1a60-4437-9847-798a3d8c8df8','PUT','/api/purchases/cars');
Insert into path values('0184ed7a-1a61-48bb-a510-fe8fe8b5df0d','POST','/api/purchases/cars');

--Desmontagem
Insert into path values('0184f853-c014-4040-b1e8-5b8d6b1d9deb','DELETE','/api/cardisassembly/itemcar');
Insert into path values('0184f853-c014-46d1-a507-4c5efc6e58ca','GET','/api/cardisassembly/itemcar');
Insert into path values('0184f853-c014-4701-b0af-1d25d58d47e3','PUT','/api/cardisassembly/itemcar');
Insert into path values('0184f853-c014-47af-b098-73fdde3149d3','POST','/api/cardisassembly/itemcar');

--Advertisement
Insert into path values('0184f853-c014-4d91-a565-05568cb862d2','DELETE','/api/advertisement/ad');
Insert into path values('0184f853-c016-4219-a097-0adda15ecdf5','GET','/api/advertisement/ad');
Insert into path values('0184f853-c016-4304-9071-b793624ecf92','PUT','/api/advertisement/ad');
Insert into path values('0184f853-c016-4963-9adf-ffbcf573e6c4','POST','/api/advertisement/ad');

--Prices
Insert into path values('018360c1-b744-4493-9b72-ca1566c959ae','DELETE','/api/prices/itemprices');
Insert into path values('0184cfc7-c945-4180-8ed0-8b0ed21c5d3e','GET','/api/prices/itemprices');
Insert into path values('0184edbf-ddd4-47e9-a327-b0bb77332545','PUT','/api/prices/itemprices');
Insert into path values('0184f67b-7199-4eac-abe2-da2add47be5f','POST','/api/prices/itemprices');

--Sales
Insert into path values('50f660d0-9f5c-4909-8882-9127c0f13f14','DELETE','/api/sales/sale');
Insert into path values('adfed7f7-e011-46f4-8279-6c56fbc54804','GET','/api/sales/sale');
Insert into path values('0184f85e-e6f4-4788-a60a-ab7f10d2b0a4','PUT','/api/sales/sale');
Insert into path values('0184f7a9-8927-4ba0-9a5b-6ef980f24657','POST','/api/sales/sale');

--Item Car
Insert into path values('3e8763be-935d-4cc7-9caf-2d73e171baee','DELETE','/api/cardisassembly/item');
Insert into path values('ef5af5f2-e28a-48dc-8dfc-3f1707f2701c','GET','/api/cardisassembly/item');
Insert into path values('85473db8-2b9e-4150-91d3-518d91067680','PUT','/api/cardisassembly/item');
Insert into path values('15f0d474-1968-4da2-b4cb-9f281ae7ffa7','POST','/api/cardisassembly/item');

--Item Categories
Insert into path values('0bf35a8e-14d6-44cb-8f3c-f55576e28370','DELETE','/api/cardisassembly/category');
Insert into path values('1f86412c-4f68-4b34-ba0a-6df154333067','GET','/api/cardisassembly/category');
Insert into path values('5bca70c4-9d78-4443-a4e7-3d92a183cb5f','PUT','/api/cardisassembly/category');
Insert into path values('bf9a36e9-3592-4b72-ac25-9677e23b9f14','POST','/api/cardisassembly/category');

--Storage
Insert into path values('def85afb-d157-4493-ba3e-3c95c823cdd8','DELETE','/api/storage/sections');
Insert into path values('db20eb0d-99df-4e35-bc94-8db96b0efde4','GET','/api/storage/sections');
Insert into path values('a11f831a-9423-4c33-a009-aa6579049144','PUT','/api/storage/sections');
Insert into path values('9984d009-cf83-4b8b-a880-4a8140c6ffb5','POST','/api/storage/sections');

--------------------------------------Features-----------------------------------------------------------------

--------------------------------------------------------ADMIN----------------------------------------------------
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'd6c4ff66-e47b-4908-9c61-4a6a7c4003ca', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'd6c4ff66-e47b-4908-9c61-4a6a7c4003ca' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


-- Clients
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184862f-45bf-47fa-b6f3-185b6cdc8b92', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184862f-45bf-47fa-b6f3-185b6cdc8b92' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '01848630-647c-44a1-ba4f-e56cb3918ba6', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '01848630-647c-44a1-ba4f-e56cb3918ba6' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0180f71e-2eac-4aac-9d0c-bce4940b8023', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0180f71e-2eac-4aac-9d0c-bce4940b8023' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451d5-386b-41e2-ae74-d7ab17cd1dc2', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451d5-386b-41e2-ae74-d7ab17cd1dc2' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451d0-f965-45e3-9baf-3aced14f032a', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451d0-f965-45e3-9baf-3aced14f032a' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451d4-0c23-4630-8865-3ec9cad1968d', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451d4-0c23-4630-8865-3ec9cad1968d' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451ce-b58a-4ebe-9c8a-d6544736cdff', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451ce-b58a-4ebe-9c8a-d6544736cdff' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451cd-d5ce-4311-9206-46a7af8aa88a', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451cd-d5ce-4311-9206-46a7af8aa88a' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '87170cab-bf36-4464-bb2c-737ca4476af7', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '87170cab-bf36-4464-bb2c-737ca4476af7' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


-- PATH
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0183c735-6b6a-45a9-a146-0d6196c5522e', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0183c735-6b6a-45a9-a146-0d6196c5522e' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0183d790-0a35-49a5-9a06-faa181ab1bdb', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0183d790-0a35-49a5-9a06-faa181ab1bdb' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0183f795-5349-47ee-9a34-3fb8d56dc543', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0183f795-5349-47ee-9a34-3fb8d56dc543' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0183f794-e7cf-48e4-9907-d1ab6c65e5bc', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0183f794-e7cf-48e4-9907-d1ab6c65e5bc' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


-- PURCHASE
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a5f-444b-be5e-8688af1dacc7', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a5f-444b-be5e-8688af1dacc7' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a60-40dd-8f58-014d20fc6f70', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a60-40dd-8f58-014d20fc6f70' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a60-4437-9847-798a3d8c8df8', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a60-4437-9847-798a3d8c8df8' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a61-48bb-a510-fe8fe8b5df0d', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a61-48bb-a510-fe8fe8b5df0d' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


-- DESMONTAGEM
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4040-b1e8-5b8d6b1d9deb', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4040-b1e8-5b8d6b1d9deb' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-46d1-a507-4c5efc6e58ca', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-46d1-a507-4c5efc6e58ca' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4701-b0af-1d25d58d47e3', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4701-b0af-1d25d58d47e3' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-47af-b098-73fdde3149d3', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-47af-b098-73fdde3149d3' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;



-- Advertising
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4d91-a565-05568cb862d2', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4d91-a565-05568cb862d2' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4219-a097-0adda15ecdf5', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4219-a097-0adda15ecdf5' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4304-9071-b793624ecf92', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4304-9071-b793624ecf92' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4963-9adf-ffbcf573e6c4', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4963-9adf-ffbcf573e6c4' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

--Prices
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018360c1-b744-4493-9b72-ca1566c959ae', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018360c1-b744-4493-9b72-ca1566c959ae' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184cfc7-c945-4180-8ed0-8b0ed21c5d3e', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184cfc7-c945-4180-8ed0-8b0ed21c5d3e' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184edbf-ddd4-47e9-a327-b0bb77332545', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184edbf-ddd4-47e9-a327-b0bb77332545' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f67b-7199-4eac-abe2-da2add47be5f', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f67b-7199-4eac-abe2-da2add47be5f' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


--Sales
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '50f660d0-9f5c-4909-8882-9127c0f13f14', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '50f660d0-9f5c-4909-8882-9127c0f13f14' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'adfed7f7-e011-46f4-8279-6c56fbc54804', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'adfed7f7-e011-46f4-8279-6c56fbc54804' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f7a9-8927-4ba0-9a5b-6ef980f24657', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f7a9-8927-4ba0-9a5b-6ef980f24657' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


--Item Car
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '3e8763be-935d-4cc7-9caf-2d73e171baee', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '3e8763be-935d-4cc7-9caf-2d73e171baee' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'ef5af5f2-e28a-48dc-8dfc-3f1707f2701c', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'ef5af5f2-e28a-48dc-8dfc-3f1707f2701c' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '85473db8-2b9e-4150-91d3-518d91067680', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '85473db8-2b9e-4150-91d3-518d91067680' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '15f0d474-1968-4da2-b4cb-9f281ae7ffa7', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '15f0d474-1968-4da2-b4cb-9f281ae7ffa7' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;


--Item Categories
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0bf35a8e-14d6-44cb-8f3c-f55576e28370', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0bf35a8e-14d6-44cb-8f3c-f55576e28370' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '1f86412c-4f68-4b34-ba0a-6df154333067', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '1f86412c-4f68-4b34-ba0a-6df154333067' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '5bca70c4-9d78-4443-a4e7-3d92a183cb5f', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '5bca70c4-9d78-4443-a4e7-3d92a183cb5f' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'bf9a36e9-3592-4b72-ac25-9677e23b9f14', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'bf9a36e9-3592-4b72-ac25-9677e23b9f14' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

--Storage
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'def85afb-d157-4493-ba3e-3c95c823cdd', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'def85afb-d157-4493-ba3e-3c95c823cdd' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'db20eb0d-99df-4e35-bc94-8db96b0efde4', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'db20eb0d-99df-4e35-bc94-8db96b0efde4' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'a11f831a-9423-4c33-a009-aa6579049144', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'a11f831a-9423-4c33-a009-aa6579049144' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '9984d009-cf83-4b8b-a880-4a8140c6ffb5', '0184f853-c016-4679-a360-ab503466e1d7') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '9984d009-cf83-4b8b-a880-4a8140c6ffb5' and feature_id = '0184f853-c016-4679-a360-ab503466e1d7'
) LIMIT 1;



--------------------------------------------------------WORKER----------------------------------------------------
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451ce-b58a-4ebe-9c8a-d6544736cdff', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451ce-b58a-4ebe-9c8a-d6544736cdff' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451cd-d5ce-4311-9206-46a7af8aa88a', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451cd-d5ce-4311-9206-46a7af8aa88a' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451d4-0c23-4630-8865-3ec9cad1968d', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451d4-0c23-4630-8865-3ec9cad1968d' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'd6c4ff66-e47b-4908-9c61-4a6a7c4003cd' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

-- PURCHASE
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a5f-444b-be5e-8688af1dacc7', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a5f-444b-be5e-8688af1dacc7' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a60-40dd-8f58-014d20fc6f70', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a60-40dd-8f58-014d20fc6f70' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a60-4437-9847-798a3d8c8df8', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a60-4437-9847-798a3d8c8df8' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184ed7a-1a61-48bb-a510-fe8fe8b5df0d', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184ed7a-1a61-48bb-a510-fe8fe8b5df0d' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


-- DESMONTAGEM
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4040-b1e8-5b8d6b1d9deb', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4040-b1e8-5b8d6b1d9deb' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-46d1-a507-4c5efc6e58ca', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-46d1-a507-4c5efc6e58ca' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4701-b0af-1d25d58d47e3', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4701-b0af-1d25d58d47e3' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-47af-b098-73fdde3149d3', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-47af-b098-73fdde3149d3' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


-- Anuncio
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c014-4d91-a565-05568cb862d2', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c014-4d91-a565-05568cb862d2' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4219-a097-0adda15ecdf5', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4219-a097-0adda15ecdf5' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4304-9071-b793624ecf92', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4304-9071-b793624ecf92' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4963-9adf-ffbcf573e6c4', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4963-9adf-ffbcf573e6c4' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


--Prices
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018360c1-b744-4493-9b72-ca1566c959ae', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018360c1-b744-4493-9b72-ca1566c959ae' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184cfc7-c945-4180-8ed0-8b0ed21c5d3e', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184cfc7-c945-4180-8ed0-8b0ed21c5d3e' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184edbf-ddd4-47e9-a327-b0bb77332545', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184edbf-ddd4-47e9-a327-b0bb77332545' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f67b-7199-4eac-abe2-da2add47be5f', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f67b-7199-4eac-abe2-da2add47be5f' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;



--Sales
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '50f660d0-9f5c-4909-8882-9127c0f13f14', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '50f660d0-9f5c-4909-8882-9127c0f13f14' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'adfed7f7-e011-46f4-8279-6c56fbc54804', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'adfed7f7-e011-46f4-8279-6c56fbc54804' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f7a9-8927-4ba0-9a5b-6ef980f24657', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f7a9-8927-4ba0-9a5b-6ef980f24657' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


--Item Car
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '3e8763be-935d-4cc7-9caf-2d73e171baee', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '3e8763be-935d-4cc7-9caf-2d73e171baee' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'ef5af5f2-e28a-48dc-8dfc-3f1707f2701c', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'ef5af5f2-e28a-48dc-8dfc-3f1707f2701c' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '85473db8-2b9e-4150-91d3-518d91067680', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '85473db8-2b9e-4150-91d3-518d91067680' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '15f0d474-1968-4da2-b4cb-9f281ae7ffa7', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '15f0d474-1968-4da2-b4cb-9f281ae7ffa7' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


--Item Categories
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0bf35a8e-14d6-44cb-8f3c-f55576e28370', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0bf35a8e-14d6-44cb-8f3c-f55576e28370' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '1f86412c-4f68-4b34-ba0a-6df154333067', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '1f86412c-4f68-4b34-ba0a-6df154333067' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '5bca70c4-9d78-4443-a4e7-3d92a183cb5f', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '5bca70c4-9d78-4443-a4e7-3d92a183cb5f' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'bf9a36e9-3592-4b72-ac25-9677e23b9f14', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'bf9a36e9-3592-4b72-ac25-9677e23b9f14' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;


--Storage
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'def85afb-d157-4493-ba3e-3c95c823cdd', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'def85afb-d157-4493-ba3e-3c95c823cdd' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'db20eb0d-99df-4e35-bc94-8db96b0efde4', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'db20eb0d-99df-4e35-bc94-8db96b0efde4' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'a11f831a-9423-4c33-a009-aa6579049144', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'a11f831a-9423-4c33-a009-aa6579049144' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '9984d009-cf83-4b8b-a880-4a8140c6ffb5', '0184f853-c014-4f3c-933c-457d469bf73d') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '9984d009-cf83-4b8b-a880-4a8140c6ffb5' and feature_id = '0184f853-c014-4f3c-933c-457d469bf73d'
) LIMIT 1;



--------------------------------------------------------CLIENT----------------------------------------------------
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184c95f-5175-4c11-bcae-b50b12b3ad92', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184c95f-5175-4c11-bcae-b50b12b3ad92' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f84e-ae30-4a3e-a756-50a866cde6b8', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f84e-ae30-4a3e-a756-50a866cde6b8' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '018451d5-386b-41e2-ae74-d7ab17cd1dc2', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '018451d5-386b-41e2-ae74-d7ab17cd1dc2' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'b2cceda9-1fa7-4202-a6d3-bc8ae0275a07' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

--Anuncio
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f853-c016-4219-a097-0adda15ecdf5', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f853-c016-4219-a097-0adda15ecdf5' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;


--Sales
INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT 'adfed7f7-e011-46f4-8279-6c56fbc54804', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = 'adfed7f7-e011-46f4-8279-6c56fbc54804' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f85e-e6f4-4788-a60a-ab7f10d2b0a4' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

INSERT INTO path_features (path_id, feature_id)
SELECT * FROM (SELECT '0184f7a9-8927-4ba0-9a5b-6ef980f24657', '0184c95f-5176-4303-b76e-bac7496e3bca') AS tmp
WHERE NOT EXISTS (
    SELECT path_id,feature_id FROM path_features WHERE path_id = '0184f7a9-8927-4ba0-9a5b-6ef980f24657' and feature_id = '0184c95f-5176-4303-b76e-bac7496e3bca'
) LIMIT 1;

