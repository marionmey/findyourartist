<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--  Import JS -->
	<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.min.js"/>"></script>
	<!-- API Map -->
	<script type="text/javascript" src="<c:url value="/resources/javascript/map.js"/>"></script>

<!--  Import CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" media="screen" />

<!-- <script
 src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script> -->

<script type="text/javascript">
//The following example creates complex markers to indicate beaches near
//Sydney, NSW, Australia. Note that the anchor is set to
//(0,32) to correspond to the base of the flagpole.

/*
 * 
 */
 
function initialize() {
	
	var mapOptions = {
	 zoom: 10,
	 center: new google.maps.LatLng(-33.9, 151.2)
	}
	var map = new google.maps.Map(document.getElementById('map-canvas'),
	                             mapOptions);
	
	<c:choose>
	<c:when test="${not empty listLongLatBeans}">
	
	var shops = [
	<c:forEach items="${listLongLatBeans}" var="entry" varStatus="status">
		['<c:out value="${entry.label}"/>',
		<c:out value="${entry.latitude}"/>,
		<c:out value="${entry.longitude}"/>]
		<c:if test="${!status.last}">,</c:if>
	</c:forEach>
	];
	/*
	for (var i = 0, length = jsonArray.length; i < length; i++) {
        var data = jsonArray[i];
        shops.push(data.label);
        shops.push(data.latitude);
        shops.push(data.longitude); 
	}*/
	
	setMarkers(map,shops);
	</c:when>
	<c:otherwise>
	// Test de la fonctionnalité des marqueurs puisque ceux en fonction des choix de l'utilisateur ne fonctionnent pas.
	// Axe d'amélioration
	setMarkers(map,beaches);
	</c:otherwise>
	</c:choose>
	
}

/**
* Data for the markers consisting of a name, a LatLng and a zIndex for
* the order in which these markers should display on top of each
* other.
*/
var beaches = [

['Bondi Beach', -33.890542, 151.274856, 4],
['Coogee Beach', -33.923036, 151.259052, 5],
['Cronulla Beach', -34.028249, 151.157507, 3],
['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
['Maroubra Beach', -33.950198, 151.259302, 1]
];


var jsonArray = htmlTojson();


function htmlTojson() {
	   var json = '{';
	   var otArr = [];
	   var tbl2 = $('#listLongLatBeans tr').each(function(i) {        
	      x = $(this).children();
	      var itArr = [];
	      x.each(function() {
	         itArr.push('"' + $(this).text() + '"');
	      });
	      otArr.push('"' + i + '": [' + itArr.join(',') + ']');
	   })
	   json += otArr.join(",") + '}'

	   return json;
	}
	
function dataToArray(data)
{
    data = data.replace(/"[0-9]+":/g,"");          //Remove all index keys
    data = data.replace(/,"length":"[0-9]+"/g,""); //Remove length key-value pair
    data = data.replace(/{/g,"[");                 //Change the left brackets
    data = data.replace(/}/g,"]");                 //Change the right brackets

    return JSON.parse(data);
}


function setMarkers(map, locations) {
	// Add markers to the map
	var bounds = new google.maps.LatLngBounds();
	
	for (var i = 0; i < locations.length; i++) {
	 var shop = locations[i];
	 var myLatLng = new google.maps.LatLng(shop[1], shop[2]);
	 var marker = new google.maps.Marker({
	     position: myLatLng,
	     map: map,
	     title: shop[0]
	 //,zIndex: shop[3]
	 });
	 bounds.extend(myLatLng);
	}
	// Automatically center the map fitting all markers on the screen
    map.fitBounds(bounds);
}

google.maps.event.addDomListener(window, 'load', initialize);


	
	
</script>


<html>
<head>
	<title>Find Your Artist</title>
</head>
<body>

<div id="header">
	<div id="header-inner">
	
<div id="title">Find your Artist</div>

<c:url value="/search" var="searchURL" />
<form:form method="POST" commandName="searchFormBean" action="${searchURL}" >
	
		
	<form:select path="idPathSelectedStyle" id="idStyle">
		<form:option value="0" label="Style" />
		<c:forEach items="${searchFormBean.listArtist}" var="entry">
			<form:option value="${entry.id}" label="${entry.style}"></form:option>
		</c:forEach>
	</form:select>
	
	<form:select path="idPathSelectedCity" id="idCity">
		<form:option value="0" label="City" />
		<c:forEach items="${searchFormBean.listShops}" var="entry">
			<form:option value="${entry.id}" label="${entry.city}"></form:option>
		</c:forEach>
	</form:select>

		
	<input type="submit" name="rechercher" id="rechercher" value="Search" class=".btn" />
</form:form>
</div>
</div>


<br/>

<div id="map-canvas" style="margin:top;height:800px; width:100%"></div>
<br>

<table id="listLongLatBeans"><!-- afin de montrer que la fonctionnalité des coordonnées fonctionne -->
<c:forEach items="${listLongLatBeans}" var="entry">
<tr><td class="label"><c:out value="${entry.label}"/></td>
<td class="latitude"><c:out value="${entry.latitude}"/></td>
<td class="longitude"><c:out value="${entry.longitude}"/></td></tr>
</c:forEach>
</table>

</body>
</html>
