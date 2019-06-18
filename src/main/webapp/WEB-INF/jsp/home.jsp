<%--
  URL Link Shortnere home page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Useful and Handsome URL Link Shortener with Statistics</title>
    <style>
        a{
            text-decoration: none;
        }
        .approved {
            color: green;
        }

        .notapproved {
            color: red;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<div>

    <h1>Simple URL Link Shortener</h1>

    <p>Long Link Address:
        <input minlength="60" type="text" placeholder="Please enter URL that wants to be shortener" name="newurl"
               id="url">
    </p>

    <p>you could to enter your custom characters to make short link</p>
    <p>Allowed characters are english lowercase ,uppercase letters ,english numbers and underline (a-z),(A-Z),(_)
        with totally minimum 5 characters </p>

    <span id="hostname"></span>
        <input minlength="60" type="text" placeholder="optional featur ro selecting custom short link" name="newurl"
               id="shortlink">

    <input type="submit" value="Check" id="check">

    <p>Status :
    <p id="status">Not Checked</p>
    </p>

    <input type="submit" value="Submit" id="submit" class="hidden">
    <a id="finallink" target="_blank"></a>
    <p>you could to see visitor statistics by adding '+' (plus sign) to end of the short link</p>
    <a id="statelink" target="_blank"></a>

</div>
<script src="/static/jquery.js"></script>
<script>
    var submit = $("#submit");
    var shortLink = $("#shortlink");
    var url = $("#url");
    var statusOp = $("#status");
    var host = document.location.origin;
    $("#hostname").text(host + "/");
    var finallink = $("#finallink");
    var statelink=$("#statelink");

    function setFinalLink(isClean, href) {
        finallink.attr("href", isClean ? "" : href);
        finallink.text(isClean ? "" :"Short Link is : "+ href);
        statelink.attr("href", isClean ? "" : href+"+");
        statelink.text(isClean ? "" : "Statistics Link is : "+href+"+");
    }

    setFinalLink(true);

    $("#check").click(function () {
        submit.addClass('hidden');
        setFinalLink(true);
        var shortLinkVal = shortLink.val();
        var urlVal = url.val();
        if (urlVal.toLowerCase().startsWith("http")) {
            var jsonPostData = JSON.stringify({"url": urlVal, "customLink": shortLinkVal ? shortLinkVal : null});
            $.ajax({
                url: "/v/new",
                type: "post",
                contentType: "application/json",
                dataType: 'json',
                cache: false,
                timeout: 5000,
                data: jsonPostData,
                success: function (jo) {
                    if (jo.msg) {
                        statusOp.removeClass().addClass("notapproved").text(jo.msg);
                    } else {
                        statusOp.removeClass().addClass("approved").text(jo.status.msg);
                        if ([1, 3, 4].includes(jo.status.code)) {
                            shortLink.val(jo.shortLink);
                            submit.removeClass('hidden');
                        }
                    }
                }
            });
        } else {
            alert("your link is not valid (must be start with http)");
        }
    });

    submit.click(function () {
        var shortLinkVal = shortLink.val();
        var urlVal = url.val();
        if (urlVal && shortLinkVal && urlVal.toLowerCase().startsWith("http")) {
            var jsonPostData = JSON.stringify({"url": urlVal, "customLink": shortLinkVal});
            $.ajax({
                url: "/v/add",
                type: "post",
                contentType: "application/json",
                dataType: 'json',
                cache: false,
                timeout: 5000,
                data: jsonPostData,
                success: function (jo) {
                    statusOp.text(jo.msg);
                    if ([1].includes(jo.code)) {
                        statusOp.removeClass().addClass("approved");
                        submit.addClass('hidden');
                        setFinalLink(false, host + "/" + jo.shortLink);
                    }
                }
            });
        } else {
            alert("your link is not valid (must be start with http)");
        }
    });

</script>
</body>
</html>
