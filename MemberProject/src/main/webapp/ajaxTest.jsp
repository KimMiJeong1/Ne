<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX 테스트</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
   integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
   crossorigin="anonymous">
   
</script>
<script>
$().ready(function() {
   $("#btn1").click(function() {
      $.ajax({
         url : '/member/rest/addJson', //클라이언트가 요청을 보낼 서버의 URL 주소
         data : {//HTTP 요청과 함께 서버로 보낼 데이터
            name : '길동이', //<input type='text' name='name' value='길동이'> 
            pwd : '11111'
         },
         dataType : 'json', /*html, text, json, xml, script*///서버에 타입을 알려줌 (보낸다)
         method : 'get',//get방식으로 post로 주면 서버에서 받을 수 없다 GetMapping이라서
         success : function(data) { //성공하면 실해해 데이터값을 줄게
            $.each( data, function( key, value ) {//우리가 아까 jsonviewer에서 확인 했던 0이 key이고 그 안에 값들이 value인데 우리는 그 value안에 arry로 되어 있는 실제값을 빼기위해 
                 var tdValue = "";
                  $.each(value, function(k1, v1) {//value꺼낸 걸 또 다시 key value로 꺼내기 위해 
                      //alert(k1 + ' ' + v1);
                      // TD 에 합치기
                      tdValue += v1 + ",";//이제 우리는  key는 필요 없고 그 값인 value만 필요해서 ex> mno가 key =1이 value
                  });
                  // TR
                  $('#memberList').append("<tr><td>" + tdValue + "</td></tr>"); //tdValue += v1 합치고 그걸 한줄씩 출력해라
               })

         },

         error : function(xhr, status, error) {
            alert(xhr.status); // 에러코드(404, 500 등)
            alert(xhr.responseText); // html 포맷의 에러 메시지
            alert(status); // 'error'
            alert(error); // 'Not Found'
         }
      });
   });
});
</script>

</head>
<body>
    <button id="btn1">AJAX Request Test -- 여길 누르세요</button>
    <div>
      <table id="memberList" border = "1">
      </table>
    </div>
</body>
</html>