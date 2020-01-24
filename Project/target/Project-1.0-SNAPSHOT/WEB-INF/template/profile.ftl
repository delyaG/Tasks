<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>${login}</h2>
    <h3>Role: ${role}</h3>
    <form action="shop">
        <input type="submit" name="shop" value="Магазин">
    </form>
    <#if role == "ADMIN">
        <form action="admin">
            <input type="submit" name="adding" value="Добавить товар">
        </form>
    </#if>
    <form action="logout">
        <input type="submit" value="Выйти" style="background-color: coral">
    </form>
</body>
</html>