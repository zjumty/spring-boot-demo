<#-- @ftlvariable name="projects" type="java.util.List<demo.domain.Project>" -->
<html>
<head>
    <title>Projects</title>
</head>
<body>
aaa
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
</body>
</html>