<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<#--    <style>-->
<#--        .product-container {-->
<#--            border: 2px red solid;-->
<#--            margin-top: 20px;-->
<#--            padding: 20px;-->
<#--        }-->
<#--    </style>-->
</head>
<body>
    <#list products as product>
        <div class="product-container">
            ID: ${product.getId()} ${product.getName()} ${product.getPrice()}
        </div>
    </#list>
</body>
</html>