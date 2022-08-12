<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Flight Night Services</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="container justify-content-center d-flex">
            <h2 class="current-tickets-text">Your current tickets</h2>
            <div id="tickets" class="d-flex justify-content-center">
            </div>
        </div>
    </body>
    <style>
        .container {
            margin: auto;
        }
        .current-tickets-text {
            font-family: 'Arimo', sans-serif;
            text-align: center;
            text-transform: uppercase;
        }
        #tickets h4 {
            text-align: center;
            font-family: 'Arimo', sans-serif;
        }
    </style>
    <script type="text/javascript">
        const ticketsElement = document.getElementById("tickets");
        const tickets = JSON.parse(sessionStorage.getItem("tickets"));
        if (tickets === null) {
            const text = document.createElement("h4");
            text.innerText = "No tickets bought yet."
            ticketsElement.appendChild(text);
        }
        else {
            for (let ticket of tickets) {
                const text = document.createElement("h4");
                text.innerText = ticket.quantity + " tickets for flight with id: " + ticket.id;
                ticketsElement.appendChild(text);
            }
        }
    </script>
</html>