<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load("current", {
    packages: ["corechart"]
  });
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {
	  fetch("drawChart.do")
	  .then(resolve => resolve.json())
	  .then(result => {
		  console.log(result);
	  })
	  .catch(err => console.log(err))
	  
    var data = google.visualization.arrayToDataTable([
      ['Name', 'Reply Count'], //타이틀 반드시 문자열로 만들어야 함
      ['Work', 16],
      ['Eat', 2],
      ['Commute', 2],
      ['Watch TV', 2],
      ['Sleep', 40],
      ['exercise', 10]
    ]);

    var options = {
      title: '작성자 건수별',
      is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
  }
  </script>
  <div id="piechart_3d" style="width: 900px; height: 500px;"></div>
  <include page = "../layout/footer.jsp" > </include>