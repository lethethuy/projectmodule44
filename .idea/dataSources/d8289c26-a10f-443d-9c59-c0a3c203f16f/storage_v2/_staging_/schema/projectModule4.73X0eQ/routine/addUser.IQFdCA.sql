create
    definer = root@localhost procedure addUser(
                                               IN newFullName varchar(255), IN newPass varchar(255),

                                               IN newPhone varchar(255), IN newEmail varchar(255)
                                        )
begin
    insert into user(role_id,fullname,password,phone,email )
    values 
    (2,newFullName,newPass,newPhone,newEmail);
end;

