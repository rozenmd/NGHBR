<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">

<html>
<head>
	<%-- Stylesheets --%>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/font-awesome.min.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/bootstrap-social.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

	<%--//mapping css--%>
		<link href="<c:url value="/static/css/leaflet.min.css" />" rel="stylesheet">
	<%--//Charting css--%>
	<link rel="stylesheet" href="https://dc-js.github.io/dc.js/css/dc.css" />
	<%--//Charting JS--%>


	<%-- JavaScripts --%>
	<script
			src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

	<title>NGHBR</title>
</head>

<body>
<jsp:include page="/navBar"></jsp:include>




<div class="container">
	<div class="col-md-6" style="padding-top: 50px;">
		<h1>Scoreboard</h1>

		<table class="table table-bordered table-hover dc-data-table dc-chart">
			<thead>
			<tr class="header">
				<th>Suburb</th>
				<th>Postcode</th>
				<th>State</th>
				<th>Total Points</th>

			</tr>
			</thead>
		</table>
	</div>
	<div class="col-md-6" style="margin-top: 115px;">
		<div id="map" style="position: relative;
		width: 100%;
		height: 670px;"></div>
	</div>
</div>
</body>
<%--JS for data display on maps:--%>

<%--JS for data display on maps:--%>
<script src="<c:url value="/static/js/vendor/d3.v3.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/topojson.v1.min.js" />"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/crossfilter/1.3.11/crossfilter.js"></script>
<script src="https://dc-js.github.io/dc.js/js/dc.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.js"></script>
<script src='//api.tiles.mapbox.com/mapbox.js/plugins/leaflet-omnivore/v0.2.0/leaflet-omnivore.min.js'></script>

<script>
	function getColor(d) {
		return d > 1000 ? '#800026' :
				d > 500  ? '#BD0026' :
						d > 200  ? '#E31A1C' :
								d > 100  ? '#FC4E2A' :
										d > 50   ? '#FD8D3C' :
												d > 20   ? '#FEB24C' :
														d > 10   ? '#FED976' :
																'#FFEDA0';
	}

	function style(feature) {
		return {
			// fillColor: getColor(feature.properties.density),
			weight: 1,
			opacity: 1,
			color: 'black',
			dashArray: '3',
			fillOpacity: 0.15
		};
	}
	var mapboxAccessToken = 'pk.eyJ1Ijoicm96ZW5tZCIsImEiOiJlODZmMjk3NDBmYTBhODc5M2U0NDBiYzUyMWM3YjlmOSJ9.muc0sJgn7kvqjzT25Sch-A';
	var tiles = L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapboxAccessToken, {
				id: 'mapbox.streets',
				attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="http://mapbox.com">Mapbox</a>'
			}),
			latlng = L.latLng(${lat}, ${lon});

	var map = L.map('map', { center: latlng, zoom: 13, layers: [tiles] });
	var customLayer = L.geoJson(null, {style: style});
	var popupContent =  'Suburb: ${suburb} <br>' +
			'Total Points:  ${points} <br>';
	L.marker(latlng).addTo(map).bindPopup(popupContent,{
		closeButton: true,
		minWidth: 120
	});
	// this can be any kind of omnivore layer
	var runLayer = omnivore.topojson('/static/js/final.js', null, customLayer).addTo(map);






	var progress = document.getElementById('progress');
	var progressBar = document.getElementById('progress-bar');

	function updateProgressBar(processed, total, elapsed, layersArray) {
		if (elapsed > 1000) {
			// if it takes more than a second to load, display the progress bar:
			progress.style.display = 'block';
			progressBar.style.width = Math.round(processed/total*100) + '%';
		}

		if (processed === total) {
			// all markers processed - hide the progress bar:
			progress.style.display = 'none';
			progressText.style.display = 'none';


		}
	}

</script>


<script>
		var data = ${jsonSuburb}
		//data = JSON.parse(myJson);
		data.forEach(function(d) {
			d.totalPoints = +d.totalPoints;
		});
		var ndx = crossfilter(data);
		var all = ndx.groupAll();
		var id = ndx.dimension(function(d) {return d.totalPoints;});

		dc.dataTable(".dc-data-table")
				.dimension(id)
				.group(function(d) {return d.totalPoints;})
				.size(15)
				.columns([
					function(d) { return d.suburbName; },
					function(d) { return d.postcode},
					function(d) { return d.state},
					function(d) { return d.totalPoints},
				])
				.sortBy(function (d) {
					return d.totalPoints;
				})
				.order(d3.descending)
				.renderlet(function (table) {
					table.selectAll(".dc-table-group").classed("info", true);
				});
		dc.renderAll();

</script>
</html>


