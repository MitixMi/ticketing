<!DOCTYPE html>
<html>
<head>
    <title>User Data</title>
</head>
<body>
    <h2>User Data</h2>
    <c:forEach var="data" items="${dataList}">
        <p>${dataList}</p>
    </c:forEach>

    <form method="post" action="/sprintl/listControllers/uploadFile" enctype="multipart/form-data">
        <label for="file">Upload File:</label>
        <input type="file" name="file" id="file" required>
        <button type="submit">Upload</button>
    </form>

    <form method="get" action="/sprintl/listControllers/logout">
        <button type="submit">Logout</button>
    </form>
</body>
</html>

