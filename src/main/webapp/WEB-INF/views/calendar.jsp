<%--
  Created by IntelliJ IDEA.
  User: duq14
  Date: 2022-05-04
  Time: 오후 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/reset.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="main_container">
    <div class="title">
        <div><button class="prev_btn">&lt</button></div>
        <div>
            <h2><span class="title_year">${calendarVO.year}</span>년 <span class="title_month">${calendarVO.month}</span>월</h2>
        </div>
        <div><button class="next_btn">&gt</button></div>
    </div>
    <table class="calendar_table">
        <thead>
        <tr>
            <th scope="cols">일</th>
            <th scope="cols">월</th>
            <th scope="cols">화</th>
            <th scope="cols">수</th>
            <th scope="cols">목</th>
            <th scope="cols">금</th>
            <th scope="cols">토</th>
        </tr>
        </thead>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td>1</td>--%>
<%--            <td>2</td>--%>
<%--            <td>3</td>--%>
<%--            <td>4</td>--%>
<%--            <td>5</td>--%>
<%--            <td>6</td>--%>
<%--            <td>7</td>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
    </table>
</div>
<script>
    $(function(){
        let tbody;
        let date = {}
        let year = parseInt('${calendarVO.year}');
        let month = parseInt('${calendarVO.month}');

        let getDate = function(year, month){
            date.year = year;
            date.month = month;
            $.ajax({
                type : "POST",
                url : "proc" ,
                headers : { "content-type": "application/json"},
                data : JSON.stringify(date),
                success : function(result){
                    drawCalendar(result);
                },
                error: function(request,status,error){
                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                },
            })
        }
        let drawCalendar = function(vo){
            tbody = $('.calendar_table > tbody');
            console.log(tbody);
            if(tbody != null) tbody.remove();

            let tbodyHTML = "<tbody>";
            $.each(vo.cm.dayList, function(index, item){
                if( index == 0 || index % 7 == 0) tbodyHTML += "<tr>";
                tbodyHTML += "<td>"+item+"</td>";
            })
            tbodyHTML += "</tbody>";
            $('table').append(tbodyHTML);
            $('.title_year').html(vo.year);
            $('.title_month').html(vo.month);
        }
        $('.next_btn').on("click",function(){
            if(month == '12'){
                year++;
                month = 1;
            }else{
                month++;
            }
            getDate(year, month);
        })
        $('.prev_btn').on("click",function(){
            if(month == 1){
                year--;
                month = 12;
            }else{
                month--;
            }
            getDate(year, month);
        })
        getDate(year, month);
    })
</script>
</body>
</html>
