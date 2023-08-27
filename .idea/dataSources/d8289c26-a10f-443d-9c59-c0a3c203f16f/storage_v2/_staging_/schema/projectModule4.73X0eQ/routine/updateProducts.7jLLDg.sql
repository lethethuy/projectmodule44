create
    definer = root@localhost procedure updateProducts(IN newId int, IN newProduct_name varchar(255),
                                                      IN newImage_url varchar(255), IN newDescription text,
                                                      IN newStock int, IN newCatalog_id int,
                                                      IN newImport_price double, IN newStatus tinyint(1))
begin
    update products set product_name = newProduct_name, image_url = newImage_url , description = newDescription, stock = newStock, catalog_id = newCatalog_id, price = newImport_price, status = newStatus   where id=newId;
end;

