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
                    <c:out value="${order.message}" />
                </span>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        if (${order.accepted}) {
            let tickets = JSON.parse(sessionStorage.getItem("tickets"));
            if (tickets === null) {
                tickets = [{id:"${order.flightNumber}", quantity: ${order.quantity}}];
                sessionStorage.setItem("tickets", JSON.stringify(tickets));
            } else {
                const addedTicketIndex = tickets.findIndex( t => t.id === "${order.flightNumber}");
                if (addedTicketIndex === -1) {
                    tickets.push({id:"${order.flightNumber}", quantity: ${order.quantity}});
                } else {
                    tickets[addedTicketIndex].quantity += ${order.quantity};
                }
                sessionStorage.setItem("tickets", JSON.stringify(tickets));
            }
        }
    </script>
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