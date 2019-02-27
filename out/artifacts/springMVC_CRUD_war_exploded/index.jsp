<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>

        $(function () {
            $("#jsonTest").click(function () {
                var url = this.href;
                var args = {};
                $.post(url, args, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var id = data[i].id;
                        var lastName = data[i].lastName;

                        alert("id:" + id + "lastName:" + lastName);
                    }
                });

                return false;
            })
        })

    </script>
</head>
<body>


<form action="testFileUpload" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc">
    <input type="submit" value="submit">
</form>

<a href="emps">emps</a>

<br>
<br>

<a href="testJson" id="jsonTest">jsonTest</a>

<br><br>

<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc">
    <input type="submit" value="submit">
</form>


<br><br><br>

<a href="testResponseEntity">testResponseEntity</a>

<br><br><br>

<a href="i18n">i18n</a>


</body>
</html>
