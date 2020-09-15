<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Test</title>
</head>
<body>
<h1>파일 업로드</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
   <input type="file" value="파일 선택" name="file"/>
   <input type="submit" value="업로드"/>
</form>
</body>
</html>

<!-- 아직 컨드롤러를 만들지 않아서 오류 rest로 주던지 그냥 컨트롤러로 주던지 상관 없는데 파일업로드 한걸 클라이언트에 이미지가 나오게 할려면 어떤게 쉽냐면 ...이미지를 바로 줄껀지 img태그를 주면서 꺼낼지   -->