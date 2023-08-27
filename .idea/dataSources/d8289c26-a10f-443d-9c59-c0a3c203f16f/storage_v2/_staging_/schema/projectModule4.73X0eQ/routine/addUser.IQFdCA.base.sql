create
    definer = root@localhost procedure addUser(IN newRoleId int, IN newUserName varchar(255),
                                               IN newFullName varchar(255), IN newPass varchar(255),
                                               IN newCountry varchar(255), IN newCity varchar(255),
                                               IN newPhone varchar(255), IN newEmail varchar(255), IN newBirt date,
                                               IN newAvatar text, IN newStatus text)
begin
    insert into user(role_id, username,fullname,password,country,city,phone,email,birthdate,avatar,status )
    values 
    (newRoleId,newUserName,newFullName,newPass,newCountry,newCity,newPhone,newEmail,newBirt,newAvatar,newStatus);
end;

