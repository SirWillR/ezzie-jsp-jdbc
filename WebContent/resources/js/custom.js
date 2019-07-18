$(function() {
	/*
	function initMap() {

		// Pega o elemento onde o mapa ficará
		var mapCanvas = document.getElementById('map');

		// Coordenadas do Brasil
		var BRASIL = {
			lat : -13.83,
			lng : -48.02
		};

		// Limites do mapa do Brasil
		var BRASIL_BOUNDS = {
			north : 5.50,
			south : -34.00,
			west : -74.17,
			east : -34.53,
		};

		// Definição das opções do mapa
		var mapOptions = {
			center : BRASIL,
			restriction : {
				latLngBounds : BRASIL_BOUNDS,
				strictBounds : false,
			},
			zoom : 12,
			panControl : false,
			disableDefaultUI : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}

		// Inicia o mapa
		var map = new google.maps.Map(mapCanvas, mapOptions);

		// Adiciona o botão de zoom no mapa
		document.querySelector('.zoom-control-in').onclick = function() {
			map.setZoom(map.getZoom() + 1);
		};
		document.querySelector('.zoom-control-out').onclick = function() {
			map.setZoom(map.getZoom() - 1);
		};
		map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document
				.querySelector('.zoom-control'));

		// Define a imagem do marcador
		var markerImage = 'resources/img/marker.png';

		// Carrega os marcadores do array locations
		var markers = locations.map(function(location, i) {
			return new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				icon : markerImage
			});
		});

		// Cria um agrupamento de marcadores próximos
		var markerCluster = new MarkerClusterer(map, markers, {
			imagePath : 'resources/img/m'
		});
		google.maps.event.addListener(markerCluster, 'clusterclick', function(
				cluster) {
			map.setCenter(cluster.getCenter());
			map.setZoom(map.getZoom());
		});

		// Adiciona informação em todos marcadores
		markers.forEach(function(marker) {
			marker.addListener('click', function() {
				new google.maps.InfoWindow({
					content : '<div class="info-window">'
						+ '<h3>Info Window Content</h3>'
						+ '<div class="info-content">'
						+ '<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>'
						+ '</div>'
						+ '</div>',
					maxWidth : 400
				}).open(map, marker);
			});
		});
		
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

		// Marca no mapa ao clicar com o mouse
		var markerClick;
		google.maps.event.addListener(map, 'click', function(event) {
			if (markerClick == null) {
				markerClick = new google.maps.Marker({
					position : event.latLng,
					animation : google.maps.Animation.DROP,
					icon : markerImage,
					map : map
				});
			} else {
				markerClick.setPosition(event.latLng);
			}
			map.panTo(event.latLng);
			geocoder = new google.maps.Geocoder();
			geocoder.geocode({'latLng' : event.latLng},	function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					if (results[1]) {
						for (var i = 0; i < results.length; i++) {
							if (results[i].types[0] === "administrative_area_level_2") {
								var cidade = results[i].address_components[0].short_name;
							} else if (results[i].types[0] === "administrative_area_level_1") {
								var estado = results[i].address_components[0].short_name;
							} else if (results[i].types[0] === "country") {
								var pais = results[i].address_components[0].short_name;
							}
						}
					$("input[name='location']").val(cidade + ", " + estado + " - " + pais);
					} else {
						console.log("No reverse geocode results.")
					}
				} else {
					console.log("Geocoder failed: "	+ status)
				}
			});
		});

		// Create the search box and link it to the UI element.
		var input = document.getElementById('pac-input');
		var searchBox = new google.maps.places.SearchBox(input);
		map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

		// Bias the SearchBox results towards current map's viewport.
		map.addListener('bounds_changed', function() {
			searchBox.setBounds(map.getBounds());
		});

		// Listen for the event fired when the user selects a prediction and
		// retrieve
		// more details for that place.
		searchBox.addListener('places_changed', function() {
			var places = searchBox.getPlaces();

			if (places.length == 0) {
				return;
			}

			// For each place, get the icon, name and location.
			var bounds = new google.maps.LatLngBounds();
			places.forEach(function(place) {
				if (!place.geometry) {
					console.log("Returned place contains no geometry");
					return;
				}

				if (place.geometry.viewport) {
					// Only geocodes have viewport.
					bounds.union(place.geometry.viewport);
				} else {
					bounds.extend(place.geometry.location);
				}
			});
			map.fitBounds(bounds);
		});
		
		// Estilo do mapa
		var styles = [ {
			"featureType" : "landscape",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"lightness" : 65
			}, {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "poi",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"lightness" : 51
			}, {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "road.highway",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "road.arterial",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"lightness" : 30
			}, {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "road.local",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"lightness" : 40
			}, {
				"visibility" : "on"
			} ]
		}, {
			"featureType" : "transit",
			"stylers" : [ {
				"saturation" : -100
			}, {
				"visibility" : "simplified"
			} ]
		}, {
			"featureType" : "administrative.province",
			"stylers" : [ {
				"visibility" : "off"
			} ]
		}, {
			"featureType" : "water",
			"elementType" : "labels",
			"stylers" : [ {
				"visibility" : "on"
			}, {
				"lightness" : -25
			}, {
				"saturation" : -100
			} ]
		}, {
			"featureType" : "water",
			"elementType" : "geometry",
			"stylers" : [ {
				"hue" : "#ffff00"
			}, {
				"lightness" : -25
			}, {
				"saturation" : -97
			} ]
		} ];
		
		map.set('styles', styles);
	}
	*/

	//google.maps.event.addDomListener(window, 'load', initMap);

});