<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>FlyByNight Services</title>
    </head>
    <body>
        <%@include file="header.html"%>
        <div class="container justify-content-center flex-box d-flex">
            <div class="flight-select d-flex justify-content-center">
                <form action="flights" method="get" class="flex-box d-flex flex-row">
                    <span>Origin:</span>
                    <label>
                        <select class="form-select" name="origin">
                            <option value="Any">Any</option>
                            <c:forEach items="${origins}" var="origin">
                                <option value="${origin}">${origin}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <span>Destination:</span>
                    <label>
                        <select class="form-select" name="destination">
                            <option value="Any">Any</option>
                            <c:forEach items="${destinations}" var="destination">
                                <option value="${destination}">${destination}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" class="flight-search" value="Search" />
                </form>
            </div>
            <%--<c:if test="${startedSearch}">--%>
                <div class="flights-table justify-content-center">
                    <c:if test="${flightOrigin != null}">
                        <h2 class="flights-info">Found <c:out value="${flights.size()}" /> flights from <c:out value="${flightOrigin}" /> to <c:out value="${flightDestination}" />.</h2>
                    </c:if>
                    <c:if test="${flights.size() > 0}">
                        <table class="flights">
                            <thead>
                                <tr>
                                    <th>Flight number</th>
                                    <th>Origin</th>
                                    <th>Destination</th>
                                    <th>Carrier</th>
                                    <th>Price</th>
                                    <th>Day</th>
                                    <th>Time</th>
                                    <th>Duration</th>
                                    <th>Available seats</th>
                                    <th>Buy tickets</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${flights}" var="flight">
                                    <tr id="${flight.id}">
                                        <td>${flight.flightNumber}</td>
                                        <td>${flight.origin}</td>
                                        <td>${flight.destination}</td>
                                        <td>${flight.carrier}</td>
                                        <td>${flight.price}</td>
                                        <td>${flight.flightDay}</td>
                                        <td>${flight.flightTime}</td>
                                        <td>${flight.duration}</td>
                                        <td>${flight.availableSeats}</td>
                                        <td>
                                            <form class="buy-form" action="order" method="post" <c:if test="${flight.availableSeats == 0}">style="pointer-events: none; opacity: 0.5;"</c:if>>
                                                <input type="hidden" name="id" value="${flight.id}">
                                                <label>
                                                    <input type="number" min="1" max="${flight.availableSeats}" name="quantity" value="1" />
                                                </label>
                                                <input type="submit" value="Buy">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            <%--</c:if>--%>
        </div>
    </body>
    <style>
        .flights {
            margin: 0 auto;
            border-collapse: collapse;
            border: 1px solid #081c2f;
            margin-top: 50px;
        }
        .flights th {
            width: 140px;
            height: 40px;
            text-align: center;
            background-color: #daf5ff;
            font-family: 'Arimo', sans-serif;
            font-size: 18px;
            border-bottom: 1px solid #222222;
            border-top: 1px solid #222222;
        }
        .flights td {
            width: 140px;
            height: 40px;
            text-align: center;
            border-bottom: 1px solid #666666;
            font-family: 'Arimo', sans-serif;
            font-size: 18px;
            color: #333333;
            background-color: #cfe6ee;
        }
        .flights td:nth-child(even) {
            background-color: #e1f7ff;
        }
        .flight-select {
            margin-top: 50px;
            justify-content: center;
            display: flex;
        }
        .flight-select select {
            width: 150px;
            height: 30px;
            margin-left: 20px;
        }
        .flight-select span {
            font-family: 'Arimo', sans-serif;
            font-size: 25px;
            margin-left: 50px;
            vertical-align: middle;
        }
        .flight-search {
            margin-left: 50px;
            width: 100px;
            background-color: #1b1b2b;
            color: white;
            border-radius: 10px;
            height: 35px;
            border: 1px solid #222222;
            font-size: 16px;
        }
        .flight-search:hover {
            background-color: white;
            cursor: pointer;
            color: black;
        }
        .flights-info {
            font-family: 'Arimo', sans-serif;
            font-size: 24px;
            text-align: center;
            margin-top: 30px;
        }
        .buy-form {
            height: 40px;
            padding: 0;
            margin: 0;
            justify-content: center;
            align-items: center;
            display: flex;
        }
        .buy-form input[type=number] {
            width: 50px;
            height: 30px;
            margin-right: 10px;
            text-align: center;
            font-size: 16px;
        }
        .buy-form input[type=number]:focus {
            outline: none;
        }
        .buy-form input[type=submit] {
            width: 50px;
            height: 30px;
            font-size: 17px;
        }
        .buy-form input[type=submit]:hover {
            cursor: pointer;
        }
        .container {
            margin: auto;
        }
    </style>
</html>