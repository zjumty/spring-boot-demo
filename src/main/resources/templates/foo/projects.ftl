<#-- @ftlvariable name="quotes" type="java.util.List<demo.domain.Quote>" -->
<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="projects" type="java.util.List<demo.domain.Project>" -->
<html>
<head>
    <title>Projects</title>
</head>
<body>
${name} aaaaaaa bbbbb ccc ddd eee fff
<table>
    <tr>
        <th>ID</th>
        <th>TYPE</th>
        <th>NAME</th>
        <th>BEGIN</th>
        <th>END</th>
    </tr>

<#list projects as project>
    <tr>
        <td>${project.id}</td>
        <td>${project.type}</td>
        <td>${project.name}</td>
        <td>${project.begin?string("yyyy-MM-dd")}</td>
        <td>${project.end?string("yyyy-MM-dd")}</td>
    </tr>
</#list>
</table>

<table>
    <tr>
        <th>ID</th>
        <th>TYPE</th>
        <th>NAME</th>
    </tr>

<#list quotes as quote>
    <tr>
        <td>${quote.id.toString()}</td>
        <td>${quote.tenantId}</td>
        <td>${quote.symbol}</td>
    </tr>
</#list>
</table>

</body>
</html>