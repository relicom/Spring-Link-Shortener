<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Please welcome to you</h3>
<%--<form action="/person/form" method="post">--%>
<input type="text" name="id" value="8877" id="id"><br><br>
<input type="text" name="firstname" value="Abraham" id="firstname"><br><br>
<input type="text" name="lastname" value="Linkon" id="lastname"><br><br>
<input type="submit" value="ارسال" id="submit">
<%--</form>--%>
<script src="/static/jquery.js"></script>
<script>
    console.log("step1");
    $("#submit").click(function () {
        var id = $("#id").val();
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();

        console.log("id:" + id);
        console.log("firstname:" + firstname);
        console.log("lastname:" + lastname);

        // if (url.toLowerCase().startsWith("http")) {
        var postDate = {"id": id, "firstname": firstname, "lastname": lastname};
        var Json = JSON.stringify(postDate);
        console.log("postData:" + postDate);
        console.log("Json PostData:" + Json)
        $.ajax({
            url: "/person/form",
            type: "post",
            contentType: "application/json",
            dataType: 'json',
            // cache: false,
            // timeout: 60000,
            data: Json, // An object with the key 'submit' and value 'true;
            success: function (result) {
                console.log("result:" + result);
            }
        });
        // } else {
        //     alert("your link is not valid (must be start with http)");
        // }
    });
</script>
</body>
</html>
