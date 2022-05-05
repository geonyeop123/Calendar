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
    </table>
</div>
<script>
    $(function(){
        /////
        // 변수 선언
        /////

        let tbody;
        let dateJSON = {};
        let year = parseInt('${calendarVO.year}');
        let month = parseInt('${calendarVO.month}');
        let tbodyHTML = "";
        // draw 시 전 달, 다음 달의 일자에 class를 추가하기 위해 첫번째와 마지막 tr을 담을 list
        let firstLastTr = [];
        let buttonFlag = false;

        // calendar를 만드는 ajax 함수
        let makeCalendar = function(year, month){
            dateJSON.year = year;
            dateJSON.month = month;
            $.ajax({
                type : "POST",
                url : "proc" ,
                headers : { "content-type": "application/json"},
                data : JSON.stringify(dateJSON),
                success : function(result){
                    drawCalendar(result);
                },
                error: function(request,status,error){
                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    alert("알 수 없는 에러가 발생했습니다!!");
                    location.href='error.jsp';
                },
            })
        }
        // calendar를 그리는 함수
        let drawCalendar = function(vo){
            // 현재 그려진 tbody가 있는지 체크, 있으면 지움
            tbody = $('.calendar_body');
            if(tbody != null) tbody.remove();

            // 그릴 tbody 생성
            tbodyHTML = "<tbody class='calendar_body'>";

            $.each(vo.cm.dayList, function(index, item){
                // 최초와 7번째마다 tr태그로 구분
                if( index == 0 || index % 7 == 0) tbodyHTML += "<tr>";
                tbodyHTML += "<td>"+item+"</td>";
            })
            // tbody를 table에 삽입
            tbodyHTML += "</tbody>";
            $('table').append(tbodyHTML);
            // calendar의 타이틀 변경
            $('.title_year').html(vo.year);
            $('.title_month').html(vo.month);
            // 첫번째, 마지막 td를 가져옴
            firstLastTr = [$('.calendar_body tr:first td'), $('.calendar_body tr:last td')];
            // 전달 혹은 다음달의 값이 있는 경우 anotherDay class 추가
            firstLastTr.forEach((tr, index)=>{
                tr.each(function(){
                    if(index == 0 ? $(this).html() > 10
                                  : $(this).html() < 10 ){
                        $(this).addClass('anotherDay');
                    }
                })
            })
        }

        // button을 클릭 시 수행될 함수
        let buttonFunction = function(mode){
            // mode에 따라 분기 처리
            buttonFlag = mode == 'N' ? true : false;
            month = buttonFlag ? month + 1 : month - 1;

            if(buttonFlag ? month > 12 : month < 1){
                month = buttonFlag ? 1 : 12;
                year = buttonFlag ? year + 1 : year - 1;
            }

            makeCalendar(year, month);
        }
        // $('.next_btn').on("click",function(){
        //     if(month == '12'){
        //         year++;
        //         month = 1;
        //     }else{
        //         month++;
        //     }
        //     getDate(year, month);
        // })
        // $('.prev_btn').on("click",function(){
        //     if(month == 1){
        //         year--;
        //         month = 12;
        //     }else{
        //         month--;
        //     }
        //     getDate(year, month);
        // })

        /////
        // 이벤트
        /////

        $('.next_btn').on("click", ()=> buttonFunction('N'));

        $('.prev_btn').on("click", ()=> buttonFunction('P'));

        // 최초 접속 시 캘린더를 그리기 위해 ajax 호출
        makeCalendar(year, month);
    })
</script>
</body>
</html>
