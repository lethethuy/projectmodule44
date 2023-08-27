create
    definer = root@localhost procedure updateUser(IN newId int, IN newRoleId int, IN newUserName varchar(255),
                                                  IN newFullName varchar(255), IN newPass varchar(255),
                                                  IN newPass2 varchar(255), IN newCountry varchar(255),
                                                  IN newCity varchar(255), IN newPhone varchar(255),
                                                  IN newEmail varchar(255), IN newBirt date, IN newAvatar text,
                                                  IN newStatus text)
begin
    update user set role_id = newRoleId, username = newUserName , 
    fullname = newFullName, password = newPass, password2 = newPass2,
    country = newCountry, city = newCity , phone = newPhone, email = newEmail, birthdate = newBirt, avatar = newAvatar, status = newStatus
    where id=newId;
end;

