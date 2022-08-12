<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>
            FlyByNight Services
        </title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="page">
            <div class="order-message">
                <span class="message">
                    <c:out value="${orderMessage}" />
                </span>
            </div>
        </div>
    </body>
    <style>
        .order-message {
            display: flex;
            width: 1280px;
            margin: auto;
        }
        .message {
            text-align: center;
            font-family: 'Arimo', sans-serif;
            font-size: 20px;
            margin-top: 20px;
        }
    </style>
</html>