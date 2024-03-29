<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--  Import JS -->
	<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.min.js"/>"></script>
	<!-- API Map -->
	<script type="text/javascript" src="<c:url value="/resources/javascript/map.js"/>"></script>

<!--  Import CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/principale.css"/>" media="screen" /><!-- feuille de style principale -->

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

setMarkers(map,shops);
}

/**
* Data for the markers consisting of a name, a LatLng and a zIndex for
* the order in which these markers should display on top of each
* other.

var beaches = [

['Bondi Beach', -33.890542, 151.274856, 4],
['Coogee Beach', -33.923036, 151.259052, 5],
['Cronulla Beach', -34.028249, 151.157507, 3],
['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
['Maroubra Beach', -33.950198, 151.259302, 1]
];
*/
	
var beaches[];
var shops = new Array();
int i=0;

<c:forEach items="${listLongLatBeans}" var="entry">
var shop = new Array();
shop.push(<c:out value="${entry.name}" />);
shop.push(<c:out value="${entry.latitude}" />);
shop.push(<c:out value="${entry.longitude}" />);
shop.push(i);

shops.push(shop);
alert(i);
	i++;
</c:forEach>
/*
<c:forEach items="${listLongLatBeans}" var="entry">
var shopDetails = [${entry.name},${entry.latitude}${entry.longitude}]


//beaches[i] = ["${entry.name}","${entry.latitude}","${entry.longitude}",i];
//alert(beaches[i][0] + beaches[i][1]);
</c:forEach>
*/

function setMarkers(map, locations) {
// Add markers to the map

// Marker sizes are expressed as a Size of X,Y
// where the origin of the image (0,0) is located
// in the top left of the image.

// Origins, anchor positions and coordinates of the marker
// increase in the X direction to the right and in
// the Y direction down.
var image = {
 url: 'resources/images/pin.png',
 // This marker is 20 pixels wide by 32 pixels tall.
 size: new google.maps.Size(20, 32),
 // The origin for this image is 0,0.
 origin: new google.maps.Point(0,0),
 // The anchor for this image is the base of the flagpole at 0,32.
 anchor: new google.maps.Point(0, 32)
};
// Shapes define the clickable region of the icon.
// The type defines an HTML &lt;area&gt; element 'poly' which
// traces out a polygon as a series of X,Y points. The final
// coordinate closes the poly by connecting to the first
// coordinate.
var shape = {
   coords: [1, 1, 1, 20, 18, 20, 18 , 1],
   type: 'poly'
};
for (var i = 0; i < locations.length; i++) {
 var beach = locations[i];
 var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
 var marker = new google.maps.Marker({
     position: myLatLng,
     map: map,
     icon: image,
     shape: shape,
     title: beach[0],
     zIndex: beach[3]
 });
}
}

<c:choose>
<c:when test="${not empty listLongLatBeans}">
google.maps.event.addDomListener(window, 'load', initialize);
</c:when>
<c:otherwise> 

</c:otherwise>
</c:choose>
	
	
</script>

<!-- 
<c:if test="${not empty listLongLatBeans}">

</c:if>
-->



<html>
<head>
	<title>Find Your Artist</title>
</head>
<body>
<c:url value="/search" var="searchURL" />
<form:form method="POST" commandName="searchFormBean" action="${searchURL}" >
	
		
	<form:select path="idPathSelectedStyle" id="idStyle" >
		<form:option value="0" label="Style" />
		<c:forEach items="${searchFormBean.listArtist}" var="entry">
			<form:option value="${entry.id}" label="${entry.style}"></form:option>
		</c:forEach>
	</form:select>
	
	<form:select path="idPathSelectedCity" id="idCity" >
		<form:option value="0" label="City" />
		<c:forEach items="${searchFormBean.listShops}" var="entry">
			<form:option value="${entry.id}" label="${entry.city}"></form:option>
		</c:forEach>
	</form:select>
		
	<input type="submit" name="rechercher" id="rechercher" value="Search" />
</form:form>

<div id="map-canvas" style="height:800px; width:800px"></div>
<br>
<c:forEach items="${listLongLatBeans}" var="entry">
${entry.name}${entry.latitude}${entry.longitude}
	
</c:forEach>
</body>
</html>
