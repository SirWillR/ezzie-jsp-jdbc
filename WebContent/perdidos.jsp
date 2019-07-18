<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="pt-br">
<head>
<title>Ezzie - Perdidos</title>
<jsp:include page="includes/head.jsp" />
</head>
<body class="profile-page sidebar-collapse bg-ezzie">

	<div class="loader"></div>

	<jsp:include page="includes/navbar.jsp" />
	<jsp:include page="includes/login.jsp" />

	<div class="page-header" data-parallax="true">
		<div class="container">
			<div class="row">
				<div class="col-md-8 ml-auto mr-auto">
					<div class="brand text-center">
						<img src="resources/img/Ezzie-logo.png" class="img-fluid" alt="Ezzie" width="150px">
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="main main-raised">
		<div class="container">
			<div class="section" style="padding-top: 15px">
				<h2>
					<small class="text-muted">Faça uma busca rápida!</small>
				</h2>
				<form autocomplete="off">
					<div class="form-row">
						<div class="col">
							<select id="estado" class="form-control" onchange="verCidades()">
								<option selected>Escolha o estado...</option>
							</select>
						</div>
						<div class="col">
							<select id="cidade" class="form-control" onchange="verTipos()" disabled>
								<option selected>Escolha a cidade...</option>
							</select>
						</div>
						<div class="col">
							<select id="tipo" class="form-control" onchange="pesquisarObjs()" disabled>
								<option selected>Escolha o tipo...</option>
							</select>
						</div>
					</div>
				</form>
				<div class="card">
					<div class="card-body">
						<div id="map" style="margin-top: 0px"></div>
						<div style="display: none">
							<div class="controls zoom-control">
								<button class="zoom-control-in" title="Zoom In">+</button>
								<button class="zoom-control-out" title="Zoom Out">&minus;</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp" />
	<jsp:include page="includes/scriptsFooter.jsp" />
	<script type="text/javascript">
	
	function verCidades() {
		$.ajax({
			url : "PesquisaServlet",
			type : 'post',
			data : {
				type : 'cidade',
				uf : $("#estado").val(),
				situacao : 'Perdido'
			},
			beforeSend : function() {
				document.getElementById('cidade').options[0].innerHTML = "Pesquisando...";
			}
		}).done(function(msg) {
			var sel = document.getElementById('cidade');
			if (msg != '[]') {
				document.getElementById('cidade').options[0].innerHTML = "Escolha a cidade...";
				document.getElementById("cidade").disabled = false;

				var cidade = [];
				var arrayJson = JSON.parse(msg);
				arrayJson.forEach(function(objeto){
					cidade.push(objeto);
				});
				
				cidade.forEach(function (item, indice, array) {
					var opt = document.createElement('option');
					opt.appendChild( document.createTextNode(item.cidade) );
					opt.value = item.cidade; 
					sel.appendChild(opt);
				});
			} else {
				for (i = sel.options.length; i > 0; i--) {
					sel.options[i] = null;
				}
				document.getElementById('cidade').options[0].innerHTML = "Escolha a cidade...";
				document.getElementById("cidade").disabled = true;
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	}
	
	$(document).ready(function(){
		$.ajax({
			url : "PesquisaServlet",
			type : 'post',
			data : {
				type : 'estado',
				situacao : 'Perdido'
			},
			beforeSend : function() {
				document.getElementById('estado').options[0].innerHTML = "Pesquisando...";
			}
		}).done(function(msg) {
			document.getElementById('estado').options[0].innerHTML = "Escolha o estado...";
			var sel = document.getElementById('estado');
			if (msg != '[]') {
				
				var estados = [];
				var arrayJson = JSON.parse(msg);
				arrayJson.forEach(function(objeto){
					estados.push(objeto);
				});
				
				estados.forEach(function (item, indice, array) {
					var opt = document.createElement('option');
					opt.appendChild( document.createTextNode(item.uf) );
					opt.value = item.uf; 
					sel.appendChild(opt);
				});
			} else {
				for (i = sel.options.length; i > 0; i--) {
					sel.options[i] = null;
				}
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	});
	
	function verTipos() {
		$.ajax({
			url : "PesquisaServlet",
			type : 'post',
			data : {
				type : 'tipo',
				uf : $("#estado").val(),
				cidade : $("#cidade").val(),
				situacao : 'Perdido'
			},
			beforeSend : function() {
				document.getElementById('tipo').options[0].innerHTML = "Pesquisando...";
			}
		}).done(function(msg) {
			var sel = document.getElementById('tipo');
			for (i = sel.options.length; i > 0; i--) {
				sel.options[i] = null;
			}
			if (msg != '[]') {
				document.getElementById('tipo').options[0].innerHTML = "Escolha o tipo...";
				document.getElementById("tipo").disabled = false;
				
				var tipos = [];
				var arrayJson = JSON.parse(msg);
				arrayJson.forEach(function(objeto){
					tipos.push(objeto);
				});
				
				tipos.forEach(function (item, indice, array) {
					var opt = document.createElement('option');
					opt.appendChild( document.createTextNode(item.tipo) );
					opt.value = item.tipo; 
					sel.appendChild(opt);
				});
			} else {
				document.getElementById('tipo').options[0].innerHTML = "Escolha o tipo...";
				document.getElementById("tipo").disabled = true;
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	}
	
	function pesquisarObjs() {
		
		if($("#tipo").val() == 'Escolha o tipo...') {
			return;
		}
		
		// Define a imagem do marcador
		var markerImage = 'resources/img/marker.png';
		
		// Busca no banco de dados os marcadores
		$.ajax({
			url : "MarkerServlet",
			type : 'post',
			data : {
				cidade : $("#cidade").val(),
				uf : $("#estado").val(),
				tipo : $("#tipo").val(),
				situacao : 'Perdido'
			},
			beforeSend : function() {
				$("#msg").html("Pesquisando...");
			}
		}).done(function(msg) {
			// Inicia um novo mapa
			map = new google.maps.Map(mapCanvas, mapOptions);
				
			// Adiciona o botão de zoom no mapa
			zoomControlIn.onclick = function() {
			map.setZoom(map.getZoom() + 1);				};
			zoomControlOut.onclick = function() {
				map.setZoom(map.getZoom() - 1);
			};
			map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(zoomControl);
			
			// Adiciona estilo ao mapa
			map.set('styles', styles);
			if (msg != '[]') {
				
				locMarkers = [];
				var locInfo = [];
				var arrayJson = JSON.parse(msg);
				arrayJson.forEach(function(objeto){
					locMarkers.push(JSON.parse(objeto.latLng));
					locInfo.push(objeto);
				})
				
				// Carrega os marcadores do array locations
				markers = locMarkers.map(function(location, i) {
					return new google.maps.Marker({
						position : location,
						map : map,
						animation : google.maps.Animation.DROP,
						icon : markerImage
					});
				});
				
				var pos = {
						lat : locMarkers[0].lat,
						lng : locMarkers[0].lng
					};
				map.setCenter(pos);
				map.setZoom(11);

				// Cria um agrupamento de marcadores próximos
				markerCluster = new MarkerClusterer(map, markers, {
					imagePath : 'resources/img/m'
				});
				google.maps.event.addListener(markerCluster, 'clusterclick', function(
						cluster) {
					map.setCenter(cluster.getCenter());
					map.setZoom(map.getZoom());
				});
				
				// Adiciona informação em todos marcadores
				markers.forEach(function(marker, i) {
					marker.addListener('click', function() {
						if (activeInfoWindow) { activeInfoWindow.close();}
						infowindow = new google.maps.InfoWindow({
							content : '<div class="info-window text-center">'
								+ '<img src="' + (locInfo[i].imagem != null ? locInfo[i].imagem : 'resources/img/bg.jpg')+ '" alt="Objeto Image" class="img-raised rounded-circle img-fluid" width="100px">'
								+ '<h3>' + locInfo[i].titulo + '</h3>'
								+ '<div class="info-content">'
								+ '<p>' + locInfo[i].descricao + '</p>'
								+ '</div>'
								+ '<a href="ObjetoServlet?id=' + locInfo[i].id + '" class="badge badge-pill badge-secondary">Ver Mais</a>'
								+ '</div>',
							maxWidth : 400
						});
						infowindow.open(map, marker);
						activeInfoWindow = infowindow;
					});
				});
			} else {
				map.setZoom(1);
				bootbox.alert("Nenhum item cadastrado");
			}
		}).fail(function(jqXHR, textStatus, msg) {
			bootbox.alert(msg);
		});
	}
	
	var map;
	var markers = [];
	var locMarkers = [];
	var markerCluster;
	var mapCanvas;
	var BRASIL;
	var BRASIL_BOUNDS;
	var mapOptions;
	var styles;
	var activeInfoWindow;
	var zoomControlIn = document.querySelector('.zoom-control-in')
	var zoomControlOut = document.querySelector('.zoom-control-out')
	var zoomControl = document.querySelector('.zoom-control')
	
	$(function() {
		function initMap() {
			// Pega o elemento onde o mapa ficará
			mapCanvas = document.getElementById('map');
			// Coordenadas do Brasil
			BRASIL = {
				lat : -13.83,
				lng : -48.02
			};

			// Limites do mapa do Brasil
			BRASIL_BOUNDS = {
				north : 5.50,
				south : -34.00,
				west : -74.17,
				east : -34.53,
			};

			// Definição das opções do mapa
			mapOptions = {
				center : BRASIL,
				restriction : {
					latLngBounds : BRASIL_BOUNDS,
					strictBounds : false,
				},
				zoom : 11,
				panControl : false,
				disableDefaultUI : true,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			}

			// Inicia o mapa
			map = new google.maps.Map(mapCanvas, mapOptions);
			
			// Adiciona o botão de zoom no mapa
			zoomControlIn.onclick = function() {
				map.setZoom(map.getZoom() + 1);
			};
			zoomControlOut.onclick = function() {
				map.setZoom(map.getZoom() - 1);
			};
			map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(zoomControl);

			// Tente pega a geolocalizacao do HTML5.
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};
					map.setCenter(pos);
				});
			}
			
			// Adiciona estilo ao mapa
			styles = [{"featureType": "landscape", "stylers": [{"saturation": -100}, {"lightness": 65}, {"visibility": "on"}]}, {"featureType": "poi", "stylers": [{"saturation": -100}, {"lightness": 51}, {"visibility": "simplified"}]}, {"featureType": "road.highway", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "road.arterial", "stylers": [{"saturation": -100}, {"lightness": 30}, {"visibility": "on"}]}, {"featureType": "road.local", "stylers": [{"saturation": -100}, {"lightness": 40}, {"visibility": "on"}]}, {"featureType": "transit", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "administrative.province", "stylers": [{"visibility": "off"}]}, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "on"}, {"lightness": -25}, {"saturation": -100}]}, {"featureType": "water", "elementType": "geometry", "stylers": [{"hue": "#ffff00"}, {"lightness": -25}, {"saturation": -97}]}];
			map.set('styles', styles);
		}
		
		google.maps.event.addDomListener(window, 'load', initMap);
		
	});
	</script>
</body>
</html>